module edu.pjatk.tpo.msgappjsm {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.validator;
    requires javax.jms.api;
    requires java.naming;
    requires net.synedra.validatorfx;

    opens edu.pjatk.tpo.msgappjsm to javafx.fxml;
    exports edu.pjatk.tpo.msgappjsm;
}