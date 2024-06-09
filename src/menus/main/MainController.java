package menus.main;

import database.Connector;
import database.UtilFunctions;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import menus.dashboard.DashBoardController;
import menus.login.Driver;
import menus.login.LoginController;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button btLogout, btEmployee, btDashboard, btSample,btCustomer,btTest,btResult;
    public GridPane gpGRID;
    public AnchorPane apMain;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane apDashboard;
    DashBoardController dashBoardController;

    @FXML
    public  LoginController loginController = new LoginController();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/dashboard/Dashboard.fxml"));
        updateClock();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        enable( btEmployee,  btCustomer,  btDashboard,  btTest,  btResult);

        try {
            Node dashboard = loader.load();
            apMain.getChildren().add(dashboard); // Assuming you want to add the dashboard to the first column and row of gpGRID
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void BtLogout(javafx.event.ActionEvent event) throws SQLException {
        Driver.getMainStage().setScene(Driver.getMainScene());
        Connector.getCon().close();
        System.out.println("Successfully Logged out");
        System.out.println("Connection Closed");
    }


    public void enable(Button btEmployee, Button btCustomer, Button btDashboard, Button btTest, Button btResult) {

        if(loginController.getType().equals("User")) {
            btEmployee.setDisable(true);
            btCustomer.setDisable(true);
            btDashboard.setDisable(true);
            btTest.setDisable(true);
            btResult.setDisable(true);
        }
    }

    public void changeTab(ActionEvent actionEvent) throws IOException {
        apMain.getChildren().clear();
        try {
            if (actionEvent.getSource() == btEmployee) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/employees/Employees.fxml"));
                Node employee = loader.load();             // MUST BE DEFINED AS NODE ONLY (CANNOT BE CAST)
                apMain.getChildren().add(employee);

            } else if (actionEvent.getSource() == btDashboard) {
                apMain.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/dashboard/Dashboard.fxml"));
                Node dashboard = loader.load();
                apMain.getChildren().add(dashboard);
                //gpEmployee.setVisible(false);

            } else if (actionEvent.getSource() == btSample) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/Sample/SampleController.fxml"));
                Node sample = loader.load();             // MUST BE DEFINED AS NODE ONLY (CANNOT BE CAST)
                apMain.getChildren().add(sample);
            }
            else if (actionEvent.getSource() == btCustomer) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/customer/Customers.fxml"));
                Node customer = loader.load();             // MUST BE DEFINED AS NODE ONLY (CANNOT BE CAST)
                apMain.getChildren().add(customer);
            }
            else if (actionEvent.getSource() == btTest) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/tests/TestController.fxml"));
                Node test = loader.load();             // MUST BE DEFINED AS NODE ONLY (CANNOT BE CAST)
                apMain.getChildren().add(test);
            }
            else if (actionEvent.getSource() == btResult) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/results/ResultController.fxml"));
                Node result = loader.load();             // MUST BE DEFINED AS NODE ONLY (CANNOT BE CAST)
                apMain.getChildren().add(result);
            }
        }
        catch (Exception ex){
            UtilFunctions.createAlert("ERROR","ERROR","check error", ButtonType.OK).show();
            ex.printStackTrace();


        }


    }
    private void updateClock() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (lblTime != null)
            lblTime.setText(formattedDateTime);
    }
}