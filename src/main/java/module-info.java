module com.btssio.gestionadherents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.glassfish.jaxb.runtime;
    requires org.apache.pdfbox;

    opens com.btssio.gestionadherents to javafx.fxml;
    opens com.btssio.models.adherent to org.glassfish.jaxb.runtime, jakarta.xml.bind;
    opens com.btssio.models.tarif to org.glassfish.jaxb.runtime, jakarta.xml.bind;
    opens com.btssio.models.utils to org.glassfish.jaxb.runtime, jakarta.xml.bind;
    exports com.btssio.gestionadherents;
    exports com.btssio.models.adherent;
    exports com.btssio.models.tarif;
    exports com.btssio.models.utils;
}
