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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Data View");
        root.requestFocus();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
