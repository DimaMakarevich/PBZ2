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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Database.TechStaff;
import sample.Main;

public class TechStaffSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Pane mainPane;

    @FXML
    private Button searchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TableView<TechStaff> resultTableView;

    @FXML
    private TableColumn<TechStaff, String> snpColumn;

    @FXML
    private TableColumn<TechStaff, String> positionColumn;

    @FXML
    void initialize() {

        searchButton.setOnAction(actionEvent -> {
            ObservableList<TechStaff> techStaffs = null;
            try {
                techStaffs = Controller.getDatabase().searchTechStaff(Date.valueOf(datePicker.getValue()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultTableView.setItems(techStaffs);

            snpColumn.setCellValueFactory(new PropertyValueFactory<TechStaff, String>("snp"));
            positionColumn.setCellValueFactory(new PropertyValueFactory<TechStaff, String>("position"));
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
