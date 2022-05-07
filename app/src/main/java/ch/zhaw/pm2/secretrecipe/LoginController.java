package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginController implements ScreenController{
    private HashMap<String, Parent> screens = new HashMap<>();
    private DataManager dataManager;
    private Session session;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private AnchorPane root;

    @FXML
    void loginUser(ActionEvent event) {
        try {
            dataManager = dataManager.getInstance();
            session = session.getInstance();
            String userName = usernameField.getText();
            String password = passwordField.getText();
            for(User user : dataManager.getUserList()) {
                if(user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                    session.setLoggedInUser(user);
                    root.getScene().setRoot(screens.get(Config.START));
                }
            }

            throw new InvalidUserEntry("Invalid user entry!");
            
        } catch (InvalidUserEntry exception) {
            usernameField.setText("Falscher Benutzername!");
            passwordField.setText("Falsches Passwort!");
        }
    }

    @FXML
    void switchToRegisterView(ActionEvent event) {
        root.getScene().setRoot(screens.get(Config.REGISTRATION));
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }

}
