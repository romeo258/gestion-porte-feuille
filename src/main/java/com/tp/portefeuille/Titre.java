package com.tp.portefeuille;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Titre {

    private final StringProperty nomTitre;
    private final StringProperty libTitre;
    private final StringProperty nomCat;
    private final StringProperty nomMar;

    public Titre()
    {
        nomTitre = new SimpleStringProperty(this, "nomTitre");
        libTitre = new SimpleStringProperty(this, "libTitre");
        nomCat = new SimpleStringProperty(this, "nomCat");
        nomMar = new SimpleStringProperty(this, "nomMar");
    }
    public StringProperty nomTitreProperty() { return nomTitre; }
    public String getNomTitre() { return nomTitre.get(); }
    public void setNomTitre(String newNomTitre) { nomTitre.set(newNomTitre); }


    public StringProperty libTitreProperty() { return libTitre; }
    public String getLibTitre() { return libTitre.get(); }
    public void setLibTitre(String newLibTitre) { libTitre.set(newLibTitre); }


    public StringProperty nomCatProperty() { return nomCat; }
    public String getNomCat() { return nomCat.get(); }
    public void setNomCat(String newNomCat) { nomCat.set(newNomCat); }


    public StringProperty nomMarProperty() { return nomMar; }
    public String getNomMar() { return nomMar.get(); }
    public void setNomMar(String newNomMar) { nomMar.set(newNomMar); }

}


