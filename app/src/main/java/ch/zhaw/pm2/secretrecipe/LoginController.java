package ch.zhaw.pm2.secretrecipe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button switchToRegisterButton;

    public void initialize() {

    }

    @FXML
    private void loginUser(){
        
    }

    @FXML
    private void switchToRegisterButton() {
        
    }

    public User getUserMatchingCredentials(String username, String password) {
        for (User user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
