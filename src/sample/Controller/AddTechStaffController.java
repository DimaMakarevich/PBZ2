package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.TechStaff;
import sample.Database.TechnicalInjection;
import sample.Main;

public class AddTechStaffController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idFieldText;

    @FXML
    private TextField snpFieldText;

    @FXML
    private Button backButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField positionFieldText;

    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
            try {

                TechStaff techStaff = new TechStaff(idFieldText.getText(), snpFieldText.getText(), positionFieldText.getText()); ///??????????????? DATE
                Controller.getDatabase().addTechStaff(techStaff);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/techStaffList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Tech Staff");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/techStaffList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Tech Staff");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
