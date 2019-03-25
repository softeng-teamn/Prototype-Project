package model;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import static org.apache.commons.io.FileUtils.contentEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CSVHandlerTest {
    @Test
    @Category(FastTest.class)
    public void exportFile() throws IOException {
        ArrayList<Node> data;
        String path;
        File expected;

        path = CSVHandlerTest.class.getResource("../export_test_empty.csv").getFile();
        expected = new File(path);
        data = new ArrayList<>();
        boolean res = CSVHandler.exportFile(data, "export_test_empty_result.csv");

        assertThat(res, is(true));
        contentEquals(expected, new File("./export_test_empty_result.csv"));





        path = this.getClass().getResource("../export_test_1.csv").getFile();
        expected = new File(path);
        data = new ArrayList<>();
        data.add(new Node("BCONF00102",
                "2",
                "45 Francis",
                "CONF",
                "Duncan Reid Conference Room",
                "Conf B0102",
                2150,1025));
        res = CSVHandler.exportFile(data, "export_test_1_result.csv");

        assertThat(res, is(true));
        contentEquals(expected, new File("./export_test_1_result.csv"));






        path = this.getClass().getResource("../export_test_2.csv").getFile();
        expected = new File(path);
        data = new ArrayList<>();
        data.add(new Node("BHALL03802", "2","45 Francis", "HALL", "Hallway Intersection 38 Level 2", "Hallway B3802", 2279,786));
        data.add(new Node("BDEPT00202","2","45 Francis","DEPT","Oral Medicine and Dentistry","DEPT B0202",2166,1039));
        data.add(new Node("BDEPT00302", "2","45 Francis","DEPT", "Lee Bell Breast Center" , "DEPT B0302", 2385,753));
        res = CSVHandler.exportFile(data, "export_test_1_result.csv");

        assertThat(res, is(true));
        contentEquals(expected, new File("./export_test_1_result.csv"));
    }

    @Test
    @Category(FastTest.class)
    public void importFile() throws IOException {
        String testFile;
        ArrayList<Node> result;
        ArrayList<Node> expected;

        testFile = "../PrototypeNodes_testset_empty.csv";
        result = CSVHandler.importFile(testFile);
        expected = new ArrayList<>();

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(expected.size()));
        assertThat(result, is(expected));




        testFile = "../PrototypeNodes_testset_1.csv";
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
        assertThat(result.size(), is(expected.size()));
        for(int i = 0; i < result.size(); i++) {
            assertThat(result.get(i), is(expected.get(i)));
        }






        testFile = "../PrototypeNodes_testset_2.csv";
        result = CSVHandler.importFile(testFile);
        expected = new ArrayList<>();
        expected.add(new Node("BHALL03802", "2","45 Francis", "HALL", "Hallway Intersection 38 Level 2", "Hallway B3802", 2279,786));
        expected.add(new Node("BDEPT00202","2","45 Francis","DEPT","Oral Medicine and Dentistry","DEPT B0202",2166,1039));
        expected.add(new Node("BDEPT00302", "2","45 Francis","DEPT", "Lee Bell Breast Center" , "DEPT B0302", 2385,753));

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(expected.size()));
        assertThat(result, is(expected));
    }
}
