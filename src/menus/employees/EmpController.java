package menus.employees;

import database.Connector;
import entities.Employee;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import menus.login.Driver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmpController implements Initializable {

    @FXML
    private TextField tfName, tfSSN, tfAddress, tfPhoneNumber, tfEmail;
    @FXML
    private ComboBox<String> cbMajor;
    @FXML
    private DatePicker dpDate;
    @FXML
    private Button btAdd, btUpdate;
    @FXML
    private TableView<Employee> tbEmployee;
    @FXML
    private TableColumn<Employee, Integer> colId;
    @FXML
    private TableColumn<Employee, String> colName, colSSN, colAddress, colDate, colMajor, colPhoneNumber, colEmail;

    private static Stage modifyStage;


    @FXML
    private GridPane gpEmployee;

    ArrayList<Employee> empList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colSSN.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSsn()));
        colAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        colDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateOfBirth()));
        colMajor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMajor()));
        colPhoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        try {
            fillTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTable() throws SQLException {
        String query = "SELECT * FROM Employees";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("SSN"),
                        resultSet.getString("Address"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("Major"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Email")
                );
                empList.add(employee);

            }
            tbEmployee.setItems(FXCollections.observableList(empList));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/employees/EmployeeOperation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            EmployeeOperationController employeeOperationController = loader.getController();
            employeeOperationController.setEmployeeList(FXCollections.observableList(empList));
            employeeOperationController.setTableView(tbEmployee);

            modifyStage = new Stage();
            modifyStage.setScene(scene);
            modifyStage.show();
        }

        if (actionEvent.getSource() == btUpdate) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/employees/EmployeeOperation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);


            EmployeeOperationController employeeOperationController = loader.getController();
            employeeOperationController.setSelectedEmployee(tbEmployee.getSelectionModel().getSelectedItem());
            employeeOperationController.setEmployeeList(FXCollections.observableList(empList));
            employeeOperationController.setTableView(tbEmployee);

            modifyStage = new Stage();
            modifyStage.setScene(scene);
            modifyStage.show();
        }
    }

//
//    public void deleteEmployee(int id) {
//        String query = "DELETE FROM Employees WHERE ID = " + id;
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password");
//             Statement statement = conn.createStatement()) {
//            statement.executeUpdate(query);
//            fillTable();  // Refresh the table view
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


}

