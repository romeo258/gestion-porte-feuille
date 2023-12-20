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

public class ClientController implements Initializable {

    @FXML
    private Button btnAjouterCli;

    @FXML
    private Button btnModifierCli;

    @FXML
    private Button btnModifierMar1;

    @FXML
    private Button btnSupprimerCli;

    @FXML
    private TableColumn<Client, String> codPostColmn;

    @FXML
    private TableColumn<Client, String> nomCliColmn;

    @FXML
    private TableColumn<Client, String> rueColmn;

    @FXML
    private TableView<Client> tabClient;

    @FXML
    private TextField txtCodePos;

    @FXML
    private TextField txtNomCli;

    @FXML
    private TextField txtRueCli;

    @FXML
    private TextField txtVIlleCli;

    @FXML
    private TableColumn<Client, String> villeColmn;

    @FXML
    void AjouterCli(ActionEvent event) {

        Connect();

        String nomCli,ville, rue, codepost;
        nomCli = txtNomCli.getText();
        ville = txtVIlleCli.getText();
        rue = txtRueCli.getText();
        codepost = txtCodePos.getText();
        try
        {
            pst = con.prepareStatement("insert into client(nomCli,ville, rue, codepost)values(?,?,?,?)");
            pst.setString(1, nomCli);
            pst.setString(2, rue);
            pst.setString(3, ville);
            pst.setString(4, codepost);

            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement Client");

            alert.setHeaderText("Client Registation");
            alert.setContentText("Ajouter!");
            alert.showAndWait();
            TabClient();

            txtNomCli.setText("");
            txtVIlleCli.setText("");
            txtRueCli.setText("");
            txtCodePos.setText("");
            txtNomCli.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(PfeuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    void ModifierCli(ActionEvent event) {

        String nomCli,ville, rue, codepost;

        myIndex = tabClient.getSelectionModel().getSelectedIndex();

        nomCli = txtNomCli.getText();
        ville = txtVIlleCli.getText();
        rue = txtRueCli.getText();
        codepost = txtCodePos.getText();
        try
        {
            pst = con.prepareStatement("update client set ville = ?, rue = ?, codepost = ? where nomCli = ? ");
            pst.setString(1, ville);
            pst.setString(2, rue);
            pst.setString(3, codepost);
            pst.setString(4, nomCli);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Client Registation");

            alert.setHeaderText("Client Registation");
            alert.setContentText("Mis A jour!");
            alert.showAndWait();
            TabClient();
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
    void SupprimerCli(ActionEvent event) {

        String nomCli;

        myIndex = tabClient.getSelectionModel().getSelectedIndex();

        nomCli = txtNomCli.getText();

        try
        {
            pst = con.prepareStatement("delete from Client where nomCli = ? ");
            pst.setString(1, nomCli);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Client Registationn");

            alert.setHeaderText("Client Registation");
            alert.setContentText("Supprimer!");
            alert.showAndWait();
            TabClient();
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


    public void TabClient() {
        Connect();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select nomCli, ville, rue, codepost from Client");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Client cli = new Client();
                    cli.setNomCli(rs.getString("nomCli"));
                    cli.setVille(rs.getString("ville"));
                    cli.setRue(rs.getString("rue"));
                    cli.setCodepost(rs.getString("codepost"));
                    clients.add(cli);
                }
            }
            tabClient.setItems(clients);
            nomCliColmn.setCellValueFactory(f -> f.getValue().nomCliProperty());
            villeColmn.setCellValueFactory(f -> f.getValue().villeProperty());
            rueColmn.setCellValueFactory(f -> f.getValue().rueProperty());
            codPostColmn.setCellValueFactory(f -> f.getValue().codepostProperty());


        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabClient.setRowFactory(tv -> {
            TableRow<Client> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tabClient.getSelectionModel().getSelectedIndex();

                    txtNomCli.setText(tabClient.getItems().get(myIndex).getNomCli());
                    txtVIlleCli.setText(tabClient.getItems().get(myIndex).getVille());
                    txtRueCli.setText(tabClient.getItems().get(myIndex).getRue());
                    txtCodePos.setText(tabClient.getItems().get(myIndex).getCodepost());
                }
            });
            return myRow;
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        TabClient();
    }

}
