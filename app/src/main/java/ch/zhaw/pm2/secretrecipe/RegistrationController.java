package ch.zhaw.pm2.secretrecipe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegistrationController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
    @FXML
    private AnchorPane registrationPane;

    @FXML
    public void initialize() {
    }

    @FXML
    private void registerUser() {
        checkIfUsernameAlreadyTaken(usernameField.getText());
    }

    private boolean checkIfUsernameAlreadyTaken(String text) {
        return false;
    }

    @FXML
    private void backToLastView() {
        goToLastView();
    }

    private void goToLastView() {
        registrationPane.getScene().setRoot(screens.get(App.LOGIN));
    }
}
