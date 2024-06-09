package menus.login;

import database.Connector;
import database.Queries;
import menus.MenuManager;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController extends MenuManager {

    @FXML
    private Button btLogin, btExit, btSignup;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private FadeTransition fadeTransition;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fadeTransition = new FadeTransition(Duration.millis(300), btLogin);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        btLogin.setOnMouseEntered(event -> {
            // Uncomment if you want to use the fade transition
            // fadeTransition.play();
        });

        btLogin.setOnMouseExited(event -> {
            // Uncomment if you want to use the fade transition
            // fadeTransition.stop();
            // btLogin.setOpacity(1.0);
        });
    }

    @FXML
    public void loginButtonAction(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username/Password cannot be empty");
            return;
        }

        try {
            Connector connecter = new Connector();
            if (Queries.checkUser(username, password)) {
                messageLabel.setText("Login successful!");
                Parent main = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menus/main/Main.fxml")));
                Scene scene = new Scene(main);
                Driver.getMainStage().setScene(scene);
            } else {
                messageLabel.setText("Invalid username or password");
            }
        } catch (SQLException e) {
            messageLabel.setText("An error occurred during login");
            e.printStackTrace();
        } catch (Exception e) {
            messageLabel.setText("An error occurred while loading the main menu");
            e.printStackTrace();
        }
    }

    @FXML
    public void signupButtonAction(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username/Password cannot be empty");
            return;
        }

        try {
            Connector connector = new Connector();
            if (Queries.insertUser(username, password)) {
                messageLabel.setText("Sign-up successful! You can now log in.");
            } else {
                messageLabel.setText("Username already taken. Please choose another one.");
            }
        } catch (SQLException e) {
            messageLabel.setText("An error occurred during sign-up");
            e.printStackTrace();
        } catch (Exception e) {
            messageLabel.setText("An unexpected error occurred");
            e.printStackTrace();
        }
    }

    @FXML
    public void btExitAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
