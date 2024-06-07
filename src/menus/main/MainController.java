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


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button btLogout, btEmployee, btDashboard, btSample;
    public GridPane gpGRID;
    public AnchorPane apMain;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane apDashboard;
    DashBoardController dashBoardController;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/dashboard/Dashboard.fxml"));
        updateClock();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


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