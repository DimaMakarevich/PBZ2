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

import sample.Database.TechnicalInjection;
import sample.Main;

public class EditTechnicalInjectionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idTechnicalInjectionTextField;

    @FXML
    private TextField dateTechnicalInjectionTextField;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private TextField resultTechnicalInjectionTextField;

    @FXML
    private TextField causeOFfFailureTechnicalInjectionTextField;

    @FXML
    void initialize() {
        idTechnicalInjectionTextField.setText(TechnicalInjectionListController.getSelectedTechnicalInjection().getId());
        dateTechnicalInjectionTextField.setText(TechnicalInjectionListController.getSelectedTechnicalInjection().getDateTI().toString()); //////;
        resultTechnicalInjectionTextField.setText(TechnicalInjectionListController.getSelectedTechnicalInjection().getResult());
        causeOFfFailureTechnicalInjectionTextField.setText(TechnicalInjectionListController.getSelectedTechnicalInjection().getCauseOfFailure());

        editButton.setOnAction(actionEvent -> {
            try {
                TechnicalInjection newTechnicalInjection = new TechnicalInjection(idTechnicalInjectionTextField.getText(), Date.valueOf(dateTechnicalInjectionTextField.getText()) ,resultTechnicalInjectionTextField.getText(), causeOFfFailureTechnicalInjectionTextField.getText());
                Controller.getDatabase().editTechnicalInjection(TechnicalInjectionListController.getSelectedTechnicalInjection(), newTechnicalInjection);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/technicalInjectionList.fxml"));
                Stage stage = (Stage) editButton.getScene().getWindow();
                stage.setTitle("Technical Injections");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/technicalInjectionList.fxml"));
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setTitle("Technical Injections");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
