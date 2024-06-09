package menus.employees;

import database.Connector;
import entities.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmpStatsController implements Initializable {

    Employee emp;
    @FXML
    Label lblID, lblName, lblSSN, lblAddress, lblMajor, lblDateOfBirth, lblPhone, lblEmail;
    @FXML
    Label lblTestPerformable, lblSampleleft;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cancelEvent(ActionEvent actionEvent) {
    }

    public void setSelectedEmployee(Employee employee) {
        emp = employee;
        setEmpData(emp);
    }

    private void setEmpData(Employee emp) {
        lblID.setText(emp.getId() + "");
        lblName.setText(emp.getName());
        lblSSN.setText(emp.getSsn());
        lblAddress.setText(emp.getAddress());
        lblMajor.setText(emp.getMajor());
        lblDateOfBirth.setText(emp.getDateOfBirth()); // Assuming dateOfBirth is a Date or LocalDate object
        lblPhone.setText(emp.getPhoneNumber());
        lblEmail.setText(emp.getEmail());
        lblTestPerformable.setText("" + getEmpTests(emp.getId()));
    }

    public int getEmpTests(int ID) {
        String query = "SELECT COUNT(*) AS TotalTestCount FROM TEST T WHERE T.EID = ?;";

        try (PreparedStatement statement = Connector.getCon().prepareStatement(query)) {

            statement.setInt(1, ID);

            ResultSet resultSet = statement.executeQuery();

            int totalTestCount = 0;
            if (resultSet.next()) {
                totalTestCount = resultSet.getInt("TotalTestCount");
            }

            resultSet.close();

            return totalTestCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
