package menus.dashboard;

import com.mysql.jdbc.Util;
import database.Queries;
import database.UtilFunctions;
import entities.Employee;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import menus.login.Driver;
import menus.MenuManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
