package menus.tests;

import database.Connector;
import entities.Employee;
import entities.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    private TableView<Test> tvTest;

    @FXML
    private TableColumn<Test, Integer> colEId, colTestId;
    @FXML
    private TableColumn<Test, Double> colPrice;
    @FXML
    private TableColumn<Test, String> colTestName, colSCode;


    private ObservableList<Test> testList =  FXCollections.observableArrayList();



    public void modifyTable(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTestId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colTestName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        colSCode.setCellValueFactory(cellData -> cellData.getValue().scodeProperty());
        colEId.setCellValueFactory(cellData -> cellData.getValue().getEidProperty().asObject());
        try {
            fillTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        tvTest.setItems(testList);
    }


    public void fillTable() throws SQLException {
        String query = "SELECT * FROM test";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Test test = new Test(
                        resultSet.getInt("Tid"),
                        resultSet.getString("Name"),
                        resultSet.getString("Scode"),
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
}

