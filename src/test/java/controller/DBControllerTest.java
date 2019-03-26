package controller;

import model.Node;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import java.sql.SQLException;
import java.util.ArrayList;

//import static controller.DBController.getMyDBC;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class DBControllerTest {

    @Before
    public void setUp(){
        DBController.init("prototype-db-test");
    }

    @After
    public void tearDown() throws Exception {
        DBController.getMyDBC().dropAll();
        close();
    }


    @Test
    public void close() {
    }

    @Test
    @Category(FastTest.class)
    public void insertNode() {
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        // make sure that the new node is successfully inserted
        assertThat(DBController.getMyDBC().insertNode(testNode), is(true));
        // make sure that the same node cannot be inserted a second time
        assertThat(DBController.getMyDBC().insertNode(testNode), is(false));
    }

    @Test
    @Category(FastTest.class)
    public void deleteNode() {
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        DBController.getMyDBC().insertNode(testNode);
        // make sure it can be got
        assertThat(DBController.getMyDBC().getNode("ACONF00102").getNodeID(), is("ACONF00102"));
        // delete the node from the database successfully
        assertThat(DBController.getMyDBC().deleteNode(testNode),is(true));
        //make sure that it is not in the database
        assertThat((DBController.getMyDBC().getNode("ACONF00102")), is(nullValue()));
        //delete is like update so trying to delete a record that isn't there doesn't cause problems. No case needed for that.
    }

    @Test
    @Category(FastTest.class)
    public void updateNode() {
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        DBController.getMyDBC().insertNode(testNode);

        Node result = DBController.getMyDBC().getNode("ACONF00102");
        assertThat(result, is(testNode));

        testNode = new Node("ACONF00102", "3", "BTM", "CONF", "Halla", "Halls", 1582, 2540);
        DBController.getMyDBC().updateNode(testNode);

        result = DBController.getMyDBC().getNode("ACONF00102");
        assertThat(result, is(testNode));
    }

    @Test
    public void getNodes() {
    }

    @Test
    @Category(FastTest.class)
    public void getAllNodes() {
        // insert nodes
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        DBController.getMyDBC().insertNode(testNode);
        testNode = new Node("ACONF00103", "3", "BTM", "CONF", "BTM Conference Center", "BTM Conference", 1648, 2968);
        DBController.getMyDBC().insertNode(testNode);
        ArrayList<Node> allNodes = DBController.getMyDBC().getAllNodes();
        assertThat(allNodes.size(),is(2));
        assertThat(allNodes.get(0).getNodeID(),is("ACONF00102"));
        assertThat(allNodes.get(1).getNodeID(),is("ACONF00103"));

        testNode = new Node("ACONF00104", "3", "BTM", "CONF", "BTM Conference Center", "BTM Conference", 1648, 2968);
        DBController.getMyDBC().insertNode(testNode);
        allNodes = DBController.getMyDBC().getAllNodes();
        assertThat(allNodes.size(),is(3));
        assertThat(allNodes.get(0).getNodeID(),is("ACONF00102"));
        assertThat(allNodes.get(1).getNodeID(),is("ACONF00103"));
        assertThat(allNodes.get(2).getNodeID(),is("ACONF00104"));
    }

    @Test
    @Category(FastTest.class)
    public void getNode() {
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        DBController.getMyDBC().insertNode(testNode);
        String toGet = "ACONF00102";
        // make sure the note is successfully got
        assertThat(DBController.getMyDBC().getNode(toGet), is(testNode));
    }

    @Test
    @Category(FastTest.class)
    public void getNodeFailure(){
        // make sure that nodes that are not in the database throw an error and return null
        assertThat((DBController.getMyDBC().getNode("ACONF00102")),is(nullValue()));
    }

    @Test
    public void tableExists() {
    }

    @Category(FastTest.class)
    @Test
    public void dropAll(){
        // A table must exist first before we can test dropping it
        Assert.assertThat(DBController.getMyDBC().tableExists("NODE"), is(true));

        DBController.getMyDBC().dropAll();

        Assert.assertThat(DBController.getMyDBC().tableExists("NODE"), is(false));
    }
}