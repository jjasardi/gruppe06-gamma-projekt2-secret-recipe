package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.InvalidUserEntry;
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
    void loginUser(ActionEvent event) {
        try {
            if (!manageEmptyInput()) {
                dataManager = dataManager.getInstance();
                session = session.getInstance();
                String userName = usernameField.getText();
                String password = passwordField.getText();
                for (User user : dataManager.getUserList()) {
                    if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                        session.setLoggedInUser(user);
                        root.getScene().setRoot(screens.get(Config.START));
                    }
                }
                throw new InvalidUserEntry("Invalid user entry!");
            }
        } catch (InvalidUserEntry exception) {
            errorInfoInvalidEntry(Color.RED, usernameField);
            errorInfoInvalidEntry(Color.RED, passwordField);
        }
    }

    @FXML
    void switchToRegisterView(ActionEvent event) {
        root.getScene().setRoot(screens.get(Config.REGISTRATION));
    }

    private boolean manageEmptyInput() {
        boolean isEmpty = false;
        TextInputControl[] contents = {usernameField, passwordField};
        for (TextInputControl content : contents) {
            if (usernameField.getText().equals("")) {
                isEmpty = true;
                errorInfoEmpty(Color.RED, content);
            }
        }
        return isEmpty;
    }

    private static void errorInfoEmpty(Color color, TextInputControl content) {
        content.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (content.getText().equals("")) {
            content.setPromptText("Bitte nicht leer lassen!");
        }
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
