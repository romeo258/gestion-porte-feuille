package com.tp.portefeuille;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NegoProprioController implements Initializable {

    @FXML
    private Button btnAjouterAppart;

    @FXML
    private Button btnAjouterNeg;

    @FXML
    private Button btnModifierAppart;

    @FXML
    private Button btnModifierNeg;

    @FXML
    private Button btnSupprimerAppart;

    @FXML
    private Button btnSupprimerNeg;

    @FXML
    private TableColumn<ObservableList<Object>, String> clientNegColmn;

    @FXML
    private TableColumn<ObservableList<Object>, String> clientTitreProcolmn;

    @FXML
    private TableColumn<ObservableList<Object>, LocalDate> periodeNegColmn;

    @FXML
    private TableColumn<ObservableList<Object>, Integer> qteNegColmn;

    @FXML
    private TableColumn<ObservableList<Object>, Integer> qteTitreProColmn;

    @FXML
    private TableColumn<ObservableList<Object>, String> sensNegColmn;

    @FXML
    private TableView<ObservableList<Object>> tabAppartenir;

    @FXML
    private TableView<ObservableList<Object>> tabNego;

    @FXML
    private TableColumn<ObservableList<Object>, String> titreNegColmn;

    @FXML
    private TableColumn<ObservableList<Object>, String> titreProColmn;

    @FXML
    private ComboBox<String> txtClientNeg;

    @FXML
    private ComboBox<String> txtClientTitrePro;

    @FXML
    private ComboBox<String> txtNomTitrepro;

    @FXML
    private ComboBox<String> txtPeriodeNeg;

    @FXML
    private TextField txtQteNeg;

    @FXML
    private TextField txtQteTitrePro;

    @FXML
    private ComboBox<String> txtSensNeg;

    @FXML
    private ComboBox<String> txtTitreNeg;

    @FXML
    void AjouterAppart(ActionEvent event) {

        String nomTitre,qte, nomCli;
        nomTitre = txtNomTitrepro.getValue();
        qte = txtQteTitrePro.getText();
        nomCli = txtClientTitrePro.getValue();
        try
        {
            pst = con.prepareStatement("insert into appartenance(nomTitre,qte, nomCli)values(?,?,?)");
            pst.setString(1, nomTitre);
            pst.setString(2, qte);
            pst.setString(3, nomCli);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement Correspondance");

            alert.setHeaderText("Correspondance Registation");
            alert.setContentText("Enregistrer!");
            alert.showAndWait();
            tabAppartenance();

            txtNomTitrepro.setValue("");
            txtQteTitrePro.setText("");
            txtClientTitrePro.setValue("");
            txtNomTitrepro.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void AjouterNeg(ActionEvent event) {

        String nomCli, nomTitre, qteNeg, sens, date;
        nomCli = txtClientNeg.getValue();
        nomTitre = txtTitreNeg.getValue();
        sens = txtSensNeg.getValue();
        qteNeg = txtQteNeg.getText();
        date = txtPeriodeNeg.getValue();
        try
        {
            pst = con.prepareStatement("insert into negociation(nomCli, nomTitre, qteNeg, sens, date)values(?,?,?,?,?)");
            pst.setString(1, nomCli);
            pst.setString(2, nomTitre);
            pst.setString(3, qteNeg);
            pst.setString(4, sens);
            pst.setString(5, date);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement Negociation");

            alert.setHeaderText("Negociation Registation");
            alert.setContentText("Record Enregistrer!");
            alert.showAndWait();
            tabNegociation();

            txtClientNeg.setValue("");
            txtTitreNeg.setValue("");
            txtQteNeg.setText("");
            txtSensNeg.setValue("");
            txtPeriodeNeg.setValue("");
            txtClientNeg.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(NegoProprioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void ModifierAppart(ActionEvent event) {

        String nomTitre, qte, nomCli;

        myIndex = tabAppartenir.getSelectionModel().getSelectedIndex();

        nomTitre = txtNomTitrepro.getValue();
        qte = txtQteTitrePro.getText();
        nomCli = txtClientTitrePro.getValue();
        try
        {
            pst = con.prepareStatement("update appartenance set qte = ?, nomCli = ? where nomTitre = ? ");
            pst.setString(1, qte);
            pst.setString(2, nomCli);
            pst.setString(3, nomTitre);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correspondance Registation");

            alert.setHeaderText("Correspondance Registation");
            alert.setContentText("Mis A jour!");
            alert.showAndWait();
            tabAppartenance();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void ModifierNeg(ActionEvent event) {

        String nomCli, nomTitre, qteNeg, sens, date;

        myIndex = tabNego.getSelectionModel().getSelectedIndex();

        nomCli = txtClientNeg.getValue();
        nomTitre = txtTitreNeg.getValue();
        qteNeg = txtQteNeg.getText();
        sens = txtSensNeg.getValue();
        date = txtPeriodeNeg.getValue();
        try
        {
            pst = con.prepareStatement("update negociation set nomTitre = ?, qteNeg = ?, sens = ?, date = ? where nomCli = ? ");
            pst.setString(1, nomTitre);
            pst.setString(2, qteNeg);
            pst.setString(3, sens);
            pst.setString(4, date);
            pst.setString(5, nomCli);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Negociation Registation");

            alert.setHeaderText("Negociation Registation");
            alert.setContentText("Mis A jour!");
            alert.showAndWait();
            tabNegociation();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(NegoProprioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void SupprimerAppart(ActionEvent event) {

        String nomTitre;

        myIndex = tabAppartenir.getSelectionModel().getSelectedIndex();

        nomTitre = txtNomTitrepro.getValue();

        try
        {
            pst = con.prepareStatement("delete from appartenance where nomTitre = ? ");
            pst.setString(1, nomTitre);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correspondance Registationn");

            alert.setHeaderText("Correspondance Registation");
            alert.setContentText("Supprimer!");
            alert.showAndWait();
            tabAppartenance();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void SupprimerNeg(ActionEvent event) {

        String nomCli, nomTitre;

        myIndex = tabNego.getSelectionModel().getSelectedIndex();

        nomCli = txtClientNeg.getValue();
        nomTitre = txtTitreNeg.getValue();

        try
        {
            pst = con.prepareStatement("delete from negociation where nomCli = ? and nomTitre = ? ");
            pst.setString(1, nomCli);
            pst.setString(2, nomTitre);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Negociation Registationn");

            alert.setHeaderText("Negociation Registation");
            alert.setContentText("Supprimer!");
            alert.showAndWait();
            tabNegociation();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(NegoProprioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connect();
        tabAppartenance();
        tabNegociation();



        ObservableList<String> titres = getAllTitresFromDB();
        txtNomTitrepro.setItems(titres);


        ObservableList<String> clients = getAllClientsFromDB();
        txtClientTitrePro.setItems(clients);


        ObservableList<String> titres1 = getAllTitres1FromDB();
        txtTitreNeg.setItems(titres1);


        ObservableList<String> clients1 = getAllClients1FromDB();
        txtClientNeg.setItems(clients1);


        ObservableList<String> periodes = getAllPeriodesFromDB();
        txtPeriodeNeg.setItems(periodes);

        ObservableList<String> sensItems = FXCollections.observableArrayList("A", "V");
        txtSensNeg.setItems(sensItems);

    }


    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;



    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/pfeuille?serverTimezone=UTC","root","1234");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    public void tabAppartenance() {
        Connect();
        ObservableList<ObservableList<Object>> appartenances = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("SELECT nomTitre, qte, nomCli FROM appartenance");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nomTitre = rs.getString("nomTitre");
                int qte = rs.getInt("qte");
                String nomCli = rs.getString("nomCli");

                // Create an ObservableList to represent a row in the TableView
                ObservableList<Object> row = FXCollections.observableArrayList();
                row.add(nomTitre);
                row.add(qte);
                row.add(nomCli);

                // Add the row to the list of cotations
                appartenances.add(row);
            }

            // Set the items in the TableView
            tabAppartenir.setItems(appartenances);

            // Configurez les cellules pour chaque colonne
            titreProColmn.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get(0)));
            qteTitreProColmn.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().get(1)).asObject());
            clientTitreProcolmn.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get(2)));
            // Define the row factory to handle row clicks
            tabAppartenir.setRowFactory(tv -> {
                TableRow<ObservableList<Object>> myRow = new TableRow<>();
                myRow.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                        // Get the selected row's data
                        ObservableList<Object> rowData = myRow.getItem();
                        String nomTitre = (String) rowData.get(0);
                        int qte = (int) rowData.get(1);
                        String nomCli = (String) rowData.get(2);

                        txtNomTitrepro.setValue(nomTitre);
                        txtQteTitrePro.setText(String.valueOf(qte));
                        txtClientTitrePro.setValue(nomCli);

                        // Use the data as needed
                        System.out.println("Titre: " + nomTitre + ", Quatite: " + qte + ", Client: " + nomCli);
                    }
                });
                return myRow;
            });

        } catch (SQLException ex) {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void tabNegociation() {
        Connect();
        ObservableList<ObservableList<Object>> negociations = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("SELECT nomCli, nomTitre, qteNeg, sens, date FROM negociation");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nomCli = rs.getString("nomCli");
                String nomTitre = rs.getString("nomTitre");
                int qteNeg = rs.getInt("qteNeg");
                String sens = rs.getString("sens");
                LocalDate date = rs.getDate("date").toLocalDate();

                // Create an ObservableList to represent a row in the TableView
                ObservableList<Object> row = FXCollections.observableArrayList();
                row.add(nomCli);
                row.add(nomTitre);
                row.add(qteNeg);
                row.add(sens);
                row.add(date);

                // Add the row to the list of cotations
                negociations.add(row);
            }

            // Set the items in the TableView
            tabNego.setItems(negociations);

            // Configurez les cellules pour chaque colonne
            clientNegColmn.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get(0)));
            titreNegColmn.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get(1)));
            qteNegColmn.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().get(2)).asObject());
            sensNegColmn.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get(3)));
            periodeNegColmn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((LocalDate) cellData.getValue().get(4)));
            // Define the row factory to handle row clicks
            tabNego.setRowFactory(tv -> {
                TableRow<ObservableList<Object>> myRow = new TableRow<>();
                myRow.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                        // Get the selected row's data
                        ObservableList<Object> rowData = myRow.getItem();
                        String nomCli = (String) rowData.get(0);
                        String nomTitre = (String) rowData.get(1);
                        int qteNeg = (int) rowData.get(2);
                        String sens = (String) rowData.get(3);
                        LocalDate date = (LocalDate) rowData.get(4);


                        txtClientNeg.setValue(nomCli);
                        txtTitreNeg.setValue(nomTitre);
                        txtQteNeg.setText(String.valueOf(qteNeg));
                        txtSensNeg.setValue(sens);
                        txtPeriodeNeg.setValue(String.valueOf(date));


                        // Use the data as needed
                        System.out.println("Client: " + nomCli + "Titre: " + nomTitre + ", Quatite: " + qteNeg + ", sens: " + nomCli + ", Periode: " + date);
                    }
                });
                return myRow;
            });

        } catch (SQLException ex) {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private ObservableList<String> getAllTitresFromDB() {
        ObservableList<String> titres = FXCollections.observableArrayList();

        try {
            String query = "SELECT nomTitre FROM titre";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String titreName = rs.getString("nomTitre");
                titres.add(titreName);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return titres;
    }

    private ObservableList<String> getAllClientsFromDB() {
        ObservableList<String> clients = FXCollections.observableArrayList();

        try {
            String query = "SELECT nomCli FROM client";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String clientNomCli = rs.getString("nomCli");
                clients.add(clientNomCli);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clients;
    }

    private ObservableList<String> getAllPeriodesFromDB() {
        ObservableList<String> periodes = FXCollections.observableArrayList();

        try {
            String query = "SELECT date FROM Periode";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String periodeDate = rs.getString("date");
                periodes.add(periodeDate);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return periodes;
    }

    //  ComboBox pour la negociation

    private ObservableList<String> getAllTitres1FromDB() {
        ObservableList<String> titres1 = FXCollections.observableArrayList();

        try {
            String query = "SELECT nomTitre FROM titre";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String titreName = rs.getString("nomTitre");
                titres1.add(titreName);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return titres1;
    }

    private ObservableList<String> getAllClients1FromDB() {
        ObservableList<String> clients1 = FXCollections.observableArrayList();

        try {
            String query = "SELECT nomCli FROM client";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String clientNomCli = rs.getString("nomCli");
                clients1.add(clientNomCli);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clients1;
    }



}
