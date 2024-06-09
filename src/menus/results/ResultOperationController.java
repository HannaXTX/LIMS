package menus.results;

import database.Connector;
import database.Queries;
import database.UtilFunctions;
import entities.Employee;
import entities.Result;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ResultOperationController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private TextField tfSCode, tfStatus, tfDescription, tfUnit;
    @FXML
    private ComboBox<String> cbStatus;
    @FXML
    private DatePicker dpDate;
    @FXML
    private Button btDone, btCancel;

    private Result res;

    private ObservableList<Result> resultsList;
    private TableView<Result> tableView;


    public void setTableView(TableView<Result> tableView) {
        this.tableView = tableView;
    }


    private int getNextId() {
        return resultsList.stream().mapToInt(Result::getResId).max().orElse(0) + 1;
    }

    @FXML
    public Result saveResult() {
        try {
            SimpleIntegerProperty intSCodeProperty = new SimpleIntegerProperty();
            intSCodeProperty.bind(Bindings.createIntegerBinding(() ->
                    Integer.parseInt(tfSCode.getText()), tfSCode.textProperty()));

            Result newResult = new Result(
                    getNextId(),
                    intSCodeProperty.get(),
                    tfStatus.getText(),
                    tfUnit.getText(),
                    tfDescription.getText(),
                    dpDate.getValue().toString()
            );

            resultsList.add(newResult);
            tableView.refresh();
            return newResult;
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input for SCode");
            e.printStackTrace();
            // Handle the exception as needed
            return null; // Or some default result
        }
    }



    public void setResData(Result res) {
        tfSCode.setText(String.valueOf(res.getResId()));
        tfStatus.setText(res.getStatus());
        dpDate.setValue(LocalDate.parse(res.getDate()));
        tfDescription.setText(res.getDescription());
        tfUnit.setText(res.getUnit());
    }



    public void updateResult() {
        try {
            // Update Result properties
            res.setSCode(Integer.parseInt(tfSCode.getText()));
            res.setStatus(tfStatus.getText());
            res.setDate(dpDate.getValue().toString()); // Assuming getDate() returns a LocalDate
            res.setDescription(tfDescription.getText());
            res.setUnit(tfUnit.getText());

            // Refresh TableView
            tableView.refresh();
        } catch (NumberFormatException e) {
            // Handle parsing error for SCode
            System.err.println("Error: Invalid input for SCode");
            e.printStackTrace(); // You might want to log or handle this differently
        } catch (Exception e) {
            // Handle validation errors or any other exceptions
            e.printStackTrace(); // You might want to log or handle this differently
        }
    }




    public void cancelEvent(ActionEvent actionEvent) {
        ResultController.getModifyStage().close();
    }

    public void setSelectedEmployeeR(Result result) {
        res = result;
        setResData(res);
    }


    public void addEvent(ActionEvent actionEvent) throws SQLException {
        try {
            if (res == null) {
                            String query = Queries.addResultToDB(getNextId(),tfSCode.getText(), tfStatus.getText(), tfUnit.getText(), tfDescription.getText(), dpDate.getValue().toString());
                            Statement statement = Connector.getCon().createStatement();
                            statement.executeUpdate(query);

                ResultController.getModifyStage().close();
                saveResult();
            } else {
                updateResult();
                System.out.println(res.getResId());
                Queries.updateResultInDB(res.getResId(), res.getSCode(),
                        res.getStatus(), res.getUnit(), res.getDescription(), res.getDate());


                ResultController.getModifyStage().close();


            }


        } catch (Exception ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }

    public void setResultList(ObservableList<Result> resultsList) {
        this.resultsList = resultsList;
    }

    public void setSelectedResult(Result result) {
        res = result;
        setResData(res);
    }

    public ObservableList<Result> getResultsList() {
        return resultsList;
    }

    public void setResultsList(ObservableList<Result> resultsList) {
        this.resultsList = resultsList;
    }
}
