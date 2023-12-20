package com.tp.portefeuille;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Periode {
    private final ObjectProperty<LocalDate> date;

    public Periode() {
        date = new SimpleObjectProperty<>(this, "date");
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate newDate) {
        date.set(newDate);
    }
}
