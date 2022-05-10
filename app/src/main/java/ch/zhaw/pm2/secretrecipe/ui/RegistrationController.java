package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Session;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
        if(!manageEmptyInput()) {
            if (!isUsernameTaken(username)) {
                User newUser = new User(firstname, surname, username, password);
                dataManager.addUser(newUser);
                session.setLoggedInUser(newUser);
                goToStartView();
            }
        }
    }

    private boolean manageEmptyInput() {
        boolean emptyInput = false;
        TextInputControl[] contents = {usernameField, fistnameField, surnameField, passwordField};
        for(TextInputControl content : contents) {
            if(content.getText().equals("")) {
                emptyInput = true;
                errorInfoEmpty(Color.RED, content);
            }
        }
        return emptyInput;
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
