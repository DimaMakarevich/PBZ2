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
import sample.Main;

public class EditTechStaffController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idTechStaffTextField;

    @FXML
    private TextField snpTechStaffTextField;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private TextField positionTechStaffTextField;

    @FXML
    void initialize() {
        idTechStaffTextField.setText(TechStaffListController.getSelectedTechStaff().getId());
        snpTechStaffTextField.setText(TechStaffListController.getSelectedTechStaff().getSnp()); //////;
        positionTechStaffTextField.setText(TechStaffListController.getSelectedTechStaff().getPosition());

        editButton.setOnAction(actionEvent -> {
            try {
                TechStaff newTechStaff = new TechStaff(idTechStaffTextField.getText(),snpTechStaffTextField.getText(), positionTechStaffTextField.getText());
                Controller.getDatabase().editTechStaff(TechStaffListController.getSelectedTechStaff(), newTechStaff);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/techStaffList.fxml"));
                Stage stage = (Stage) editButton.getScene().getWindow();
                stage.setTitle("Tech Staffs");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/techStaffList.fxml"));
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setTitle("Tech Staffs");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
