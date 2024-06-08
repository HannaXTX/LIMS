package menus.tests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import entities.Test; // Ensure this import is correct based on your package structure

public class TestController {
    @FXML
    private TableView<Test> testTableView;
    @FXML
    private TableColumn<Test, String> tidColumn;
    @FXML
    private TableColumn<Test, String> nameColumn;
    @FXML
    private TableColumn<Test, String> scodeColumn;
    @FXML
    private TableColumn<Test, Integer> eidColumn;
    @FXML
    private TableColumn<Test, Double> priceColumn;

    private ObservableList<Test> testList;

    public void initialize() {
        tidColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        scodeColumn.setCellValueFactory(cellData -> cellData.getValue().scodeProperty());
        eidColumn.setCellValueFactory(cellData -> cellData.getValue().eidProperty().asObject());


        testList = FXCollections.observableArrayList();
        testTableView.setItems(testList);


    }


    }

