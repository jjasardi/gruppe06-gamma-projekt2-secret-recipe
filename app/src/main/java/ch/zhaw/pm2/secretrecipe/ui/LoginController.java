package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.InvalidUserEntry;
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

public class LoginController implements ControlledScreens {
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
    private Button loginButton;

    @FXML
    void initialize() {
        dataManager = DataManager.getInstance();
        session = Session.getInstance();
        loginButton.disableProperty().bind(Bindings.isEmpty(usernameField.textProperty())
                .or(Bindings.isEmpty(passwordField.textProperty())));
    }

    @FXML
    void loginUser(ActionEvent event) {
        try {
            String userName = usernameField.getText();
            String password = passwordField.getText();
            for (User user : dataManager.getUserList()) {
                if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                    session.setLoggedInUser(user);
                    root.getScene().setRoot(screens.get(Config.START));
                    return;
                }
            }
            throw new InvalidUserEntry("Invalid user entry!");

        } catch (InvalidUserEntry exception) {
            errorInfoInvalidEntry(Color.RED, usernameField);
            errorInfoInvalidEntry(Color.RED, passwordField);
        }
    }

    @FXML
    void switchToRegisterView(ActionEvent event) {
        root.getScene().setRoot(screens.get(Config.REGISTRATION));
    }

    private static void errorInfoInvalidEntry(Color color, TextInputControl content) {
        content.clear();
        content.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        content.setPromptText("fehlerhafte Eingabe!");
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }

}
