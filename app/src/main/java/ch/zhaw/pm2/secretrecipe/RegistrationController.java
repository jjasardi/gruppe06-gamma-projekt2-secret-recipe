package ch.zhaw.pm2.secretrecipe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    public void initialize() {        
    }

    @FXML
    private void registerUser() {
        checkIfUsernameAlreadyTaken(usernameField.getText());
    }

    private boolean checkIfUsernameAlreadyTaken(String text) {
        
    }
}
