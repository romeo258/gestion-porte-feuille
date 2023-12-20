module com.tp.portefeuille {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.tp.portefeuille to javafx.fxml;
    exports com.tp.portefeuille;
}