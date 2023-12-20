package com.tp.portefeuille;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private final StringProperty nomCli;
    private final StringProperty rue;
    private final StringProperty codepost;
    private final StringProperty ville;



    public Client()
    {
        nomCli = new SimpleStringProperty(this, "nomCli");
        rue = new SimpleStringProperty(this, "rue");
        codepost = new SimpleStringProperty(this, "codepost");
        ville = new SimpleStringProperty(this, "ville");
    }
    public StringProperty nomCliProperty() { return nomCli; }
    public String getNomCli() { return nomCli.get(); }
    public void setNomCli(String newNomCli) { nomCli.set(newNomCli); }


    public StringProperty rueProperty() { return rue; }
    public String getRue() { return rue.get(); }
    public void setRue(String newRue) { rue.set(newRue); }


    public StringProperty codepostProperty() { return codepost; }
    public String getCodepost() { return codepost.get(); }
    public void setCodepost(String newCodepost) { codepost.set(newCodepost); }


    public StringProperty villeProperty() { return ville; }
    public String getVille() { return ville.get(); }
    public void setVille(String newville) { ville.set(newville); }

}
