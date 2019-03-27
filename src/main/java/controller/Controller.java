package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

// save ANY change to DB

public class Controller {

    @FXML
    private TableView<Node> table_info;

    @FXML
    private TableColumn<Node, String> col_short, col_id, col_building, col_floor, col_long, col_type, col_x, col_y;

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
        loadData();
    }

    private void loadData() {

        col_id.setCellValueFactory(new PropertyValueFactory<>("nodeID"));
        col_floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        col_building.setCellValueFactory(new PropertyValueFactory<>("building"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("nodeType"));
        col_long.setCellValueFactory(new PropertyValueFactory<>("longName"));
        col_short.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        col_x.setCellValueFactory(new PropertyValueFactory<>("xcoord"));
        col_y.setCellValueFactory(new PropertyValueFactory<>("ycoord"));


        ObservableList<Node> nodes = FXCollections.observableArrayList();
        ArrayList<Node> cleanNodes = DBController.getMyDBC().getAllNodes();
        nodes.addAll(cleanNodes);

        table_info.setItems(nodes);
        table_info.setEditable(true);
    }



}
