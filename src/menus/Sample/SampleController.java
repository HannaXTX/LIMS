package menus.Sample;

import database.Connector;
import database.Queries;
import database.UtilFunctions;
import entities.Sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonType.YES;

public class SampleController implements Initializable {




    @FXML
    private static Stage modifyStage;



    @FXML
    private TextField txtCode, txtName, txtStorage, txtTemperature;
    @FXML
    private DatePicker dpProdDate, dpExpDate;
    @FXML
    private ComboBox<String> cbSampleType;
    @FXML
    private Button btAddSample, btUpdateSample, btDeleteSample;
    @FXML
    private TableView<Sample> tbSample;
    @FXML
    private TableColumn<Sample, String>  colName, colProdDate, colExpDate, colStorage, colTemp, colType;

    @FXML
    private TableColumn<Sample, Integer> colCode;


    private ArrayList<Sample> sampleList = new ArrayList<>();

    @FXML
    SampleOperationController sampleOperationController = new SampleOperationController();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colCode.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty().asObject());
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colProdDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductionDate()));
        colExpDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpirationDate()));
        colStorage.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStorage()));
        colTemp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTemperature()));
        colType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSampleType()));

        try {
            fillTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTable() throws SQLException {
        String query = "SELECT * FROM SAMPLE";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sample sample = new Sample(
                        resultSet.getInt("SCode"),
                        resultSet.getString("Name"),
                        resultSet.getInt("Cid"),
                        resultSet.getString("ProductionDate"),
                        resultSet.getString("ExpirationDate"),
                        resultSet.getString("Storage"),
                        resultSet.getString("Temperature"),
                        resultSet.getString("IS_A")
                );

//                        Tid INT,
                sampleList.add(sample);
            }
            tbSample.setItems(FXCollections.observableList(sampleList));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyTable(javafx.event.ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btAddSample) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/Sample/SampleOperationController.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            menus.Sample.SampleOperationController  sampleOperationController = loader.getController();
            sampleOperationController.setSampleList(FXCollections.observableList(sampleList));
            sampleOperationController.setTableView(tbSample);

            modifyStage = new Stage();
            modifyStage.setScene(scene);
            modifyStage.show();
        }

        if (actionEvent.getSource() == btUpdateSample) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/Sample/SampleOperationController.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            menus.Sample.SampleOperationController sampleOperationController = loader.getController();
            sampleOperationController.setSelectedSample(tbSample.getSelectionModel().getSelectedItem());
            sampleOperationController.setSampleList(FXCollections.observableList(sampleList));
            sampleOperationController.setTableView(tbSample);

            modifyStage = new Stage();
            modifyStage.setScene(scene);
            modifyStage.show();
        }
    }
    @FXML
    public void deleteSample (ActionEvent actionEvent) throws SQLException {
        entities.Sample sample = tbSample.getSelectionModel().getSelectedItem();
        UtilFunctions.createAlert("CONFIRMATION", "Confirmation",
                "Are you sure you want to delete Sample " + sample.getName() + "?", YES).showAndWait().ifPresent(buttonType -> {
            if (buttonType == YES) {
                try {
                    Queries.deleteSample(sample, String.valueOf(sample.getCode()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tbSample.getItems().remove(sample);
                tbSample.refresh();
            }
        });
    }



    public static Stage getModifyStage() {
        return modifyStage;
    }


}
