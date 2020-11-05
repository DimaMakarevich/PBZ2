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
import sample.Database.Equipment;
import sample.Main;

public class EquipmentListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Equipment> equipmentTableView;

    @FXML
    private TableColumn<Equipment, String> idColumn;

    @FXML
    private TableColumn<Equipment, String> titleColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setContentText("Select you equipment ");
        alert.showAndWait();
    }

    private static Equipment selectedEquipment;

    private static ObservableList<Equipment> equipments;

    public static Equipment getSelectedEquipment() {
        return selectedEquipment;
    }

    private  void createEquipmentTable() {
        equipments = FXCollections.observableArrayList(Controller.getDatabase().getEquipmentList());
        equipmentTableView.setItems(equipments);
        idColumn.setCellValueFactory(new PropertyValueFactory<Equipment, String>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Equipment, String>("title")); ///////
    }

    @FXML
    void initialize() {
        createEquipmentTable();
        addButton.setOnAction(actionEvent -> {
            try{
                Parent root = FXMLLoader.load(Main.class.getResource("View/addEquipment.fxml"));
                Stage stage = (Stage)  addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(actionEvent -> {
            try {
                selectedEquipment = equipmentTableView.getSelectionModel().getSelectedItem();
                if(selectedEquipment != null){
                    Parent root = FXMLLoader.load(Main.class.getResource("View/editEquipment.fxml"));
                    Stage stage = (Stage)  editButton.getScene().getWindow();
                    stage .setTitle("Edit");
                    stage.setScene(new Scene(root));
                } else {
                    showError();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                selectedEquipment = equipmentTableView.getSelectionModel().getSelectedItem();
                if(selectedEquipment != null){
                    Controller.getDatabase().deletedEquipment(getSelectedEquipment()); ////
                    equipments= FXCollections.observableArrayList(Controller.getDatabase().getEquipmentList());
                    equipmentTableView.setItems(equipments);
                } else  {
                    showError();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/sample.fxml"));
                Stage stage = (Stage)  backButton.getScene().getWindow();
                stage .setTitle("Учет оборудования");
                stage.setScene(new Scene(root));
            } catch (IOException e){
                e.printStackTrace();
            }

        });
    }
}
