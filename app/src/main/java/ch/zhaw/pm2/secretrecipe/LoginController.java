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
    private List<User> userList = new ArrayList<>();
    User user;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private AnchorPane root;

    @FXML
    void loginUser(ActionEvent event) {
        try {
            String userName = usernameField.getText();
            String password = passwordField.getText();
            if(!userList.contains(user)) {
                throw new InvalidUserEntry("Invalid user entry!");
            }
        } catch (InvalidUserEntry exception) {

        }
    }

    @FXML
    void switchToRegisterView(ActionEvent event) {
        root.getScene().setRoot(screens.get(App.REGISTRATION));
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }

}
