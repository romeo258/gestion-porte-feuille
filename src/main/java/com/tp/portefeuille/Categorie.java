package com.tp.portefeuille;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Categorie {

    private final StringProperty nomCat;
    private final StringProperty libCat;

    public Categorie()
    {
        nomCat = new SimpleStringProperty(this, "nomCat");
        libCat = new SimpleStringProperty(this, "libCat");
    }
    public StringProperty nomCatProperty() { return nomCat; }
    public String getNomCat() { return nomCat.get(); }
    public void setNomCat(String newNomCat) { nomCat.set(newNomCat); }


    public StringProperty libCatProperty() { return libCat; }
    public String getLibCat() { return libCat.get(); }
    public void setLibCat(String newLibCat) { libCat.set(newLibCat); }
}
