package menus.customer;

import database.Connector;
import database.Queries;
import database.UtilFunctions;
import entities.Customer;
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

public class CustomerController implements Initializable {

    @FXML
    private TextField tfName, tfSSN, tfAddress, tfPhoneNumber, tfEmail;
    @FXML
    private ComboBox<String> cbMajor;
    @FXML
    private DatePicker dpDate;
    @FXML
    private Button btAdd, btUpdate;
    @FXML
    private TableView<Customer> tvCustomer;
    @FXML
    private TableColumn<Customer, Integer> colId;
    @FXML
    private TableColumn<Customer, String> colName, colJob, colPhoneNumber, colEmail;

    private static Stage modifyStage;


    @FXML
    private GridPane gpEmployee;

    ArrayList<Customer> cusList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colJob.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJob()));
        colPhoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        try {
            fillTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTable() throws SQLException {
        String query = "SELECT * FROM customer";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("Cid"),
                        resultSet.getString("Name"),
                        resultSet.getString("Job"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Email")
                );
                cusList.add(customer);

            }
            tvCustomer.setItems(FXCollections.observableList(cusList));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Stage getModifyStage() {
        return modifyStage;
    }

    public void modifyTable(javafx.event.ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btAdd) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/customer/CustomerOperation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            CustomerOperationController customerOperationController = loader.getController();
            customerOperationController.setCustomersList(FXCollections.observableList(cusList));
            customerOperationController.setTableView(tvCustomer);

            modifyStage = new Stage();
            modifyStage.setScene(scene);
            modifyStage.show();
        }

        if (actionEvent.getSource() == btUpdate) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/customer/CustomerOperation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);


                CustomerOperationController customerOperationController = loader.getController();
                customerOperationController.setSelectedCustomer(tvCustomer.getSelectionModel().getSelectedItem());
                customerOperationController.setCustomersList(FXCollections.observableList(cusList));
                customerOperationController.setTableView(tvCustomer);

                modifyStage = new Stage();
                modifyStage.setScene(scene);
                modifyStage.show();
            } catch (Exception ex) {
                UtilFunctions.createAlert("ERROR", "no Record Selected", "Please select a record to update", null).show();
            }

        }
    }


    public void deleteEmployee(javafx.event.ActionEvent actionEvent) throws SQLException {
        Customer cus = tvCustomer.getSelectionModel().getSelectedItem();
        UtilFunctions.createAlert("CONFIRMATION", "Confirmation",
                "are you sure you want to Delete Employee " + cus.getName() + " ?", YES).showAndWait().ifPresent(buttonType -> {
            if (buttonType == YES) {
                try {
                    Queries.deleteCustomer(cus, cus.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tvCustomer.getItems().remove(cus);
                tvCustomer.refresh();

            }

        });

    }
}

