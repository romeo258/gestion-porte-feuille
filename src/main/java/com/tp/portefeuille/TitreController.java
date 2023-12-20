package com.tp.portefeuille;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TitreController implements Initializable {

    @FXML
    private TableColumn<Titre, String> MarcheTitreColmn;

    @FXML
    private Button btnAjouterTitre;

    @FXML
    private Button btnModifierMar1;

    @FXML
    private Button btnModifierTitre;

    @FXML
    private Button btnSupprimerTitre;

    @FXML
    private TableColumn<Titre, String> categorieTitreColmn;

    @FXML
    private TableColumn<Titre, String> nomTitreColmn;

    @FXML
    private TableColumn<Titre, String> refTitreColmn;

    @FXML
    private TableView<Titre> tabTitre;

    @FXML
    private ComboBox<String> txtCatTitre;

    @FXML
    private TextField txtLibTitre;

    @FXML
    private ComboBox<String> txtMarcheTitre;

    @FXML
    private TextField txtNomTitre;

    @FXML
    void AjouterTitre(ActionEvent event) {

        String nomTitre,libelleTitre, nomCat, nomMarTitre;
        nomTitre = txtNomTitre.getText();
        libelleTitre = txtLibTitre.getText();
        nomCat = txtCatTitre.getValue();
        nomMarTitre = txtMarcheTitre.getValue();
        try
        {
            pst = con.prepareStatement("insert into titre(nomTitre,libTitre, nomMar, nomCat)values(?,?,?,?)");
            pst.setString(1, nomTitre);
            pst.setString(2, libelleTitre);
            pst.setString(3, nomMarTitre);
            pst.setString(4, nomCat);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement Titre");

            alert.setHeaderText("Titre Registation");
            alert.setContentText("Record Enregistrer!");
            alert.showAndWait();
            tabTitre();

            txtNomTitre.setText("");
            txtLibTitre.setText("");
            txtCatTitre.setValue("");
            txtMarcheTitre.setValue("");
            txtNomTitre.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void ModifierMar(ActionEvent event) {

    }

    @FXML
    void ModifierTitre(ActionEvent event) {

        String nomTitre, libelleTitre, nomCat, nomMarTitre;

        myIndex = tabTitre.getSelectionModel().getSelectedIndex();

        nomTitre = txtNomTitre.getText();
        libelleTitre = txtLibTitre.getText();
        nomCat = txtCatTitre.getValue();
        nomMarTitre = txtMarcheTitre.getValue();
        try
        {
            pst = con.prepareStatement("update titre set libTitre = ?, nomMar = ?, nomCat = ? where nomTitre = ? ");
            pst.setString(1, libelleTitre);
            pst.setString(2, nomMarTitre);
            pst.setString(3, nomCat);
            pst.setString(4, nomTitre);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Titre Registation");

            alert.setHeaderText("Titre Registation");
            alert.setContentText("Mis A jour!");
            alert.showAndWait();
            tabTitre();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void SupprimerTitre(ActionEvent event) {

        String nomTitre;

        myIndex = tabTitre.getSelectionModel().getSelectedIndex();

        nomTitre = txtNomTitre.getText();

        try
        {
            pst = con.prepareStatement("delete from titre where nomTitre = ? ");
            pst.setString(1, nomTitre);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Titre Registationn");

            alert.setHeaderText("Titre Registation");
            alert.setContentText("Supprimer!");
            alert.showAndWait();
            tabTitre();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void tabTitre()
    {
        Connect();
        ObservableList<Titre> titres = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select nomTitre,libTitre, nomCat, nomMar from titre");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Titre tir = new Titre();
                    tir.setNomTitre(rs.getString("nomTitre"));
                    tir.setLibTitre(rs.getString("libTitre"));
                    tir.setNomCat(rs.getString("nomCat"));
                    tir.setNomMar(rs.getString("nomMar"));
                    titres.add(tir);
                }
            }
            tabTitre.setItems(titres);
            refTitreColmn.setCellValueFactory(f -> f.getValue().nomTitreProperty());
            nomTitreColmn.setCellValueFactory(f -> f.getValue().libTitreProperty());
            categorieTitreColmn.setCellValueFactory(f -> f.getValue().nomCatProperty());
            MarcheTitreColmn.setCellValueFactory(f -> f.getValue().nomMarProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TitreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabTitre.setRowFactory( tv -> {
            TableRow<Titre> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tabTitre.getSelectionModel().getSelectedIndex();

                    txtNomTitre.setText(tabTitre.getItems().get(myIndex).getNomTitre());
                    txtLibTitre.setText(tabTitre.getItems().get(myIndex).getLibTitre());
                    txtCatTitre.setValue(tabTitre.getItems().get(myIndex).getNomCat());
                    txtMarcheTitre.setValue(tabTitre.getItems().get(myIndex).getNomMar());
                }
            });
            return myRow;
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        tabTitre();
        Connect(); // Assurez-vous que la connexion à la base de données est établie

        // Remplir le ComboBox txtCatTitre avec les éléments du champ nomCat de la table categorie
        ObservableList<String> categories = getAllCategoriesFromDB();
        txtCatTitre.setItems(categories);

        // Remplir le ComboBox txtMarcheTitre avec les éléments du champ nomMar de la table Marche
        ObservableList<String> marches = getAllMarchesFromDB();
        txtMarcheTitre.setItems(marches);
    }

    private ObservableList<String> getAllCategoriesFromDB() {
        ObservableList<String> categories = FXCollections.observableArrayList();

        try {
            String query = "SELECT nomCat FROM categorie";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String categoryName = rs.getString("nomCat");
                categories.add(categoryName);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return categories;
    }

    private ObservableList<String> getAllMarchesFromDB() {
        ObservableList<String> marches = FXCollections.observableArrayList();

        try {
            String query = "SELECT nomMar FROM Marche";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String marchName = rs.getString("nomMar");
                marches.add(marchName);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return marches;
    }

}

