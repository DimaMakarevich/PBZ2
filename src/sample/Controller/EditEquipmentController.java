package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Equipment;
import sample.Database.ProductionSite;
import sample.Main;

public class EditEquipmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idEquipmentTextField;

    @FXML
    private TextField titleEquipmentTextField;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    void initialize() {
        idEquipmentTextField.setText(EquipmentListController.getSelectedEquipment().getId());
        titleEquipmentTextField.setText(EquipmentListController.getSelectedEquipment().getTitle());

        editButton.setOnAction(actionEvent -> {
            try {
                Equipment newEquipment = new Equipment(idEquipmentTextField.getText(), titleEquipmentTextField.getText());
                Controller.getDatabase().editEquipment(EquipmentListController.getSelectedEquipment(), newEquipment);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/equipmentList.fxml"));
                Stage stage = (Stage) editButton.getScene().getWindow();
                stage.setTitle("Equipments");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/equipmentList.fxml"));
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setTitle("Equipments");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
