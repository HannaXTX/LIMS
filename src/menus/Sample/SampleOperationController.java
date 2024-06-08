package menus.Sample;

import database.Queries;
import database.UtilFunctions;
import entities.Sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import menus.employees.EmpController;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonType.YES;

public class SampleOperationController implements Initializable {

    @FXML
    private TextField txtCode, txtName, txtStorage, txtTemperature;
    @FXML
    private DatePicker dpProdDate, dpExpDate;
    @FXML
    private ComboBox<String> cbSampleType;
    @FXML
    private Button btnSave, btnCancel;

    private Sample sample;

    private ObservableList<Sample> sampleList;
    private TableView<Sample> tableView;

    public void setSampleList(ObservableList<Sample> sampleList) {
        this.sampleList = sampleList;
    }

    public void setTableView(TableView<Sample> tableView) {
        this.tableView = tableView;
    }

    public void setSampleData(Sample sample) {
        this.sample = sample;
        txtCode.setText(Integer.toString(sample.getCode()));
        txtName.setText(sample.getName());
        dpProdDate.setValue(LocalDate.parse(sample.getProductionDate()));
        dpExpDate.setValue(LocalDate.parse(sample.getExpirationDate()));
        txtStorage.setText(sample.getStorage());
        txtTemperature.setText(sample.getTemperature());
        cbSampleType.setValue(sample.getSampleType());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSampleType.getItems().addAll("Human Food", "Animal feed", "Drinking Water", "Bottled water");
    }

    public void cancelEvent(ActionEvent actionEvent) {
        menus.Sample.SampleController.getModifyStage().close();
    }

    public void setSelectedSample(Sample sample) {
        this.sample = sample;
        setSampleData(sample);
    }

    @FXML
    public Sample saveSample() {
        try {
            int code = Integer.parseInt(txtCode.getText()); // Parse text to int
            Sample newSample = new Sample(
                    code, // Use parsed int code
                    txtName.getText(),
                    0, // Replace with actual Cid if needed
                    dpProdDate.getValue().toString(),
                    dpExpDate.getValue().toString(),
                    txtStorage.getText(),
                    txtTemperature.getText(),
                    cbSampleType.getValue()
            );
            if (sampleList != null) { // Check if sampleList is initialized
                sampleList.add(newSample);
                tableView.refresh();
            }
            return newSample;
        } catch (NumberFormatException e) {
            // Handle parsing error
            e.printStackTrace();
            return null;
        }
    }

    public void updateSample() {
        if (sample != null) { // Check if sample is initialized
            sample.setCode(Integer.parseInt(txtCode.getText()));
            sample.setName(txtName.getText());
            sample.setProductionDate(dpProdDate.getValue().toString());
            sample.setExpirationDate(dpExpDate.getValue().toString());
            sample.setStorage(txtStorage.getText());
            sample.setTemperature(txtTemperature.getText());
            sample.setSampleType(cbSampleType.getValue());
            if (tableView != null) { // Check if tableView is initialized
                tableView.refresh();
            }
        }
    }


    public void deleteSample(ActionEvent actionEvent) throws SQLException {
        if (tableView != null) { // Check if tableView is initialized
            Sample sample = tableView.getSelectionModel().getSelectedItem();
            if (sample != null) { // Check if sample is selected
                UtilFunctions.createAlert("CONFIRMATION", "Confirmation",
                        "Are you sure you want to delete Sample " + sample.getName() + "?", YES).showAndWait().ifPresent(buttonType -> {
                    if (buttonType == YES) {
                        try {
                            Queries.deleteSample(sample, String.valueOf(sample.getCode()));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        tableView.getItems().remove(sample);
                        tableView.refresh();
                    }
                });
            }
        }
    }

    public void addEvent(ActionEvent actionEvent) throws SQLException {
       try {

           if (sample == null) {
               //            String query = Queries.addEmployeetoDB(tfName.getText(), tfSSN.getText(), tfAddress.getText(), dpDate.getValue().toString(), cbMajor.getValue(), tfPhoneNumber.getText(), tfEmail.getText());
               //            Statement statement = Connector.getCon().createStatement();
               //            statement.executeUpdate(query);

               SampleController.getModifyStage().close();
               saveSample();
           } else {
               updateSample();
               System.out.println(sample.getCode());
               Queries.updateSampleInDB(
                       String.valueOf(sample.getCode()),
                       txtName.getText(),
                       dpProdDate.getValue().toString(),
                       dpExpDate.getValue().toString(),
                       txtStorage.getText(),
                       txtTemperature.getText(),
                       cbSampleType.getValue()
               );


               SampleController.getModifyStage().close();


           }

       } catch (Exception ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }




}
