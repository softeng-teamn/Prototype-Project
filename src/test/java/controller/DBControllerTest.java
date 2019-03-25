package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import java.sql.SQLException;

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
        DBController.myDBC.dropAll();
        close();
    }

    @Test
    public void close() {
    }

    @Test
    public void insertNode() {
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
    public void getNode() {
    }

    @Test
    public void tableExists() {
    }
}