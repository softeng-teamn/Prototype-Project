package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.Node;

import java.io.IOException;
import java.util.ArrayList;

public class EditController {

    @FXML
    private JFXButton viewBtn, exportBtn, saveBtn;

    @FXML
    private JFXListView id, x, y, floor, building, type, longName, shortName;

    @FXML
    private TableView<Node> table_info;

    @FXML
    private TableColumn<Node, String> col_short, col_id, col_building, col_floor, col_long, col_type;

    @FXML
    private TableColumn<Node, Integer> col_x, col_y;


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
    public final void editCommit(ActionEvent e) {
        System.out.println("im committed to you");
    }

    @FXML
    public void initialize() {
        initTable();
        loadData();
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("nodeID"));
        col_floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        col_building.setCellValueFactory(new PropertyValueFactory<>("building"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("nodeType"));
        col_long.setCellValueFactory(new PropertyValueFactory<>("longName"));
        col_short.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        col_x.setCellValueFactory(new PropertyValueFactory<>("xcoord"));
        col_y.setCellValueFactory(new PropertyValueFactory<>("ycoord"));

        editableCols();
    }

    private void editableCols() {
        col_id.setCellFactory(TextFieldTableCell.forTableColumn());

        col_id.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setNodeID(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });


        col_floor.setCellFactory(TextFieldTableCell.forTableColumn());

        col_floor.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setFloor(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });


        col_building.setCellFactory(TextFieldTableCell.forTableColumn());

        col_building.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setBuilding(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });


        col_type.setCellFactory(TextFieldTableCell.forTableColumn());

        col_type.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setNodeType(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });


        col_long.setCellFactory(TextFieldTableCell.forTableColumn());

        col_long.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setLongName(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });


        col_short.setCellFactory(TextFieldTableCell.forTableColumn());

        col_short.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setShortName(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });


        col_x.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        col_x.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setXcoord(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });

        col_y.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        col_y.setOnEditCommit(e -> {
            Node n = e.getTableView().getItems().get(e.getTablePosition().getRow());
            n.setYcoord(e.getNewValue());
            DBController.myDBC.updateNode(n);
        });
    }

    private void loadData() {
        ObservableList<Node> nodes = FXCollections.observableArrayList();
        ArrayList<Node> cleanNodes = DBController.myDBC.getAllNodes();
        nodes.addAll(cleanNodes);
//        nodes.add(new Node("nid", "2", "bmT", "nType", "long", "short",12, 34));
//        nodes.add(new Node("nid2","2", "bmH", "nTypess", "longer", "shortr", 3, 7));

        table_info.setItems(nodes);
        table_info.setEditable(true);
    }

}
