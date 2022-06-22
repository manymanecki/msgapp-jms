module edu.pjatk.tpo.msgappjsm {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.validator;
    requires javax.jms.api;
    requires java.naming;

    opens edu.pjatk.tpo.msgappjsm to javafx.fxml;
    exports edu.pjatk.tpo.msgappjsm;
}