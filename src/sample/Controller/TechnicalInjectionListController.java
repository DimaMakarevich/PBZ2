package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Database.TechnicalInjection;
import sample.Main;

public class TechnicalInjectionListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TechnicalInjection> technicalInjectionTableView;

    @FXML
    private TableColumn<TechnicalInjection, String> idColumn;

    @FXML
    private TableColumn<TechnicalInjection, Date> dateColumn;

    @FXML
    private TableColumn<TechnicalInjection, String> resultColumn;

    @FXML
    private TableColumn<TechnicalInjection, String> causeOfFailure;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;


    private static TechnicalInjection selectedTechnicalInjection;

    private static ObservableList<TechnicalInjection> technicalInjections;

    public static TechnicalInjection getSelectedTechnicalInjection() {
        return selectedTechnicalInjection;
    }

    private void createTechnicalInjectionTable() {
        technicalInjections = FXCollections.observableArrayList(Controller.getDatabase().getTechnicalInjectionList());
        technicalInjectionTableView.setItems(technicalInjections);
        idColumn.setCellValueFactory(new PropertyValueFactory<TechnicalInjection, String>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<TechnicalInjection, Date>("dateTI"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<TechnicalInjection, String>("result"));
        causeOfFailure.setCellValueFactory(new PropertyValueFactory<TechnicalInjection, String>("causeOfFailure"));

        ///////
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setContentText("Select you technical injection");
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        createTechnicalInjectionTable();
        addButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/addTechnicalInjection.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(actionEvent -> {
            try {
                selectedTechnicalInjection = technicalInjectionTableView.getSelectionModel().getSelectedItem();
                if (selectedTechnicalInjection != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("View/editTechnicalInjection.fxml"));
                    Stage stage = (Stage) editButton.getScene().getWindow();
                    stage.setTitle("Edit");
                    stage.setScene(new Scene(root));
                } else {
                    showError();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                selectedTechnicalInjection = technicalInjectionTableView.getSelectionModel().getSelectedItem();
                if (selectedTechnicalInjection != null) {
                    Controller.getDatabase().deleteTechnicalInjection(getSelectedTechnicalInjection()); ////
                    technicalInjections = FXCollections.observableArrayList(Controller.getDatabase().getTechnicalInjectionList());
                    technicalInjectionTableView.setItems(technicalInjections);
                } else {
                    showError();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/sample.fxml"));
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setTitle("Учет оборудования");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
