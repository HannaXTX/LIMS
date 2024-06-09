package menus.userSample;

import com.mysql.jdbc.Connection;
import database.Connector;
import database.Queries;
import database.UtilFunctions;
import entities.Result;
import entities.Sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import menus.results.ResultController;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DocumentResultController implements Initializable {

    public DatePicker dpReceiveDate;
    public Label lblStatus;
    public Label tfUnit;
    public Label tfDescription;
    public DatePicker dpDate;
    public Label lblthisSCode;

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
        lblthisSCode.setText("" + sample.getCode());
    }


//    private int getNextId() {
//        return sampleList.stream().mapToInt(Result::getResId).max().orElse(0) + 1;
//    }


    private int getLastIdFromDatabase() throws SQLException {
        try {
            String query = "SELECT MAX(ResId) AS LastId FROM Result";

            Statement statement = Connector.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);{
                if (resultSet.next()) {
                    return resultSet.getInt("LastId");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if no result or an error occurs
    }

    @FXML
    public void saveResult() {
        try {
            Result newResult = new Result(
                    getLastIdFromDatabase(),
                    sample.getCode(),
                    lblStatus.getText(),
                    tfUnit.getText(),
                    tfDescription.getText(),
                    dpDate.getValue().toString()
            );


            if (sampleList != null) { // Check if sampleList is initialized
                // sampleList.remove(sample);
                tableView.refresh();
            }

        } catch (NumberFormatException e) {
            // Handle parsing error
            System.out.println("Error");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSampleType.getItems().addAll("Human Food", "Animal feed", "Drinking Water", "Bottled water");
    }

    public void cancelEvent(ActionEvent actionEvent) {
        MySampleController.getModifyStage().close();
    }

    public void setSelectedSample(Sample sample) {
        this.sample = sample;
        setSampleData(sample);
    }


    public void addEvent(ActionEvent actionEvent) throws SQLException {
        try {

            String query = Queries.addResultToDB( lblStatus.getText(), tfDescription.getText(), dpDate.getValue().toString(), Integer.parseInt(lblthisSCode.getText()),tfUnit.getText());
            Statement statement = Connector.getCon().createStatement();
            statement.executeUpdate(query);


            MySampleController.getModifyStage().close();
            saveResult();

        } catch (
                Exception ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }


}
