import controller.DBController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CSVHandler;
import model.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Data View");
        root.requestFocus();
        stage.show();
    }

    @Override
    public void stop() {
        DBController.getMyDBC().dropAll();
        DBController.close();
        System.out.println("Database Closed");
    }

    public static void main(String[] args) throws IOException {
        startDB();
        launch();
    }

    public static void startDB() throws IOException {
        DBController.init();
//        DBController.myDBC.dropAll();
        ArrayList<Node> nodes = CSVHandler.importFile( Main.class.getResourceAsStream("PrototypeNodes.csv"));
        for (Node n : nodes) {
            DBController.getMyDBC().insertNode(n);
        }
        System.out.println("Database Started");
    }

}
