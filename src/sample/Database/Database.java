package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database extends Config {
    private static Connection dbConnection;
    private final String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC";
    private static Statement stm;

    private List<ProductionSite> productionSiteList = new ArrayList<>();
    private List<Equipment> equipmentList = new ArrayList<>();
    private List<TechnicalInjection> technicalInjectionList = new ArrayList<>();
    private List<TechStaff> techStaffList = new ArrayList<>();

    public Database() throws ClassNotFoundException, SQLException {
        try {
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stm = dbConnection.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        setEquipment();
        setProductionSite();
        setStaff();
        setTechnicalInjection();

        //Class.forName("com.mysql/jdbc.Driver");
    }

    public void setProductionSite() throws SQLException {
        String id;
        String name;
        String equipment;
        try {
            ResultSet resultSet = stm.executeQuery("SELECT  * FROM productionSite ");
            while (resultSet.next()) {
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                productionSiteList.add(new ProductionSite(id, name));
                //????????????????????
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void setEquipment() throws SQLException {
        String id;
        String title;

        try {
            ResultSet resultSet = stm.executeQuery("SELECT  * FROM equipment ");
            while (resultSet.next()) {
                id = resultSet.getString(1);
                title = resultSet.getString(2);
                equipmentList.add(new Equipment(id, title));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void setTechnicalInjection() throws SQLException {
        String id;
        Date date;
        String result;
        String causeOfFailure;

        try {
            ResultSet resultSet = stm.executeQuery("SELECT  * FROM technicalInjection ");
            while (resultSet.next()) {
                id = resultSet.getString(1);
                date = resultSet.getDate(2);
                result = resultSet.getString(3);
                causeOfFailure = resultSet.getString(4);
                technicalInjectionList.add(new TechnicalInjection(id, date, result, causeOfFailure));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void setStaff() throws SQLException {
        String id;
        String snp;
        String position;

        try {
            ResultSet resultSet = stm.executeQuery("SELECT  * FROM techStaff ");
            while (resultSet.next()) {
                id = resultSet.getString(1);
                snp = resultSet.getString(2);
                position = resultSet.getString(3);
                techStaffList.add(new TechStaff(id, snp, snp));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void addProductionSite(ProductionSite productionSite) throws SQLException {
        try {
            stm.executeUpdate("INSERT INTO productionSite (id, name) " + "VALUES ('" + productionSite.getId() + "', '" + productionSite.getName() + "');");
            productionSiteList.add(productionSite);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void addEquipment(Equipment equipment) throws SQLException {
        try {
            stm.executeUpdate("INSERT INTO equipment(id, title) " + "VALUES ('" + equipment.getId() + "', '" + equipment.getTitle() + "');");
            equipmentList.add(equipment);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    //  INSERT INTO TEACHERS (privateNumber, firstname, post, department, specialty, homePhone) VALUES ('221L', 'Frolov', 'Docent', 'ECM', 'ECM', 543);
    public void addTechnicalInjection(TechnicalInjection technicalInjection) throws SQLException {
        try {
            stm.executeUpdate("INSERT INTO technicalInjection(id, dateTI, result, causeOffailure) "
                    + "VALUES ('" + technicalInjection.getId() + "', '" + technicalInjection.getDateTI() + "', '" + technicalInjection.getResult() + "', '" + technicalInjection.getCauseOfFailure() + "');");
            technicalInjectionList.add(technicalInjection);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void addTechStaff(TechStaff techStaff) throws SQLException {
        try {
            stm.executeUpdate("INSERT INTO techStaff(id, snp, position) "
                    + "VALUES ('" + techStaff.getId() + "', '" + techStaff.getSnp() + "', '" + techStaff.getPosition() + "');");
            techStaffList.add(techStaff);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

//----------------------------------------------------------------------------------------------------------------


    public void deleteProductionSite(ProductionSite productionSite) throws SQLException {
        try {
            stm.executeUpdate("DELETE FROM  productionSite WHERE id ='" + productionSite.getId() + "'AND name ='" + productionSite.getName() + "'");
            productionSiteList.remove(productionSite);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void deletedEquipment(Equipment equipment) throws SQLException {
        try {
            stm.executeUpdate("DELETE FROM  equipment WHERE id ='" + equipment.getId() + "'AND title ='" + equipment.getTitle() + "'");
            equipmentList.remove(equipment);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void deleteTechnicalInjection(TechnicalInjection technicalInjection) throws SQLException {
        try {
            stm.executeUpdate("DELETE FROM  technicalInjection WHERE id= '"
                    + technicalInjection.getId() + "'AND dateTI = '" + technicalInjection.getDateTI() + "'AND  result = '" + technicalInjection.getResult() +
                    "'AND causeOfFailure='" + technicalInjection.getCauseOfFailure() + "'");
            technicalInjectionList.remove(technicalInjection);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void deleteTechStaff(TechStaff techStaff) throws SQLException {
        try {
            stm.executeUpdate("DELETE FROM  techStaff WHERE id= '" + techStaff.getId() + "'AND snp ='" + techStaff.getSnp() + "'AND position = '" + techStaff.getPosition() + "'");
            techStaffList.remove(techStaff);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public void editProductionSite(ProductionSite productionSite, ProductionSite newProductionSite) throws SQLException {
        try {
            stm.executeUpdate("UPDATE productionSite SET id='" + newProductionSite.getId() + "', "
                    + "name = '" + newProductionSite.getName() + "'" +
                    "WHERE id='" + productionSite.getId() + "'AND name ='" + productionSite.getName() + "'");

            for (int i = 0; i < productionSiteList.size(); i++) {
                if (productionSiteList.get(i).equals(productionSite)) {
                    productionSiteList.set(i, newProductionSite);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void editEquipment(Equipment equipment, Equipment newEquipment) throws SQLException {
        try {
            stm.executeUpdate("UPDATE equipment SET id='" + newEquipment.getId() + "', "
                    + "title = '" + newEquipment.getTitle() + "'" +
                    "WHERE id='" + equipment.getId() + "'AND title ='" + equipment.getTitle() + "'");

            for (int i = 0; i < equipmentList.size(); i++) {
                if (equipmentList.get(i).equals(equipment)) {
                    equipmentList.set(i, newEquipment);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void editTechnicalInjection(TechnicalInjection technicalInjection, TechnicalInjection newTechnicalInjection) throws SQLException {
        try {
            stm.executeUpdate("UPDATE technicalInjection SET id='" + newTechnicalInjection.getId() + "', "
                    + "dateTI ='" + newTechnicalInjection.getDateTI() + "', " +
                    "result = '" + newTechnicalInjection.getResult() + "', " +
                    "causeOfFailure ='" + newTechnicalInjection.getCauseOfFailure() + "'" +
                    "WHERE id= '" + technicalInjection.getId() + "'AND dateTI = '" + technicalInjection.getDateTI() +
                    "'And result ='" + technicalInjection.getResult() + "'AND causeOfFailure ='" + technicalInjection.getCauseOfFailure() + "'");

            for (int i = 0; i < technicalInjectionList.size(); i++) {
                if (technicalInjectionList.get(i).equals(technicalInjection)) {
                    technicalInjectionList.set(i, newTechnicalInjection);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void editTechStaff(TechStaff techStaff, TechStaff newTechStaff) throws SQLException {
        try {
            stm.executeUpdate("UPDATE techStaff SET id='" + newTechStaff.getId() + "', "
                    + "snp = '" + newTechStaff.getSnp() + "', " +
                    "position = '" + newTechStaff.getPosition() + "'" +
                    "WHERE id='" + techStaff.getId() + "'AND snp ='" + techStaff.getSnp() +
                    "'And position ='" + techStaff.getPosition() + "'");

            for (int i = 0; i < techStaffList.size(); i++) {
                if (techStaffList.get(i).equals(techStaff)) {
                    techStaffList.set(i, newTechStaff);
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

//--------------------------------------------------------------------------------

    public ObservableList<String> getProductionSiteNames() throws SQLException {

        ObservableList<String> productionSiteNames = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = stm.executeQuery("SELECT DISTINCT productionSite.name FROM productionSite");


            while (resultSet.next()) {
                productionSiteNames.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productionSiteNames;
    }

    public ObservableList<TechnicalInjection> searchEquipment(String productionSiteName) throws SQLException {
        ObservableList<TechnicalInjection> failureEquipment = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = stm.executeQuery("SELECT * FROM equipment_productionSite " +
                    "JOIN equipment_technicalInjection ON equipment_technicalInjection.idEquipment = equipment_productionSite.idEquipment" +
                    " JOIN equipment ON equipment.id = equipment_productionSite.idEquipment " +
                    "JOIN  technicalInjection ON technicalInjection.id =   equipment_technicalInjection.idTechnicalInjection" +
                    " JOIN productionSite ON productionSite.id = equipment_productionSite.idProductionSite " +
                    " WHERE technicalInjection.result = 'failure'" + "AND productionSite.name = '" + productionSiteName + "'"

            );

            while (resultSet.next()) {
                 failureEquipment.add(new TechnicalInjection(resultSet.getString(5), Date.valueOf(resultSet.getString(8)), resultSet.getString(9), resultSet.getString(10))); //смотреть порядок  даных в резултсете;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }


        return failureEquipment;


    }

    public ObservableList<TechnicalInjection> searchTechInjectionEquipment(String equipmentID) throws SQLException {
        ObservableList<TechnicalInjection> historyTechInjection = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = stm.executeQuery("SELECT * FROM technicalInjection " +
                    "JOIN equipment_technicalInjection ON equipment_technicalInjection.idTechnicalInjection = technicalInjection.id" +
                     " JOIN equipment ON equipment.id = equipment_technicalInjection.idEquipment" +
                    " WHERE equipment_technicalInjection.idEquipment ='" + equipmentID+ "'");


            while (resultSet.next()) {
                   historyTechInjection.add(new TechnicalInjection(resultSet.getString(8), Date.valueOf(resultSet.getString(2)), resultSet.getString(3), resultSet.getString(4)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return historyTechInjection;
    }


    public ObservableList<TechStaff> searchTechStaff(Date date) throws SQLException {
        ObservableList<TechStaff> techStaffs = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = stm.executeQuery("SELECT * FROM techStaff" +
                    " JOIN  techStaff_technicalInjection ON techStaff_technicalInjection.idTechStaff = techStaff.id" +
                    " JOIN   technicalInjection ON techStaff_technicalInjection.idTechnicalInjection = technicalInjection.id" +
                     " WHERE  technicalInjection.dateTI ='" + date +"'");

            while (resultSet.next()) {
                  techStaffs.add(new TechStaff(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return techStaffs;
    }


    public List<ProductionSite> getProductionSiteList() {
        return productionSiteList;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public List<TechnicalInjection> getTechnicalInjectionList() {
        return technicalInjectionList;
    }

    public List<TechStaff> getTechStaffList() {
        return techStaffList;
    }


}