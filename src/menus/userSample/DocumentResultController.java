package menus.userSample;

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

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DocumentResultController implements Initializable {

    public DatePicker dpReceiveDate;
    public Label lblStatus;
    public TextField tfUnit;
    public TextField tfDescription;
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
            String query = "SELECT MAX(Rid) AS LastId FROM Result";

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
    public Result saveResult() {
        try {
            Result newResult = new Result(
                    getLastIdFromDatabase(),
                    sample.getCode(),
                    lblStatus.getText(),
                    tfUnit.getText(),
                    tfDescription.getText(),
                    dpDate.getValue().toString()
            );
            return newResult;
        } catch (NumberFormatException | SQLException e) {
            // Handle parsing error
            System.out.println("Error");

        }
        return null;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // cbSampleType.getItems().addAll("Human Food", "Animal feed", "Drinking Water", "Bottled water");
    }

    public void cancelEvent(ActionEvent actionEvent) {
        MySampleController.getModifyStage().close();
    }

    public void setSelectedSample(Sample sam) {
        this.sample = sam;
        setSampleData(sam);
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
