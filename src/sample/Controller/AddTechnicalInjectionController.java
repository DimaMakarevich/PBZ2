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
import sample.Database.ProductionSite;
import sample.Database.TechnicalInjection;
import sample.Main;
import java.sql.Date;

public class AddTechnicalInjectionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idFieldText;

    @FXML
    private TextField dataFieldText;

    @FXML
    private Button backButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField causeOfFailureFieldText;

    @FXML
    private TextField resultFieldText;

    @FXML
    void initialize() {

        addButton.setOnAction(actionEvent -> {
            try {

                TechnicalInjection technicalInjection = new TechnicalInjection(idFieldText.getText(),Date.valueOf(dataFieldText.getText()), resultFieldText.getText(), causeOfFailureFieldText.getText()); ///??????????????? DATE
                Controller.getDatabase().addTechnicalInjection(technicalInjection);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/technicalInjectionList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Technical Injections");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/technicalInjectionList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Technical Injections");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
