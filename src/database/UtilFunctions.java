package database;

import entities.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UtilFunctions {

    private void setupTableView(TableView<Employee> tableView) {
        String[] empAttributes = Employee.getCols();
        for (int i = 0; i < empAttributes.length; i++) {
            TableColumn<Employee, String> tblCol = new TableColumn<>(empAttributes[0]);
            int index =i;
        //    tblCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(index)));
        }
        ObservableList<Employee> observableData = FXCollections.observableArrayList();
        tableView.setItems(observableData);
    }


    public static Alert createAlert(String type, String title, String content, ButtonType buttonType) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.setContentText(content);
        alert.getButtonTypes().removeAll();
        if (buttonType != null)
            alert.getButtonTypes().set(0, buttonType);
        return alert;
    }


}
