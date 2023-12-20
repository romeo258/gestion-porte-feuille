package com.tp.portefeuille;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Marche {

    private final StringProperty nomMar;
    private final StringProperty libMar;

    public Marche()
    {
        nomMar = new SimpleStringProperty(this, "nomMar");
        libMar = new SimpleStringProperty(this, "libMar");
    }
    public StringProperty nomMarProperty() { return nomMar; }
    public String getNomMar() { return nomMar.get(); }
    public void setNomMar(String newNomMar) { nomMar.set(newNomMar); }


    public StringProperty libMarProperty() { return libMar; }
    public String getLibMar() { return libMar.get(); }
    public void setLibMar(String newLibMar) { libMar.set(newLibMar); }
}
