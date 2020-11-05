package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Database.TechnicalInjection;
import sample.Main;

public class TechInjectionEquipmentSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private Button searchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TableView<TechnicalInjection> resultTableView;

    @FXML
    private TableColumn<TechnicalInjection, String> nameColumn;

    @FXML
    private TableColumn<TechnicalInjection, String> resultColumn;

    @FXML
    private TableColumn<TechnicalInjection, Date> dateColumn;

    @FXML
    private TextField idTextField;

    @FXML
    void initialize() {


     //   ObservableList<String> techInjections = Controller.getDatabase().getProductionSiteNames();
       // productionSiteComboBox.setItems(techInjections);

        searchButton.setOnAction(actionEvent -> {
            ObservableList<TechnicalInjection>  technicalInjections = null;
            try {
                technicalInjections = Controller.getDatabase().searchTechInjectionEquipment(idTextField.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultTableView.setItems(technicalInjections);

            nameColumn.setCellValueFactory(new PropertyValueFactory<TechnicalInjection, String>("id"));
            resultColumn.setCellValueFactory(new PropertyValueFactory<TechnicalInjection, String>("result"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<TechnicalInjection, Date>("dateTI"));

        });

        cancelButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/sample.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
