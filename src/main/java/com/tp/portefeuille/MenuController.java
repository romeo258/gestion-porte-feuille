package com.tp.portefeuille;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button btnClients;

    @FXML
    private Button btnMarCat;

    @FXML
    private Button btnPeriodCot;

    @FXML
    private Button btnProprioNego;

    @FXML
    private Button btnTitres;

    @FXML
    void Clients(ActionEvent event) {

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre pour le fichier pfeuille.fxml
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Afficher la nouvelle fenêtre (la fenêtre actuelle ne sera pas fermée)
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void MarCat(ActionEvent event) {

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pfeuille.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre pour le fichier pfeuille.fxml
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Afficher la nouvelle fenêtre (la fenêtre actuelle ne sera pas fermée)
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void PeriodCot(ActionEvent event) {

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("periodeCotation.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre pour le fichier titre.fxml
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Afficher la nouvelle fenêtre (la fenêtre actuelle ne sera pas fermée)
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    void ProprioNego(ActionEvent event) {

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("negoProprio.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre pour le fichier titre.fxml
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Afficher la nouvelle fenêtre (la fenêtre actuelle ne sera pas fermée)
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Titres(ActionEvent event) {

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("titres.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre pour le fichier titre.fxml
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Afficher la nouvelle fenêtre (la fenêtre actuelle ne sera pas fermée)
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
