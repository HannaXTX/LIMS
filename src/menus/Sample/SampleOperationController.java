package menus.Sample;

import database.Queries;
import database.UtilFunctions;
import entities.Employee;
import entities.Sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonType.YES;

public class SampleOperationController implements Initializable {

    public TextField tfName,tfTemperature,tfStorage;
    public DatePicker dpReceiveDate;

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
        tfName.setText(sample.getName());
        dpProdDate.setValue(LocalDate.parse(sample.getProductionDate()));
        dpExpDate.setValue(LocalDate.parse(sample.getExpirationDate()));
        tfStorage.setText(sample.getStorage());
        tfTemperature.setText(sample.getTemperature());
        cbSampleType.setValue(sample.getSampleType());
    }


    private int getNextCid() {
        return sampleList.stream().mapToInt(Sample::getCode).max().orElse(0) + 1;
    }

    @FXML
    public Sample saveSample() {
        try {
           // Parse text to int
            Sample newSample = new Sample(
                    getNextCid(),
                    tfName.getText(),
                    0, // Replace with actual Cid if needed
                    dpProdDate.getValue().toString(),
                    dpExpDate.getValue().toString(),
                    tfStorage.getText(),
                    tfTemperature.getText(),
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
            sample.setName(tfName.getText());
            sample.setProductionDate(dpProdDate.getValue().toString());
            sample.setExpirationDate(dpExpDate.getValue().toString());
            sample.setStorage(tfStorage.getText());
            sample.setTemperature(tfTemperature.getText());
            sample.setSampleType(cbSampleType.getValue());
            if (tableView != null) { // Check if tableView is initialized
                tableView.refresh();
            }
        }
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
                       tfName.getText(),
                       dpProdDate.getValue().toString(),
                       dpExpDate.getValue().toString(),
                       tfStorage.getText(),
                       tfTemperature.getText(),
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
