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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class NewRecipeController implements ControlledScreens {
    private HashMap<String, Parent> screens = new HashMap<>();
    private DataManager dataManager;
    private Session session;
    private ObservableList<String> enteredAuthorizedUsers = FXCollections.observableArrayList();
    private Recipe recipe;
    private boolean editMode = false;

    @FXML
    private ListView<String> authorizedUsersListView = new ListView<>();

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField userToAuthorizeTextField;

    @FXML
    private TextArea ingredientsTextArea;

    @FXML
    private TextField recipeNameTextField;

    @FXML
    private Button addUserToAuthorizeListButton;

    @FXML
    private Button removeUserFromAuthorizeListButton;

    @FXML
    private AnchorPane root;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        dataManager = DataManager.getInstance();
        session = Session.getInstance();
        authorizedUsersListView.setItems(enteredAuthorizedUsers);
        removeUserFromAuthorizeListButton.disableProperty()
                .bind(Bindings.isEmpty(userToAuthorizeTextField.textProperty()));

        saveButton.disableProperty().bind(Bindings.isEmpty(recipeName.textProperty())
                .or(Bindings.isEmpty(ingredients.textProperty()))
                .or(Bindings.isEmpty(description.textProperty())));
      
        StartController.recipeClickedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                editMode = true;
                recipe = StartController.getClickedRecipe();
                loadInformationFromRecipe();
            } else {
                recipe = null;
                editMode = false;
            }
        });
    }

    @FXML
    void cancel(ActionEvent event) {
        clearText();
        if (editMode) {
            setNewScene(Config.DETAIL);
        } else {
            setNewScene(Config.START);
        }
    }

    @FXML
    void saveRecipe(ActionEvent event) {
        String nameRecipe = recipeNameTextField.getText();
        String ingredientsRecipe = ingredientsTextArea.getText();
        String descriptionRecipe = descriptionTextArea.getText();
        if (editMode) {
            updateRecipe(nameRecipe, ingredientsRecipe, descriptionRecipe);
        } else {
            createRecipe(nameRecipe, ingredientsRecipe, descriptionRecipe);
        }
        if (editMode) {
            setNewScene(Config.DETAIL);
        } else {
            setNewScene(Config.START);
        }
    }

    private void createRecipe(String nameRecipe, String ingredientsRecipe, String descriptionRecipe) {
        Recipe currentRecipe = new Recipe(
                dataManager.getNewId(), nameRecipe, ingredientsRecipe, descriptionRecipe,
                session.getLoggedInUser());
        dataManager.addRecipe(currentRecipe);
        addRecipeAuthorizationOnUsers();
    }

    private void updateRecipe(String nameRecipe, String ingredientsRecipe, String descriptionRecipe) {
        recipe.setName(nameRecipe);
        recipe.setIngredients(ingredientsRecipe);
        recipe.setDescription(descriptionRecipe);
        updateRecipeAuthorizationOnUsers();
    }

    private void updateRecipeAuthorizationOnUsers() {
        dataManager.getUserList().forEach(userOnList -> userOnList.removeRecipeAuthorization(recipe));
        addRecipeAuthorizationOnUsers();
    }

    private void addRecipeAuthorizationOnUsers() {
        for (String userName : authorizedUsersListView.getItems()) {
            for (User user : dataManager.getUserList()) {
                if (user.getUsername().equals(userName)) {
                    user.addRecipeAuthorization(recipe);
                }
            }
        }
    }

    private void loadInformationFromRecipe() {
        recipeNameTextField.setText(recipe.getName());
        ingredientsTextArea.setText(recipe.getIngredients());
        descriptionTextArea.setText(recipe.getDescription());
        for (User authorizedUser : dataManager.getAuthorizedUserList(recipe)) {
            authorizedUsersListView.getItems().add(authorizedUser.getUsername());
        }
        clearText();
        root.getScene().setRoot(screens.get(Config.START));
    }

    private void clearText() {
        TextInputControl[] contents = { recipeNameTextField, ingredientsTextArea, descriptionTextArea };
        for (TextInputControl content : contents) {
            content.clear();
        }
    }

    @FXML
    void addUserToAuthorizedUsersListView(ActionEvent event) {
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
    private void removeUserFromAuthorizedUsersListView() {
        int selectedIndex = authorizedUsersListView.getSelectionModel().getSelectedIndex();
        enteredAuthorizedUsers.remove(selectedIndex);
    }

    private boolean manageEmptyInput() {
        boolean isEmpty = false;
        TextInputControl[] contents = { recipeNameTextField, ingredientsTextArea, descriptionTextArea };
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

    private void setNewScene(String view) {
        root.getScene().setRoot(screens.get(view));
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
