package menus.employees;

import database.Queries;
import database.UtilFunctions;
import entities.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeOperationController implements Initializable {

    @FXML
    private TextField tfName, tfSSN, tfAddress, tfPhoneNumber, tfEmail;
    @FXML
    private ComboBox<String> cbMajor;
    @FXML
    private DatePicker dpDate;
    @FXML
    private Button btDone, btCancel;

    private Employee emp;

    private ObservableList<Employee> employeeList;
    private TableView<Employee> tableView;

    public void setEmployeeList(ObservableList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setTableView(TableView<Employee> tableView) {
        this.tableView = tableView;
    }


    @FXML
    public Employee saveEmployee() {
        Employee newEmployee = new Employee(
                getNextId(),
                tfName.getText(),
                tfSSN.getText(),
                tfAddress.getText(),
                dpDate.getValue().toString(),
                cbMajor.getValue(),
                tfPhoneNumber.getText(),
                tfEmail.getText()
        );
        employeeList.add(newEmployee);
        tableView.refresh();
        return newEmployee;
    }


    public void updateEmployee() {
        emp.setName(tfName.getText());
        emp.setSsn(tfSSN.getText());
        emp.setAddress(tfAddress.getText());
        emp.setDateOfBirth(dpDate.getValue().toString());
        emp.setMajor(cbMajor.getValue());
        emp.setPhoneNumber(tfPhoneNumber.getText());
        emp.setEmail(tfEmail.getText());
        tableView.refresh();
    }


    public void setEmpData(Employee emp) {
        tfName.setText(emp.getName());
        tfSSN.setText(emp.getSsn());
        tfAddress.setText(emp.getAddress());
        dpDate.setValue(LocalDate.parse(emp.getDateOfBirth()));
        cbMajor.setValue(emp.getMajor());
        tfPhoneNumber.setText(emp.getPhoneNumber());
        tfEmail.setText(emp.getEmail());
    }


    private int getNextId() {
        return employeeList.stream().mapToInt(Employee::getId).max().orElse(0) + 1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbMajor.getItems().addAll("Biology", "Chemistry", "Finance");


    }

    public void cancelEvent(ActionEvent actionEvent) {
        EmpController.getModifyStage().close();
    }

    public void setSelectedEmployee(Employee employee) {
        emp = employee;
        setEmpData(emp);
    }

    public void addEvent(ActionEvent actionEvent) throws SQLException {
        try {
            if (emp == null) {
                //            String query = Queries.addEmployeetoDB(tfName.getText(), tfSSN.getText(), tfAddress.getText(), dpDate.getValue().toString(), cbMajor.getValue(), tfPhoneNumber.getText(), tfEmail.getText());
                //            Statement statement = Connector.getCon().createStatement();
                //            statement.executeUpdate(query);

                EmpController.getModifyStage().close();
                saveEmployee();
            } else {
                updateEmployee();
                System.out.println(emp.getId());
                Queries.updateEmployeeInDB(emp.getId(), emp.getName(),
                        emp.getSsn(), emp.getAddress(), emp.getDateOfBirth(),
                        emp.getMajor(), emp.getPhoneNumber(), emp.getEmail());

                EmpController.getModifyStage().close();


            }


        } catch (Exception ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }

}
