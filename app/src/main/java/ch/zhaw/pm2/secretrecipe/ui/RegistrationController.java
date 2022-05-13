package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Session;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
    private Button registrationButton;

    public void initialize() {
        dataManager = DataManager.getInstance();
        session = Session.getInstance();
        registrationButton.disableProperty().bind(Bindings.isEmpty(usernameField.textProperty())
                .or(Bindings.isEmpty(fistnameField.textProperty()))
                .or(Bindings.isEmpty(surnameField.textProperty()))
                .or(Bindings.isEmpty(passwordField.textProperty())));
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

    private static void errorInfoEmpty(Color color, TextInputControl content) {
        content.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (content.getText().equals("")) {
            content.setPromptText("Bitte nicht leer lassen!");
        }
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
