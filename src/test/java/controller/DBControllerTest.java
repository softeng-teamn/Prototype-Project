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
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Category(FastTest.class)
    public void init(){
        // This test wouldn't be valid if myDBC already had a value, so check this first
        assertThat(DBController.myDBC, is(nullValue()));

        // Attempt to initialize
        DBController.init();

        // Verify that myDBC now has a value and has the correct name
        assertThat(DBController.myDBC, is(notNullValue()));
        assertThat(DBController.myDBC.getName(), is("prototype-DB"));

        // Attempt to close
        DBController.close();

        // myDBC should once again be null
        assertThat(DBController.myDBC, is(nullValue()));
    }

    @Test
    public void init1() {
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