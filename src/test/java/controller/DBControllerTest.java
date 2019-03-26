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

import static controller.DBController.myDBC;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class DBControllerTest {

    @Before
    public void setUp(){
        DBController.init();
    }

    @After
    public void tearDown() throws Exception {
        myDBC.dropAll();
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
        assertThat(myDBC.insertNode(testNode),is(true));
        // make sure that the same node cannot be inserted a second time
        assertThat(myDBC.insertNode(testNode),is(false));
    }

    @Test
    @Category(FastTest.class)
    public void deleteNode() {
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        myDBC.insertNode(testNode);
        // make sure it can be got
        assertThat(myDBC.getNode("ACONF00102").getNodeID(),is("ACONF00102"));
        // delete the node from the database successfully
        assertThat(myDBC.deleteNode(testNode),is(true));
        //make sure that it is not in the database
        assertThat((myDBC.getNode("ACONF00102")),is(nullValue()));

    }

    @Test
    public void updateNode() {
    }

    @Test
    public void getNodes() {
    }

    @Test
    @Category(FastTest.class)
    public void getAllNodes() {
        // insert nodes
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        myDBC.insertNode(testNode);
        testNode = new Node("ACONF00103", "3", "BTM", "CONF", "BTM Conference Center", "BTM Conference", 1648, 2968);
        myDBC.insertNode(testNode);
        ArrayList<Node> allNodes = myDBC.getAllNodes();
        assertThat(allNodes.size(),is(2));
        assertThat(allNodes.get(0).getNodeID(),is("ACONF00102"));

        assertThat(allNodes.get(1).getNodeID(),is("ACONF00103"));
    }

    @Test
    @Category(FastTest.class)
    public void getNode() {
        Node testNode = new Node("ACONF00102", "2", "BTM", "HALL", "Hall", "Hall", 1580, 2538);
        myDBC.insertNode(testNode);
        String toGet = "ACONF00102";
        // make sure the note is successfully got
        assertThat(myDBC.getNode(toGet).getNodeID(),is(toGet));


    }

    @Test
    @Category(FastTest.class)
    public void getNodeFailure(){
        // make sure that nodes that are not in the database throw an error and return null
        assertThat((myDBC.getNode("ACONF00102")),is(nullValue()));
    }

    @Test
    public void tableExists() {
    }

    @Category(FastTest.class)
    @Test
    public void dropAll(){
        // A table must exist first before we can test dropping it
        Assert.assertThat(myDBC.tableExists("NODE"), is(true));

        myDBC.dropAll();

        Assert.assertThat(myDBC.tableExists("NODE"), is(false));
    }
}