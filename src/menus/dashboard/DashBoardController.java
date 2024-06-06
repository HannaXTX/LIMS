package menus.dashboard;

import entities.Employee;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardController extends MenuManager {


    @FXML
    public Button btLogout, btEmployee, btDashboard;
    public GridPane gpDashboard, gpEmployee;
    public AnchorPane apDashboard;
    @FXML
    private Label lblTime;
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

    public void initialize() {
     //  gpDashboard.setVisible(true);
        //gpEmployee.setVisible(false);



    }

    public GridPane getGpDashboard(){
        return gpDashboard;
    }


    public void changeTab(ActionEvent event) {
        if (event.getSource() == btEmployee) {
            gpDashboard.setVisible(false);
          //  gpEmployee.setVisible(true);
        } else if (event.getSource() == btDashboard) {
            gpDashboard.setVisible(true);
            //gpEmployee.setVisible(false);
        }
    }


    public void BtLogout(ActionEvent event) {
        Driver.getMainStage().setScene(Driver.getMainScene());
    }





}
