package menus.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;



public class Driver extends Application {

    private static Stage mainStage;
    private static Scene mainScene;

    public static void main(String[] args) {

        launch(args);

        System.out.println("Hello world!");
    }


    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        Scene scene = new Scene(root);
       mainScene = scene;
        String css = this.getClass().getResource("/Assets/Buttons/LoginButtonStyle.css").toExternalForm();
        System.out.println(css);
//        scene.getStylesheets().add(css);
        mainStage.setScene(scene);
        mainStage.setResizable(false);

        mainStage.show();

    }

    public static Stage getMainStage() {
        return mainStage;
    }
    public static Scene getMainScene() {
        return mainScene;
    }
}