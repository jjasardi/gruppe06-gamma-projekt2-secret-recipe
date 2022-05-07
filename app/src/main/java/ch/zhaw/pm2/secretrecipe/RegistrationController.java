package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class RegistrationController implements ControlledScreens {
    private HashMap<String, Parent> screens = new HashMap<>();
    private DataManager dataManager;
    private Session session;

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
    public void initialize() {
        dataManager = DataManager.getInstance();
        session = Session.getInstance();
    }

    @FXML
    private void backToLoginView(ActionEvent event) {
        root.getScene().setRoot(screens.get(Config.LOGIN));
    }

    @FXML
    private void registerUser(ActionEvent event) {
        String username = usernameField.getText();
        String firstname = fistnameField.getText();
        String surname = surnameField.getText();
        String password = passwordField.getText();
        if (!isUsernameTaken(username)) {
            User newUser = new User(firstname, surname, username, password);
            dataManager.addUser(newUser);
            session.setLoggedInUser(newUser);
            goToStartView();
        }
    }

    private void goToStartView() {
        root.getScene().setRoot(screens.get(Config.START));
    }

    private boolean isUsernameTaken(String username) {
        for (User registeredUser : dataManager.getUserList()) {
            if (username.equals(registeredUser.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
