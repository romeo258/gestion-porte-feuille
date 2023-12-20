package com.tp.portefeuille;

import javafx.beans.property.SimpleDoubleProperty;
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

public class PeriodeCotationController implements Initializable {

    @FXML
    private TableColumn<ObservableList<Object>, String> titreCotColmn;

    @FXML
    private TableColumn<ObservableList<Object>, Double> prixCotColmn;

    @FXML
    private TableColumn<ObservableList<Object>, LocalDate> dateCotColmn;

    @FXML
    private Button btnAjouterCotation;

    @FXML
    private Button btnAjouterDate;

    @FXML
    private Button btnModifierCotation;

    @FXML
    private Button btnSupprimerCotation;

    @FXML
    private Button btnSupprimerDate;

    @FXML
    private TableColumn<Periode, LocalDate> datePeriodeColmn;

    @FXML
    private TableView<ObservableList<Object>> tabCotation;

    @FXML
    private TableView<Periode> tabPeriode;

    @FXML
    private ComboBox<String> txtDateTitreCoter;

    @FXML
    private DatePicker txtNomDatePeriode;

    @FXML
    private ComboBox<String> txtNomTitreCoter;

    @FXML
    private TextField txtPrixTitreCoter;

    @FXML
    void AjouterCotation(ActionEvent event) {

        String nomTitre,prix, date;
        nomTitre = txtNomTitreCoter.getValue();
        prix = txtPrixTitreCoter.getText();
        date = txtDateTitreCoter.getValue();
        try
        {
            pst = con.prepareStatement("insert into cotation(nomTitre,prix, date)values(?,?,?)");
            pst.setString(1, nomTitre);
            pst.setString(2, prix);
            pst.setString(3, date);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement Cotation");

            alert.setHeaderText("Catation Registation");
            alert.setContentText("Record Enregistrer!");
            alert.showAndWait();
            tabCotation();

            txtPrixTitreCoter.setText("");
            txtNomTitreCoter.setValue("");
            txtDateTitreCoter.setValue("");
            txtNomTitreCoter.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void AjouterDate(ActionEvent event) {
        Connect();

        LocalDate datePeriode = txtNomDatePeriode.getValue();

        try {

            PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM periode WHERE date = ?");
            checkStmt.setDate(1, Date.valueOf(datePeriode));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // La date existe déjà, afficher un message d'erreur ou ignorer l'ajout
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("La date existe déjà");
                alert.setContentText("La date sélectionnée existe déjà dans la table.");
                alert.showAndWait();
            } else {

                pst = con.prepareStatement("INSERT INTO periode (date) VALUES (?)");
                pst.setDate(1, Date.valueOf(datePeriode));
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enregistrement Date");
                alert.setHeaderText("Date Registration");
                alert.setContentText("Record Added!");
                alert.showAndWait();

                tabPeriode(); // Met à jour la table des périodes après l'ajout d'une nouvelle date.

                txtNomDatePeriode.setValue(null); // Réinitialise le DatePicker après l'ajout de la date.
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void ModifierCotation(ActionEvent event) {

        String nomTitre, prix, date;

        myIndex = tabCotation.getSelectionModel().getSelectedIndex();

        nomTitre = txtNomTitreCoter.getValue();
        prix = txtPrixTitreCoter.getText();
        date = txtDateTitreCoter.getValue();
        try
        {
            pst = con.prepareStatement("update cotation set prix = ?, date = ? where nomTitre = ? ");
            pst.setString(1, prix);
            pst.setString(2, date);
            pst.setString(3, nomTitre);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cotation Registation");

            alert.setHeaderText("Cotation Registation");
            alert.setContentText("Mis A jour!");
            alert.showAndWait();
            tabCotation();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void SupprimerCotation(ActionEvent event) {

        String nomTitre;

        myIndex = tabCotation.getSelectionModel().getSelectedIndex();

        nomTitre = txtNomTitreCoter.getValue();

        try
        {
            pst = con.prepareStatement("delete from cotation where nomTitre = ? ");
            pst.setString(1, nomTitre);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cotation Registationn");

            alert.setHeaderText("Cotation Registation");
            alert.setContentText("Supprimer!");
            alert.showAndWait();
            tabCotation();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void SupprimerDate(ActionEvent event) {
        Connect();

        Periode selectedPeriode = tabPeriode.getSelectionModel().getSelectedItem();
        if (selectedPeriode == null) {
            // Aucune période sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune période sélectionnée");
            alert.setContentText("Veuillez sélectionner une période dans le tableau.");
            alert.showAndWait();
            return;
        }

        LocalDate datePeriode = selectedPeriode.getDate();

        Date date;
        date = Date.valueOf(txtNomDatePeriode.getValue());

        try {
            pst = con.prepareStatement("DELETE FROM periode WHERE date = ?");
            pst.setDate(1, Date.valueOf(date.toLocalDate()));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression Date");
            alert.setHeaderText("Date Suppression");
            alert.setContentText("La période sélectionnée a été supprimée!");
            alert.showAndWait();

            tabPeriode(); // Met à jour la table des périodes après la suppression de la date.
        } catch (SQLException ex) {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }

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


    public void tabPeriode() {
        Connect();
        ObservableList<Periode> periodes = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select date from periode");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Periode per = new Periode();
                per.setDate(rs.getDate("date").toLocalDate());
                periodes.add(per);
            }
            tabPeriode.setItems(periodes);
            datePeriodeColmn.setCellValueFactory(f -> f.getValue().dateProperty());


        } catch (SQLException ex) {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tabPeriode.setRowFactory(tv -> {
            TableRow<Periode> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tabPeriode.getSelectionModel().getSelectedIndex();
                    txtNomDatePeriode.setValue(tabPeriode.getItems().get(myIndex).getDate());
                }
            });
            return myRow;
        });
    }

    @FXML
    public void tabCotation() {
        Connect();
        ObservableList<ObservableList<Object>> cotations = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("SELECT nomTitre, prix, date FROM cotation");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nomTitre = rs.getString("nomTitre");
                double prix = rs.getDouble("prix");
                LocalDate date = rs.getDate("date").toLocalDate();

                // Create an ObservableList to represent a row in the TableView
                ObservableList<Object> row = FXCollections.observableArrayList();
                row.add(nomTitre);
                row.add(prix);
                row.add(date);

                // Add the row to the list of cotations
                cotations.add(row);
            }

            // Set the items in the TableView
            tabCotation.setItems(cotations);

            // Configurez les cellules pour chaque colonne
            titreCotColmn.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get(0)));
            prixCotColmn.setCellValueFactory(cellData -> new SimpleDoubleProperty((double) cellData.getValue().get(1)).asObject());
            dateCotColmn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((LocalDate) cellData.getValue().get(2)));

            // Define the row factory to handle row clicks
            tabCotation.setRowFactory(tv -> {
                TableRow<ObservableList<Object>> myRow = new TableRow<>();
                myRow.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                        // Get the selected row's data
                        ObservableList<Object> rowData = myRow.getItem();
                        String nomTitre = (String) rowData.get(0);
                        double prix = (double) rowData.get(1);
                        LocalDate date = (LocalDate) rowData.get(2);

                        txtNomTitreCoter.setValue(nomTitre);
                        txtPrixTitreCoter.setText(String.valueOf(prix));
                        txtDateTitreCoter.setValue(String.valueOf(date));

                        // Use the data as needed
                        System.out.println("Titre: " + nomTitre + ", Prix: " + prix + ", Date: " + date);
                    }
                });
                return myRow;
            });

        } catch (SQLException ex) {
            Logger.getLogger(PeriodeCotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        tabPeriode();
        tabCotation();

        // Remplir le ComboBox txtCatTitre avec les éléments du champ nomCat de la table categorie
        ObservableList<String> titres = getAllTitresFromDB();
        txtNomTitreCoter.setItems(titres);

        // Remplir le ComboBox txtMarcheTitre avec les éléments du champ nomMar de la table Marche
        ObservableList<String> periodes = getAllPeriodesFromDB();
        txtDateTitreCoter.setItems(periodes);
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


}
