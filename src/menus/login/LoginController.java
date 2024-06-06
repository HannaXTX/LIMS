package menus.login;

import database.Connector;
import menus.MenuManager;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController extends MenuManager {


    @FXML
    //REQUIRED to inform the class that this variable is the same as the variable in SCENE BUILDER (must have fx:id and name be the same)
    private Button btLogin, btExit;

    private FadeTransition fadeTransition;

    public void initialize() {

        fadeTransition = new FadeTransition(Duration.millis(300), btLogin);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);


        btLogin.setOnMouseEntered(event -> {
//            fadeTransition.play();
        });

        btLogin.setOnMouseExited(event -> {
//            fadeTransition.stop();
//            loginButton.setOpacity(1.0);
        });
    }

    public void loginButtonAction(ActionEvent actionEvent) throws Exception {

        try {
            Connector connecter = new Connector();
        } catch (SQLException ex) {
            System.out.println("PLACEHOLDER ERROR");
        }
        Parent main = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menus/main/Main.fxml")));     // using the dash before Menus like /Menus takes the path to src.
        Scene scene = new Scene(main);
        Driver.getMainStage().setScene(scene);
    }

    public void btExitAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}



