package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Session;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * This class is the controller of the Registration view.
 */
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
    private Button registrationButton;

    /**
     * gets the {@link DataManager} instance and {@link Session} instance.
     * it also deactivates the button when needed fields are empty.
     */
    @FXML
    public void initialize() {
        dataManager = DataManager.getInstance();
        session = Session.getInstance();
        registrationButton.disableProperty().bind(Bindings.isEmpty(usernameField.textProperty())
                .or(Bindings.isEmpty(fistnameField.textProperty()))
                .or(Bindings.isEmpty(surnameField.textProperty()))
                .or(Bindings.isEmpty(passwordField.textProperty())));
    }

    @FXML
    private void backToLoginView() {
        root.getScene().setRoot(screens.get(Config.LOGIN));
    }

    @FXML
    private void registerUser() {
        String username = usernameField.getText();
        String firstname = fistnameField.getText();
        String surname = surnameField.getText();
        String password = passwordField.getText();
        if (!isUsernameTaken(username)) {
            initialize();
            User newUser = new User(firstname, surname, username, password);
            dataManager.addUser(newUser);
            session.setLoggedInUser(newUser);
            goToStartView();
        } else {
            handleTakenUsername();
        }
    }

    private void handleTakenUsername() {
        usernameField.clear();
        usernameField.setPromptText("Benutzername bereits verwendet!");
        usernameField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    private void goToStartView() {
        root.getScene().setRoot(screens.get(Config.START));
    }

    private boolean isUsernameTaken(String username) {
        boolean isUsernameTaken = false;
        for (User registeredUser : dataManager.getUserList()) {
            if (username.equals(registeredUser.getUsername())) {
                isUsernameTaken = true;
            }
        }
        return isUsernameTaken;
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
