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
import model.Node;

import java.io.IOException;
import java.util.ArrayList;

public class ExportController {
    @FXML
    private JFXButton viewBtn, editBtn, exportChanges, saveBtn;

    @FXML
    private JFXTextField pathFinder;

    @FXML
    public final void saveAction(ActionEvent e) {
//        ArrayList<Node> allNodes = DBController.myDBC.getAllNodes();
        ArrayList<Node> allNodes = new ArrayList<Node>();
        allNodes.add(new Node("nid", "2", "bmT", "nType", "long", "short",12, 34));
        allNodes.add(new Node("nid2","2", "bmH", "nTypess", "longer", "shortr", 3, 7));

        CSVHandler.exportFile(allNodes, pathFinder.getText());
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
