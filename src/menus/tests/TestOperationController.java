package menus.tests;

import database.Queries;
import database.UtilFunctions;
import entities.Test;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestOperationController implements Initializable {

    @FXML
    private TextField tfTestId, tfTestName, tfPrice;
    @FXML
    private ComboBox<String> cbSCode, cbEId;
    @FXML
    private Button btSave, btCancel;

    private Test test;

    private ObservableList<Test> testList;
    private TableView<Test> tableView;

    public void setTestList(ObservableList<Test> testList) {
        this.testList = testList;
    }

    public void setTableView(TableView<Test> tableView) {
        this.tableView = tableView;
    }

    private int getNextTestId() {
        return testList.stream().mapToInt(Test::getId).max().orElse(0) + 1;
    }



    public void setTestData(Test test) {
        tfTestId.setText(test.getId()+"");
        tfTestName.setText(test.getName());
        cbSCode.setValue(test.getScode());
        cbEId.setValue(String.valueOf(test.getEid()));
        tfPrice.setText(String.valueOf(test.getPrice()));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate with actual SCode and EId values from the database
        cbSCode.getItems().addAll("SCode1", "SCode2", "SCode3");
        cbEId.getItems().addAll("1", "2", "3");
    }

    public void cancelOperation(ActionEvent actionEvent) {
        TestController.getModifyStage().close();
    }

    @FXML
    public void saveTest(ActionEvent actionEvent) {
        try {
            if (test == null) {
                Test newTest = new Test(
                        getNextTestId(),
                        tfTestName.getText(),
                        cbSCode.getValue(),
                        Integer.parseInt(cbEId.getValue()),
                        Double.parseDouble(tfPrice.getText())
                );
                testList.add(newTest);
            } else {
                test.setName(tfTestName.getText());
                test.setScode(cbSCode.getValue());
                test.setEid(Integer.parseInt(cbEId.getValue()));
                test.setPrice(Double.parseDouble(tfPrice.getText()));
            }
            tableView.refresh();
        } catch (NumberFormatException | NullPointerException ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }

    public void setSelectedTest(Test test) {
        this.test = test;
        setTestData(test);
    }


    public void addEvent(ActionEvent actionEvent) throws SQLException {
        try {
            Test newTest = new Test(
                    getNextTestId(),
                    tfTestName.getText(),
                    cbSCode.getValue(),
                    Integer.parseInt(cbEId.getValue()),
                    Double.parseDouble(tfPrice.getText())
            );
            testList.add(newTest);
            tableView.refresh();
            // Optionally, you can perform database insertion here using Queries.addTesttoDB() method
        } catch (NumberFormatException | NullPointerException ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }

    public void updateTest() {
        try {
            test.setName(tfTestName.getText());
            test.setScode(cbSCode.getValue());
            test.setEid(Integer.parseInt(cbEId.getValue()));
            test.setPrice(Double.parseDouble(tfPrice.getText()));
            tableView.refresh();
            // Optionally, you can perform database update here using Queries.updateTestInDB() method
        } catch (NumberFormatException | NullPointerException ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }




}
