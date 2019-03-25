package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {

    public static boolean exportFile(ArrayList<Node> nodes) {
        return false;
    }

    public static ArrayList<Node> importFile(String fileName) throws IOException {
        ArrayList<Node> nodes = new ArrayList<>();

        InputStream inputStream = CSVHandler.class.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        // Setup vars
        String nodeID, building, nodeType, longName, shortName, floor;
        int xcoord, ycoord;
        // Read line
        String strLine = reader.readLine();
        // Read line again to neglect heading line
        for (String line = strLine; (line = reader.readLine()) != null;) {

            List<String> attributes = Arrays.asList(line.split(","));
            nodeID = attributes.get(0);
            xcoord = Integer.parseInt(attributes.get(1));
            ycoord = Integer.parseInt(attributes.get(2));
            floor = attributes.get(3);
            building = attributes.get(4);
            nodeType = attributes.get(5);
            longName = attributes.get(6);
            shortName = attributes.get(7);

            nodes.add(new Node(nodeID, floor, building, nodeType, longName, shortName, xcoord, ycoord));
        }

        reader.close();

        return nodes;
    }
}
