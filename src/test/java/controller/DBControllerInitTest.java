package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class DBControllerInitTest {


    @Test
    @Category(FastTest.class)
    public void init(){
        // This test wouldn't be valid if myDBC already had a value, so check this first
        assertThat(DBController.myDBC, is(nullValue()));

        // Attempt to initialize
        DBController.init();

        // Verify that myDBC now has a value and has the correct name
        assertThat(DBController.myDBC, is(notNullValue()));
        assertThat(DBController.myDBC.getName(), is("prototype-db-test"));

        // Attempt to close
        DBController.close();

        // myDBC should once again be null
        assertThat(DBController.myDBC, is(nullValue()));
    }

    @Test
    @Category(FastTest.class)
    public void named_init(){
        // This test wouldn't be valid if myDBC already had a value, so check this first
        assertThat(DBController.myDBC, is(nullValue()));

        String dbName = "prototype-db-test-2";

        // Attempt to initialize
        DBController.init(dbName);

        // Verify that myDBC now has a value and has the correct name
        assertThat(DBController.myDBC, is(notNullValue()));
        assertThat(DBController.myDBC.getName(), is(dbName));

        // Attempt to close
        DBController.close();

        // myDBC should once again be null
        assertThat(DBController.myDBC, is(nullValue()));
    }
}