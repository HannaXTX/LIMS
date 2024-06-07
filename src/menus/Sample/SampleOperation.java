package menus.Sample;

import entities.Employee;
import entities.Sample;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SampleOperation implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSampleType.getItems().addAll("Human Food", "Animal feed", "Drinking Water", "Bottled water");
    }


    @FXML
    private TextField tfName , tfStorage, tfTemp;
    @FXML
    private ComboBox<String> cbSampleType;
    @FXML
    private DatePicker dpExpDate, tfProdDate;
    @FXML
    private Button btDone, btCancel;

    private Sample sample;

    private ObservableList<Sample> sampleList;
    private TableView<Sample> tableView;






}
