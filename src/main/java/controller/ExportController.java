package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExportController {
    @FXML
    private JFXButton viewBtn, editBtn;

    @FXML
    public final void viewAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../home.fxml"));
        JFXButton btn = (JFXButton) e.getSource();
        Stage mainStage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    public final void editAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../editor.fxml"));
        JFXButton btn = (JFXButton) e.getSource();
        Stage mainStage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
