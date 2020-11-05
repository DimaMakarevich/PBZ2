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

public class AddEquipmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idSiteTextField;

    @FXML
    private TextField titleSiteTextField;

    @FXML
    private Button backButton;

    @FXML
    private Button addButton;

    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
            try {
                Equipment equipment = new Equipment(idSiteTextField.getText(), titleSiteTextField.getText());
                Controller.getDatabase().addEquipment(equipment);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/equipmentList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Equipments");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/equipmentList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Equipments");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
