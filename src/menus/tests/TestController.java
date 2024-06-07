package menus.tests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestController {
    @FXML
    private TableView<Test> testTableView;
    @FXML
    private TableColumn<Test, Integer> tidColumn;
    @FXML
    private TableColumn<Test, String> nameColumn;
    @FXML
    private TableColumn<Test, String> scodeColumn;
    @FXML
    private TableColumn<Test, Integer> eidColumn;
    @FXML
    private TableColumn<Test, Float> priceColumn;

    private ObservableList<Test> testList;

    public void initialize() {
        tidColumn.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        scodeColumn.setCellValueFactory(new PropertyValueFactory<>("SCode"));
        eidColumn.setCellValueFactory(new PropertyValueFactory<>("EID"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        testList = FXCollections.observableArrayList();

        testTableView.setItems(testList);
    }


}
