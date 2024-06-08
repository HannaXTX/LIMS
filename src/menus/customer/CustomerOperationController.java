package menus.customer;

import database.Queries;
import database.UtilFunctions;
import entities.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerOperationController implements Initializable {

    @FXML
    private TextField tfName, tfJob, tfPhoneNumber, tfEmail;

    @FXML
    private DatePicker dpDate;
    @FXML
    private Button btDone, btCancel;

    private Customer cus;

    private ObservableList<Customer> customersList;
    private TableView<Customer> tableView;

    public void setCustomersList(ObservableList<Customer> customersList) {
        this.customersList = customersList;
    }

    public void setTableView(TableView<Customer> tableView) {
        this.tableView = tableView;
    }


    @FXML
    public Customer saveEmployee() {
        Customer newCustomer = new Customer(
                getNextId(),
                tfName.getText(),
                tfJob.getText(),
                tfPhoneNumber.getText(),
                tfEmail.getText()
        );
        customersList.add(newCustomer);
        tableView.refresh();
        return newCustomer;
    }


    public void updateCustomer() {
        cus.setName(tfName.getText());
        cus.setJob(tfJob.getText());
        cus.setPhoneNumber(tfPhoneNumber.getText());
        cus.setEmail(tfEmail.getText());
        tableView.refresh();
    }


    public void setCusData(Customer cus) {
        tfName.setText(cus.getName());
        tfJob.setText(cus.getJob());
        tfPhoneNumber.setText(cus.getPhoneNumber());
        tfEmail.setText(cus.getEmail());
    }


    private int getNextId() {
        return customersList.stream().mapToInt(Customer::getId).max().orElse(0) + 1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void cancelEvent(ActionEvent actionEvent) {
        CustomerController.getModifyStage().close();
    }

    public void setSelectedCustomer(Customer customer) {
        cus = customer;
        setCusData(cus);
    }

    public void addEvent(ActionEvent actionEvent) throws SQLException {
        try {
            if (cus == null) {
                //            String query = Queries.addEmployeetoDB(tfName.getText(), tfSSN.getText(), tfAddress.getText(), dpDate.getValue().toString(), cbMajor.getValue(), tfPhoneNumber.getText(), tfEmail.getText());
                //            Statement statement = Connector.getCon().createStatement();
                //            statement.executeUpdate(query);

                CustomerController.getModifyStage().close();
                saveEmployee();
            } else {
                updateCustomer();
                System.out.println(cus.getId());
                Queries.updateEmployeeInDB(cus.getId(), cus.getName(),
                        cus.getJob(), cus.getPhoneNumber(), cus.getEmail());

                CustomerController.getModifyStage().close();


            }


        } catch (Exception ex) {
            UtilFunctions.createAlert("ERROR", "ERROR", "EMPTY FIELDS", null).show();
            ex.printStackTrace();
        }
    }

}
