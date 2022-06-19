module edu.pjatk.tpo.msgappjsm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires commons.validator;

    opens edu.pjatk.tpo.msgappjsm to javafx.fxml;
    exports edu.pjatk.tpo.msgappjsm;
}