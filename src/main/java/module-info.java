module edu.pjatk.tpo.msgappjsm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens edu.pjatk.tpo.msgappjsm to javafx.fxml;
    exports edu.pjatk.tpo.msgappjsm;
}