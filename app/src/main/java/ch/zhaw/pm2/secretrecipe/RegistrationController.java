package ch.zhaw.pm2.secretrecipe;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class RegistrationController implements ScreenController {
    private HashMap<String, Parent> screens = new HashMap<>();

    @FXML
    private TextField fistnameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    @FXML
    void backToLoginView(ActionEvent event) {
        root.getScene().setRoot(screens.get(App.LOGIN));
    }

    @FXML
    void registerUser(ActionEvent event) {
        String username = usernameField.getText();
        String firstname = fistnameField.getText();
        String surname = surnameField.getText();
        String password = passwordField.getText();

        User user = new User(firstname, surname, username, password);
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
