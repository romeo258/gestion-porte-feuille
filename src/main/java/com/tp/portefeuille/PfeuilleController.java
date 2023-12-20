package com.tp.portefeuille;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PfeuilleController implements Initializable {

    @FXML
    private Button btnAjouterCat;

    @FXML
    private Button btnAjouterMar;

    @FXML
    private Button btnModifierCat;

    @FXML
    private Button btnModifierMar;

    @FXML
    private Button btnSupprimerCat;

    @FXML
    private Button btnSupprimerMar;

    @FXML
    private TableColumn<Categorie, String> nomCatColmn;

    @FXML
    private TableColumn<Marche, String> nomMarColmn;

    @FXML
    private TableColumn<Categorie, String> refCatColmn;

    @FXML
    private TableColumn<Marche, String> refMarColmn;

    @FXML
    private TableView<Categorie> tabCategorie;

    @FXML
    private TableView<Marche> tabMarche;

    @FXML
    private TextField txtLibCat;

    @FXML
    private TextField txtLibMar;

    @FXML
    private TextField txtNomCat;

    @FXML
    private TextField txtNomMar;

    @FXML
    void AjouterCat(ActionEvent event) {

        Connect();

        String nomCat,libelleCat;
        nomCat = txtNomCat.getText();
        libelleCat = txtLibCat.getText();
        try
        {
            pst = con.prepareStatement("insert into categorie(nomCat,libCat)values(?,?)");
            pst.setString(1, nomCat);
            pst.setString(2, libelleCat);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement Catégorie Titre");

            alert.setHeaderText("Categorie Registation");
            alert.setContentText("Ajouter!");
            alert.showAndWait();
            tabCategorie();

            txtNomCat.setText("");
            txtLibCat.setText("");
            txtNomCat.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void AjouterMar(ActionEvent event) {
        Connect();

        String nomMar,libelleMar;
        nomMar = txtNomMar.getText();
        libelleMar = txtLibMar.getText();
        try
        {
            pst = con.prepareStatement("insert into marche(nomMar,libMar)values(?,?)");
            pst.setString(1, nomMar);
            pst.setString(2, libelleMar);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement Marché / Catégorie Titre");

            alert.setHeaderText("Marché Registation");
            alert.setContentText("Record Addedddd!");
            alert.showAndWait();
            tabMarche();

            txtNomMar.setText("");
            txtLibMar.setText("");
            txtNomMar.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tabMarche()
    {
        Connect();
        ObservableList<Marche> marches = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select nomMar,libMar from Marche");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Marche mar = new Marche();
                    mar.setNomMar(rs.getString("nomMar"));
                    mar.setLibMar(rs.getString("libMar"));
                    marches.add(mar);
                }
            }
            tabMarche.setItems(marches);
            refMarColmn.setCellValueFactory(f -> f.getValue().nomMarProperty());
            nomMarColmn.setCellValueFactory(f -> f.getValue().libMarProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabMarche.setRowFactory( tv -> {
            TableRow<Marche> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tabMarche.getSelectionModel().getSelectedIndex();

                    txtNomMar.setText(tabMarche.getItems().get(myIndex).getNomMar());
                    txtLibMar.setText(tabMarche.getItems().get(myIndex).getLibMar());
                }
            });
            return myRow;
        });


    }

    public void tabCategorie()
    {
        Connect();
        ObservableList<Categorie> categories = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select nomCat,libCat from Categorie");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Categorie cat = new Categorie();
                    cat.setNomCat(rs.getString("nomCat"));
                    cat.setLibCat(rs.getString("libCat"));
                    categories.add(cat);
                }
            }
            tabCategorie.setItems(categories);
            refCatColmn.setCellValueFactory(f -> f.getValue().nomCatProperty());
            nomCatColmn.setCellValueFactory(f -> f.getValue().libCatProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabCategorie.setRowFactory( tv -> {
            TableRow<Categorie> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tabCategorie.getSelectionModel().getSelectedIndex();

                    txtNomCat.setText(tabCategorie.getItems().get(myIndex).getNomCat());
                    txtLibCat.setText(tabCategorie.getItems().get(myIndex).getLibCat());
                }
            });
            return myRow;
        });


    }






    @FXML
    void ModifierCat(ActionEvent event) {

        String nomCat,libelleCat;

        myIndex = tabCategorie.getSelectionModel().getSelectedIndex();

        nomCat = txtNomCat.getText();
        libelleCat = txtLibCat.getText();
        try
        {
            pst = con.prepareStatement("update Categorie set libCat = ? where nomCat = ? ");
            pst.setString(1, libelleCat);
            pst.setString(2, nomCat);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Categorie Registation");

            alert.setHeaderText("Categorie Registation");
            alert.setContentText("Mis A jour!");
            alert.showAndWait();
            tabCategorie();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void ModifierMar(ActionEvent event) {

        String nomMar,libelleMar;

        myIndex = tabMarche.getSelectionModel().getSelectedIndex();

        nomMar = txtNomMar.getText();
        libelleMar = txtLibMar.getText();
        try
        {
            pst = con.prepareStatement("update marche set libMar = ? where nomMar = ? ");
            pst.setString(1, libelleMar);
            pst.setString(2, nomMar);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Marché Registationn");

            alert.setHeaderText("Marché Registation");
            alert.setContentText("Mis A jour!");
            alert.showAndWait();
            tabMarche();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void SupprimerCat(ActionEvent event) {

        String nomCat;

        myIndex = tabCategorie.getSelectionModel().getSelectedIndex();

        nomCat = txtNomCat.getText();

        try
        {
            pst = con.prepareStatement("delete from categorie where nomCat = ? ");
            pst.setString(1, nomCat);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Categorie Registationn");

            alert.setHeaderText("Categorie Registation");
            alert.setContentText("Supprimer!");
            alert.showAndWait();
            tabCategorie();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void SupprimerMar(ActionEvent event) {

        String nomMar;

        myIndex = tabMarche.getSelectionModel().getSelectedIndex();

        nomMar = txtNomMar.getText();

        try
        {
            pst = con.prepareStatement("delete from marche where nomMar = ? ");
            pst.setString(1, nomMar);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Marché Registationn");

            alert.setHeaderText("Marché Registation");
            alert.setContentText("Supprimer!");
            alert.showAndWait();
            tabMarche();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        tabMarche();
        tabCategorie();
    }

}
