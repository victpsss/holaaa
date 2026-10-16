module com.example.acunan {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.acunan to javafx.fxml;
    opens com.example.acunan.controller to javafx.fxml;
    opens com.example.acunan.Dto to javafx.base;

    exports com.example.acunan;
    exports com.example.acunan.controller;
}