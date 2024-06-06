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
    private TableColumn<Sample, Integer> colCode;
    @FXML
    private TableColumn<Sample, String> colName, colRecDate, colProdDate, colExpDate, colTemp, colStorage,colType;

    @FXML
    private GridPane gpEmployee;

    @FXML
    ArrayList<Sample> sampleList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colRecDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReceiveDate()));
        colProdDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductionDate()));
        colExpDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpirationDate()));
        colTemp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTemperature()));
        colStorage.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStorage()));
//        colType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get()));
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
                        resultSet.getString("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Receive_Date"),
                        resultSet.getString("Production_Date"),
                        resultSet.getString("Expiration_Date"),
                        resultSet.getString("Temperature"),
                        resultSet.getString("Storage")
                );
                sampleList.add(sample);
            }
            tbSample.setItems(FXCollections.observableList(sampleList));
            resultSet.close();
        }
    }

    public int SamplesCounter() {
        return sampleList.size();
    }

    // for pie chart to show the num of Statistics in a month
    public int numOfSamplesInMonth(ArrayList<Sample> samples, String month) {
        int count = 0;
        for (Sample sample : samples) {
            String receiveDate = sample.getReceiveDate();
            String sampleMonth = receiveDate.split("-")[1]; // Assuming 'receiveDate' is in the format "YYYY-MM-DD"

            if (sampleMonth.equals(month)) {
                count++;
            }
        }
        return count;
    }

    // Method to count samples by year
    public int numOfSamplesInYear(ArrayList<Sample> samples, String year) {
        int count = 0;
        for (Sample sample : samples) {
            String receiveDate = sample.getReceiveDate();
            String sampleYear = receiveDate.split("-")[0]; // Assuming 'receiveDate' is in the format "YYYY-MM-DD"

            if (sampleYear.equals(year)) {
                count++;
            }
        }
        return count;
    }

    // Method to count samples by storage type
    public int countSamplesByStorage(ArrayList<Sample> samples, String storageType) {
        int count = 0;
        for (Sample sample : samples) {
            if (sample.getStorage().equalsIgnoreCase(storageType)) {
                count++;
            }
        }
        return count;
    }


    // Method to count samples that are expired
    public int countExpiredSamples(ArrayList<Sample> samples, String currentDate) {
        int count = 0;
        for (Sample sample : samples) {
            if (sample.getExpirationDate().compareTo(currentDate) < 0) {
                count++;
            }
        }
        return count;
    }

    // Method to find the sample with the earliest production date
    public Sample earliestProductionSample(ArrayList<Sample> samples) {
        Sample earliestSample = null;
        for (Sample sample : samples) {
            if (earliestSample == null || sample.getProductionDate().compareTo(earliestSample.getProductionDate()) < 0) {
                earliestSample = sample;
            }
        }
        return earliestSample;
    }


    public void modifyTable() {

    }
}
