import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        readData();
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("HelloSwingNode Sample");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void readData() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("PrototypeNodes.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        // Setup vars
        String nodeID, building, nodeType, longName, shortName;
        int xcoord, ycoord, floor;
        // Read line
        String strLine = reader.readLine();
        // Read line again to neglect heading line
        for (String line = strLine; (line = reader.readLine()) != null;) {
            System.out.println(line);
            List<String> attributes = Arrays.asList(line.split(","));
            nodeID = attributes.get(0);
            xcoord = Integer.parseInt(attributes.get(1));
            ycoord = Integer.parseInt(attributes.get(2));
            floor = Integer.parseInt(attributes.get(3));
            building = attributes.get(4);
            nodeType = attributes.get(5);
            longName = attributes.get(6);
            shortName = attributes.get(7);

            // CREATE THE NODE
            System.out.println(line);
        }


    }

}
