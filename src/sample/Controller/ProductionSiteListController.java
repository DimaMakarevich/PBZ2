package sample.Controller;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.x.protobuf.MysqlxConnection;
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
import sample.Database.ProductionSite;
import sample.Main;

public class ProductionSiteListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ProductionSite> productionSiteTableView;

    @FXML
    private TableColumn<ProductionSite, String> idColumn;

    @FXML
    private TableColumn<ProductionSite, String> nameColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;

    private static ProductionSite selectedProductionSite;

    private static ObservableList<ProductionSite> productionSites;

    public static ProductionSite getSelectedProductionSite() {
        return selectedProductionSite;
    }

    private  void createProductionSiteTable() {
        productionSites = FXCollections.observableArrayList(Controller.getDatabase().getProductionSiteList());
        productionSiteTableView.setItems(productionSites);
        idColumn.setCellValueFactory(new PropertyValueFactory<ProductionSite, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ProductionSite, String>("name")); ///////
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setContentText("Select you production site ");
        alert.showAndWait();
    }

    @FXML
    void initialize() throws SQLException, IOException{
        createProductionSiteTable();
        addButton.setOnAction(actionEvent -> {
            try{
                Parent root = FXMLLoader.load(Main.class.getResource("View/addProductionSite.fxml"));
                Stage stage = (Stage)  addButton.getScene().getWindow();
                stage .setTitle("Adding");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(actionEvent -> {
            try {
                selectedProductionSite = productionSiteTableView.getSelectionModel().getSelectedItem();
                if(selectedProductionSite != null){
                    Parent root = FXMLLoader.load(Main.class.getResource("View/editProductionSite.fxml"));
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
                selectedProductionSite = productionSiteTableView.getSelectionModel().getSelectedItem();
                if(selectedProductionSite != null){
                    Controller.getDatabase().deleteProductionSite(getSelectedProductionSite()); ////
                    productionSites= FXCollections.observableArrayList(Controller.getDatabase().getProductionSiteList());
                    productionSiteTableView.setItems(productionSites);
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

