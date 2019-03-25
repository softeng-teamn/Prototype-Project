package controller;

import model.Node;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import java.sql.SQLException;

import static controller.DBController.myDBC;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class DBControllerTest {

    @Before
    public void setUp(){
        DBController.init("NeoDB");
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
    public void deleteNode() {
    }

    @Test
    public void updateNode() {
    }

    @Test
    public void getNodes() {
    }

    @Test
    public void getAllNodes() {
    }

    @Test
    //@Category(FastTest.class)
    public void getNode() {
        String toGet = "ACONF00102";
        assertThat(myDBC.getNode(toGet).getNodeID(),is(toGet));
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