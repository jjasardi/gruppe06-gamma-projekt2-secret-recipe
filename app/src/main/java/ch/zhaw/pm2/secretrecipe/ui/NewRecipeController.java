package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Recipe;
import ch.zhaw.pm2.secretrecipe.model.Session;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class NewRecipeController implements ControlledScreens {
    private HashMap<String, Parent> screens = new HashMap<>();
    private DataManager dataManager;
    private Session session;
    private ObservableList<String> enteredAuthorizedUsers = FXCollections.observableArrayList();

    @FXML
    private ListView<String> authorizedUsersListView = new ListView<>();

    @FXML
    private TextArea description;

    @FXML
    private TextField userToAuthorizeTextField;

    @FXML
    private TextArea ingredients;

    @FXML
    private TextField recipeName;

    @FXML
    private Button addUserToAuthorizeListButton;

    @FXML
    private Button removeUserFromAuthorizeListButton;

    @FXML
    private AnchorPane root;

    @FXML
    public void initialize() {
        dataManager = DataManager.getInstance();
        session = Session.getInstance();
        authorizedUsersListView.setItems(enteredAuthorizedUsers);
        removeUserFromAuthorizeListButton.disableProperty()
                .bind(Bindings.isEmpty(userToAuthorizeTextField.textProperty()));
    }

    @FXML
    void backToLastView(ActionEvent event) {
        goToLastView();
    }

    @FXML
    void deleteDraftRecipe(ActionEvent event) {
        goToLastView();
    }

    @FXML
    void saveRecipe(ActionEvent event) {
        String nameRecipe = recipeName.getText();
        String ingredientsRecipe = ingredients.getText();
        String describtionRecipe = description.getText();

        if (!manageEmptyInput()) {
            Recipe currentRecipe = new Recipe(
                    dataManager.getNewId(), nameRecipe, ingredientsRecipe, describtionRecipe, session.getLoggedInUser());
            dataManager.addRecipe(currentRecipe);
            for (String userName : authorizedUsersListView.getItems()) {
                for (User user : dataManager.getUserList()) {
                    if (user.getUsername().equals(userName)) {
                        user.addRecipeAuthorization(currentRecipe);
                    }
                }
            }
            clearText();
            root.getScene().setRoot(screens.get(Config.START));
        }
    }

    private void clearText() {
        TextInputControl[] contents = {recipeName, ingredients, description};
        for(TextInputControl content : contents) {
            content.clear();
        }
    }

    @FXML
    void addUserToAuthorizeList(ActionEvent event) {
        String username = userToAuthorizeTextField.getText();
        if (userExists(username) && !username.equals(session.getLoggedInUser().getUsername())) {
            if (!enteredAuthorizedUsers.contains(username)) {
                enteredAuthorizedUsers.add(username);
            }
            userToAuthorizeTextField.clear();
        } else {
            TextInputControl content = userToAuthorizeTextField;
            errorInfo(Color.RED, content, "Benutzer existiert nicht");
        }
    }

    private boolean userExists(String username) {
        for (User registeredUser : dataManager.getUserList()) {
            if (username.equals(registeredUser.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void removeUserFromAuthorizeList() {
        int selectedIndex = authorizedUsersListView.getSelectionModel().getSelectedIndex();
        enteredAuthorizedUsers.remove(selectedIndex);
    }

    private boolean manageEmptyInput() {
        boolean isEmpty = false;
        TextInputControl[] contents = { recipeName, ingredients, description };
        for (TextInputControl content : contents) {
            if (content.getText().equals("")) {
                isEmpty = true;
                errorInfo(Color.RED, content, "Bitte nicht leer lassen!");
            }
        }
        return isEmpty;
    }

    private void errorInfo(Color color, TextInputControl content, String errorMessage) {
        content.setBorder(
                new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        content.setText(errorMessage);
    }

    private void goToLastView() {
        root.getScene().setRoot(screens.get(Config.START));
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
