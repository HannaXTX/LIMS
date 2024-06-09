package menus.results;

import database.Connector;
import database.Queries;
import database.UtilFunctions;
import entities.Result;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonType.YES;

public class ResultController implements Initializable {


    ;
    @FXML
    private TextField tfResultId, tfUnit, tfDescription, tfDate;
    @FXML
    private Button btAdd, btUpdate;
    @FXML
    private TableView<Result> tvResult;
    @FXML
    private TableColumn<Result, Integer> colId, colSCode;
    @FXML
    private TableColumn<Result, String> colDescription, colUnit, colDate, colStatus;

    private static Stage modifyStage;


    @FXML
    private GridPane gpEmployee;

    ArrayList<Result> resList = new ArrayList<>();


    public void initialize(URL location, ResourceBundle resources) {

//        Result(int resId, int SCode, String Status, String unit, String description, String date

        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getResId()).asObject());
        colSCode.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSCode()).asObject());
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        colUnit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnit()));
        colDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        colDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

        try {
            fillTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTable() throws SQLException {
        String query = "SELECT * FROM Result";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //        Result(int resId, int SCode, String Status, String unit, String description, String date

                Result result = new Result(
                        resultSet.getInt("Rid"),
                        resultSet.getInt(("SCode")),
                        resultSet.getString("Status"),
                        resultSet.getString("Unit"),
                        resultSet.getString("Description"),
                        resultSet.getString("Date")

                );
                resList.add(result);

            }
            tvResult.setItems(FXCollections.observableList(resList));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyTable(javafx.event.ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btAdd) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/results/ResultOperationController.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            ResultOperationController resultOperationController = loader.getController();
            resultOperationController.setResultList(FXCollections.observableList(resList));
            resultOperationController.setTableView(tvResult);

            modifyStage = new Stage();
            modifyStage.setScene(scene);
            modifyStage.show();
        }

        if (actionEvent.getSource() == btUpdate) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/results/ResultOperationController.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);


                ResultOperationController resultOperationController = loader.getController();
                resultOperationController.setSelectedResult(tvResult.getSelectionModel().getSelectedItem());
                resultOperationController.setResultsList(FXCollections.observableList(resList));
                resultOperationController.setTableView(tvResult);

                modifyStage = new Stage();
                modifyStage.setScene(scene);
                modifyStage.show();
            } catch (Exception ex) {
                UtilFunctions.createAlert("ERROR", "no Record Selected", "Please select a record to update", null).show();
            }

        }
    }


    public void deleteResult(javafx.event.ActionEvent actionEvent) throws SQLException {
        Result res = tvResult.getSelectionModel().getSelectedItem();
        UtilFunctions.createAlert("CONFIRMATION", "Confirmation",
                "are you sure you want to Delete this result" + " ?", YES).showAndWait().ifPresent(buttonType -> {
            if (buttonType == YES) {
                Queries.deleteResult(res.getResId());
                tvResult.getItems().remove(res);
                tvResult.refresh();

            }

        });

    }

    public static Stage getModifyStage() {
        return modifyStage;
    }
}
