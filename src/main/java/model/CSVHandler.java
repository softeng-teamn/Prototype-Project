package model;

import com.sun.javafx.binding.StringFormatter;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {

    public static boolean exportFile(ArrayList<Node> nodes, String path) {
        try (PrintWriter writer = new PrintWriter(new File(path + ".csv"), "UTF-8")) {
            String line = "nodeID,xcoord,ycoord,floor,building,nodeType,longName,shortName\n";
            writer.write(line);

            for (Node node: nodes) {
                line = StringFormatter.format("%s,%s,%s,%s,%s,%s,%s,%s\n", node.getNodeID(), node.getXcoord(), node.getYcoord(), node.getFloor(), node.getBuilding(), node.getNodeType(), node.getLongName(), node.getShortName()).getValue();

                writer.write(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<Node> importFile(InputStream in) throws IOException {
        ArrayList<Node> nodes = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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
