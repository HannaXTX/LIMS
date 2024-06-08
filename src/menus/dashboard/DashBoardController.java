package menus.dashboard;

import database.Queries;
import entities.Employee;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {


    @FXML
    public Button btLogout, btEmployee, btDashboard;
    @FXML
    Label lblTotalEmp, lblPendResult, lblTotalSamples;
    public GridPane gpDashboard;

//
//    private TableView<Employee> employeeTable;
//    private TableColumn<Employee, String> colId;
//    private TableColumn<Employee, String> colName;
//    private TableColumn<Employee, String> colSSN;
//    private TableColumn<Employee, String> colAddress;
//    private TableColumn<Employee, String> colDateOfBirth;
//    private TableColumn<Employee, String> colMajor;
//    private TableColumn<Employee, String> colPhoneNumber;
//    private TableColumn<Employee, String> colEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblTotalEmp.setText(Queries.getEmployeeCount() + " " + lblTotalEmp.getText());
            lblTotalSamples.setText(Queries.getSampleCount(0) + " " + lblTotalSamples.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
