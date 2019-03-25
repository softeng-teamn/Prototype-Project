package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Controller {

    @FXML
    private JFXListView id, x, y, floor, building, type, longName, shortName;

    @FXML
    private JFXButton editBtn, exportBtn;

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

    @FXML
    public final void exportAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../export.fxml"));
        JFXButton btn = (JFXButton) e.getSource();
        Stage mainStage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setTitle("Export to CSV");
        root.requestFocus();
        mainStage.show();
    }

    @FXML
    public void initialize() {
        System.out.println("i was just initialized");
        // show the nodes

//        ArrayList<Node> nodes = DBController.myDBC.getAllNodes();
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("nid", "2", "bmT", "nType", "long", "short",12, 34));
        nodes.add(new Node("nid2","2", "bmH", "nTypess", "longer", "shortr", 3, 7));

        for (Node node : nodes) {
            id.getItems().add(node.getNodeID());
            x.getItems().add(node.getXcoord());
            y.getItems().add(node.getYcoord());
            floor.getItems().add(node.getFloor());
            building.getItems().add(node.getBuilding());
            type.getItems().add(node.getNodeType());
            longName.getItems().add(node.getLongName());
            shortName.getItems().add(node.getShortName());
        }

    }



}
