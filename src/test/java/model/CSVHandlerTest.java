package model;

import org.junit.Test;
import junitx.framework.*;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class CSVHandlerTest {
    @Test
    @Category(FastTest.class)
    public void exportFile() {
    }

    @Test
    @Category(FastTest.class)
    public void importFile() {
        String testFile;
        ArrayList<Node> result;
        ArrayList<Node> expected;

        testFile = "ProtypeNodes_testset_empty.csv";
        result = CSVHandler.importFile(testFile);
        expected = new ArrayList<>();

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));
        assertThat(result, is(expected));




        testFile = "ProtypeNodes_testset_1.csv";
        result = CSVHandler.importFile(testFile);
        expected = new ArrayList<>();
        expected.add(new Node("BCONF00102",
                "2",
                "45 Francis",
                "CONF",
                "Duncan Reid Conference Room",
                "Conf B0102",
                2150,1025));

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result, is(expected));






        testFile = "ProtypeNodes_testset_2.csv";
        result = CSVHandler.importFile(testFile);
        expected = new ArrayList<>();
        expected.add(new Node("BHALL03802", "2","45 Francis", "HALL", "Hallway Intersection 38 Level 2", "Hallway B3802", 2279,786));
        expected.add(new Node("BDEPT00202","2","45 Francis","DEPT","Oral Medicine and Dentistry","DEPT B0202",2166,1039));
        expected.add(new Node("BDEPT00302", "2","45 Francis","DEPT", "Lee Bell Breast Center" , "DEPT B0302", 2385,753));

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(3));
        assertThat(result, is(expected));
    }
}
