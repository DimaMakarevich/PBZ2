package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Database.Database;
import sample.Main;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    private static Database database;
    @FXML
    private Button operationProductionSiteButton;

    @FXML
    private Button operationEquipmentButton;

    @FXML
    private Button operationTechnicalInspectionButton;

    @FXML
    private Button operationStaffButton;

    @FXML
    private Button listFailedEquipmentButton;

    @FXML
    private Button historyTechInspectionButton;

    @FXML
    private Button listStaffTechDepartmentButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        database = new Database();

        operationProductionSiteButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/productionSiteList.fxml"));
                Stage stage = (Stage) operationProductionSiteButton.getScene().getWindow();
                stage.setTitle("Production site");
                stage.setScene(new Scene(root, 700, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        operationEquipmentButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/equipmentList.fxml"));
                Stage stage = (Stage) operationEquipmentButton.getScene().getWindow();
                stage.setTitle("Equipment");
                stage.setScene(new Scene(root, 700, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        operationTechnicalInspectionButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/technicalInjectionList.fxml"));
                Stage stage = (Stage) operationTechnicalInspectionButton.getScene().getWindow();
                stage.setTitle("Technical Inspection");
                stage.setScene(new Scene(root, 700, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        operationStaffButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/techStaffList.fxml"));
                Stage stage = (Stage) operationStaffButton.getScene().getWindow();
                stage.setTitle("Staff");
                stage.setScene(new Scene(root, 700, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        //------------------------------------------------------------------------------------------------



        listFailedEquipmentButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/equipmentSearch.fxml"));
                Stage stage = (Stage) listFailedEquipmentButton.getScene().getWindow();
                stage.setTitle("Failed Equipment");
                stage.setScene(new Scene(root, 700, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        historyTechInspectionButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/techInjectionEquipmentSearch.fxml"));
                Stage stage = (Stage) historyTechInspectionButton.getScene().getWindow();
                stage.setTitle("History Tech Inspection");
                stage.setScene(new Scene(root, 700, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        listStaffTechDepartmentButton.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("View/techStaffSearch.fxml"));
                Stage stage = (Stage) listStaffTechDepartmentButton.getScene().getWindow();
                stage.setTitle("Staff Tech Department");
                stage.setScene(new Scene(root, 700, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Database getDatabase() {
        return database;
    }
}
