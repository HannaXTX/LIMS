package menus.tests;

import database.Connector;
import database.Queries;
import database.UtilFunctions;
import entities.Test;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonType.YES;

public class TestController implements Initializable {

    @FXML
    private TableView<Test> tvTest;
    @FXML
    private TableColumn<Test, Integer> colEId, colTestId;
    @FXML
    private TableColumn<Test, Double> colPrice;
    @FXML
    private TableColumn<Test, String> colTestName;
    @FXML
    private Button btAdd, btUpdate, btDelete;

    private static Stage modifyStage;

    private ObservableList<Test> testList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTestId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colTestName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        colEId.setCellValueFactory(cellData -> cellData.getValue().eidProperty().asObject());

        try {
            fillTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTable() throws SQLException {
        String query = "SELECT * FROM test";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Test test = new Test(
                        resultSet.getInt("Tid"),
                        resultSet.getString("Name"),
                        resultSet.getInt("EID"),
                        resultSet.getDouble("price")
                );
                testList.add(test);
            }
            tvTest.setItems(FXCollections.observableList(testList));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyTable(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btAdd) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/tests/TestOperationController.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            TestOperationController testOperationController = loader.getController();
            testOperationController.setTestList(FXCollections.observableList(testList));
            testOperationController.setTableView(tvTest);

            modifyStage = new Stage();
            modifyStage.setScene(scene);
            modifyStage.show();
        }

        if (actionEvent.getSource() == btUpdate) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/tests/TestOperationController.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                TestOperationController testOperationController = loader.getController();
                testOperationController.setSelectedTest(tvTest.getSelectionModel().getSelectedItem());
                testOperationController.setTestList(FXCollections.observableList(testList));
                testOperationController.setTableView(tvTest);

                modifyStage = new Stage();
                modifyStage.setScene(scene);
                modifyStage.show();
            } catch (Exception ex) {
                UtilFunctions.createAlert("ERROR", "No Record Selected", "Please select a record to update", null).show();
                ex.printStackTrace();
            }
        }

        // If the actionEvent source is neither btAdd nor btUpdate, then do nothing
    }


    public void deleteTest(ActionEvent actionEvent) throws SQLException {
        Test test = tvTest.getSelectionModel().getSelectedItem();
        UtilFunctions.createAlert("CONFIRMATION", "Confirmation",
                "Are you sure you want to Delete Test " + test.getName() + "?", YES).showAndWait().ifPresent(buttonType -> {
            if (buttonType == YES) {
                try {
                    Queries.deleteTest(test, test.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tvTest.getItems().remove(test);
                tvTest.refresh();
            }
        });
    }

    public static Stage getModifyStage() {
        return modifyStage;
    }


}
