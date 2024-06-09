package menus.tests;

import database.Connector;
import database.UtilFunctions;
import entities.Employee;
import entities.Test;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestOperationController implements Initializable {

    @FXML
    private TextField tfTestId, tfTestName, tfPrice;
    @FXML
    private ComboBox<String> cbSCode;
    @FXML
    private ComboBox<Employee> cbEId;
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
        tfTestId.setText(test.getId() + "");
        tfTestName.setText(test.getName());
        getEmployee(test.getEid());
        tfPrice.setText(String.valueOf(test.getPrice()));
    }


    public void getEmployee(int Eid) {
        for (int i = 0; i < cbEId.getItems().size(); i++) {
            if (cbEId.getItems().get(i).getId() == Eid) {
              cbEId.getItems().get(i);
                cbEId.setValue(cbEId.getItems().get(i));
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate with actual SCode and EId values from the database

        try {
            fillEmployeeBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillEmployeeBox() throws SQLException {
        String query = "SELECT * FROM Employees";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("SSN"),
                        resultSet.getString("Address"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("Major"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Email")
                );
                cbEId.getItems().add(employee);

            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                        cbEId.getValue().getId(),
                        Double.parseDouble(tfPrice.getText())
                );
                testList.add(newTest);
            } else {
                test.setName(tfTestName.getText());
                test.setEid(cbEId.getValue().getId());
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
                    cbEId.getValue().getId(),
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
            test.setEid(cbEId.getValue().getId());
            test.setPrice(Double.parseDouble(tfPrice.getText()));
            tableView.refresh();
            // Optionally, you can perform database update here using Queries.updateTestInDB() method
        } catch (NumberFormatException | NullPointerException ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }


}
