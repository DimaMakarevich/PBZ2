package sample.Controller;

import java.io.IOException;
import java.net.URL;
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
import sample.Database.TechStaff;

import sample.Main;

public class TechStaffListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TechStaff> techStaffTableView;

    @FXML
    private TableColumn<TechStaff, String> idColumn;

    @FXML
    private TableColumn<TechStaff, String> snpColumn;

    @FXML
    private TableColumn<TechStaff, String> positionColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;

    private static TechStaff selectedTechStaff;

    private static ObservableList< TechStaff> techStaffs;

    public static  TechStaff getSelectedTechStaff() {
        return selectedTechStaff;
    }

    private  void createTechStaffTable() {
        techStaffs = FXCollections.observableArrayList(Controller.getDatabase().getTechStaffList());
        techStaffTableView.setItems(techStaffs);
        idColumn.setCellValueFactory(new PropertyValueFactory<TechStaff, String>("id"));
        snpColumn.setCellValueFactory(new PropertyValueFactory<TechStaff, String>("snp"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<TechStaff, String>("position"));
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setContentText("Select you tech Staff");
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        createTechStaffTable();
        addButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/addTechnStaff.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(actionEvent -> {
            try {
                selectedTechStaff = techStaffTableView.getSelectionModel().getSelectedItem();
                if (selectedTechStaff != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("View/editTechStaff.fxml"));
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
                selectedTechStaff = techStaffTableView.getSelectionModel().getSelectedItem();
                if (selectedTechStaff != null) {
                    Controller.getDatabase().deleteTechStaff(getSelectedTechStaff()); ////
                    techStaffs = FXCollections.observableArrayList(Controller.getDatabase().getTechStaffList());
                    techStaffTableView.setItems(techStaffs);
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
