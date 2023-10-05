module com.btssio.gestionadherents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.btssio.gestionadherents to javafx.fxml;
    exports com.btssio.gestionadherents;
}