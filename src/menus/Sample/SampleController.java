package menus.Sample;

import database.Connector;
import entities.Sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    @FXML
    private TableView<Sample> tbSample;

    @FXML
    private TableColumn<Sample, String> colCode;

    @FXML
    private TableColumn<Sample, String> colName, colProdDate, colExpDate, colStorage, colTemp, colType;

    @FXML
    private GridPane gpEmployee;

    private ArrayList<Sample> sampleList = new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colProdDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductionDate()));
        colExpDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpirationDate()));
        colStorage.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStorage()));
        colTemp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTemperature()));
        colType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSampleType()));

        try {
            fillTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTable() throws SQLException {
        String query = "SELECT * FROM SAMPLE";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sample sample = new Sample(
                        resultSet.getString("SCode"),
                        resultSet.getString("Name"),
                        resultSet.getInt("CID"), // Use getInt for CID
                        resultSet.getString("ProductionDate"),
                        resultSet.getString("ExpirationDate"),
                        resultSet.getString("Storage"),
                        resultSet.getString("Temperature"),
                        resultSet.getString("IS_A")
                );
                sampleList.add(sample);
            }
            tbSample.setItems(FXCollections.observableList(sampleList));
        }
    }

    public void modifyTable() {
        // Implementation
    }
}
