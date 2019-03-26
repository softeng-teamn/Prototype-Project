package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CSVHandler;

import java.io.IOException;

public class ExportController {
    @FXML
    private JFXButton viewBtn, editBtn, exportChanges, saveBtn;

    @FXML
    private JFXTextField pathFinder;

    @FXML
    public final void saveAction(ActionEvent e) {
        CSVHandler.exportFile(DBController.myDBC.getAllNodes(), pathFinder.getText());
        Stage stage =  (Stage) pathFinder.getScene().getWindow();
        stage.close();
    }

    @FXML
    public final void exAction(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../path.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Save Path");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(exportChanges.getScene().getWindow());
        stage.showAndWait();
//        CSVHandler.exportFile(DBController.myDBC.getAllNodes(), )
    }

    @FXML
    public final void viewAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../home.fxml"));
        JFXButton btn = (JFXButton) e.getSource();
        Stage mainStage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setTitle("Data View");
        root.requestFocus();
        mainStage.show();
    }

    @FXML
    public final void editAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../editor.fxml"));
        JFXButton btn = (JFXButton) e.getSource();
        Stage mainStage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setTitle("Editor");
        root.requestFocus();
        mainStage.show();
    }
}
