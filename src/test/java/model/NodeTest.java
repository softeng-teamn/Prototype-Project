package model;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NodeTest {
    @Test
    @Category(FastTest.class)
    public void validateID() {
        // No preconditions - this is testing a stateless method

        String values[] = {"BCONF00102", "BSTAI00602", "", null, "BCONF0010", "BSTAI006", "2343"};
        boolean expectedResults[] = {true, true, false, false, false, false, false};

        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            boolean expected = expectedResults[i];

            assertThat(value, is(expected));
        }
    }

    @Test
    @Category(FastTest.class)
    public void validateCoordinate() {
        // No preconditions - this is testing a stateless method

        int values[] = {-1, 0, 1};
        boolean expectedResults[] = {false, true, true};

        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            boolean expected = expectedResults[i];

            assertThat(value, is(expected));
        }
    }

    @Test
    @Category(FastTest.class)
    public void validateFloor() {
        // No preconditions - this is testing a stateless method

        String values[] = {"1", "2", "3", "L1", "L2", "G", "", null, "4", "ABC"};
        boolean expectedResults[] = {true, true, true, true, true, true, false, false, false, false};

        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            boolean expected = expectedResults[i];

            assertThat(value, is(expected));
        }
    }

    @Test
    @Category(FastTest.class)
    public void validateNodeType() {
        // No preconditions - this is testing a stateless method

        String values[] = {"CONF", "HALL", "DEPT", "INFO", "LABS", "REST", "SERV", "STAI", "", null, "ABCD"};
        boolean expectedResults[] = {true, true, true, true, true, true, true, true, false, false, false};

        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            boolean expected = expectedResults[i];

            assertThat(value, is(expected));
        }
    }
}
