package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Recipe;
import ch.zhaw.pm2.secretrecipe.model.Session;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

/**
 * This class is the controller of the detail view, where the details of a
 * recipe are shown.
 */
public class DetailController implements ControlledScreens {
    private Recipe recipe;
    private HashMap<String, Parent> screens = new HashMap<>();
    private DataManager dataManager;
    private Session session;

    @FXML
    private Label authorizedLabel;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<String> authorizedUsersListView;

    @FXML
    private Label recipeNameLabel;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextArea ingredientsTextArea;

    @FXML
    private AnchorPane root;

    /**
     * gets the {@link DataManager} instance and {@link Session} instance.
     * it also changes the view and loads the information from the recipe.
     */
    @FXML
    public void initialize() {
        dataManager = DataManager.getInstance();
        session = Session.getInstance();
        StartController.recipeClickedProperty().addListener((observable) -> {
            recipe = StartController.getClickedRecipe();
            changeControlsVisibility();
            loadInformationFromRecipe();
        });
    }

    @FXML
    private void backToLastView() {
        StartController.setRecipeClicked(false);
        setNewScene(Config.START);
    }

    @FXML
    private void editRecipe() {
        setNewScene(Config.NEWRECIPE);
    }

    @FXML
    private void deleteRecipe() {
        dataManager.deleteRecipe(recipe);
    }

    private void loadInformationFromRecipe() {
        recipeNameLabel.setText(recipe.getName());
        ingredientsTextArea.setText(recipe.getIngredients());
        descriptionTextArea.setText(recipe.getDescription());
        for (User authorizedUser : dataManager.getAuthorizedUserList(recipe)) {
            authorizedUsersListView.getItems().add(authorizedUser.getUsername());
        }
    }

    private void changeControlsVisibility() {
        boolean visibility = session.getLoggedInUser().getUsername().equals(recipe.getOwner().getUsername());
        authorizedLabel.setVisible(visibility);
        editButton.setVisible(visibility);
        deleteButton.setVisible(visibility);
        authorizedUsersListView.setVisible(visibility);
    }

    private void setNewScene(String view) {
        root.getScene().setRoot(screens.get(view));
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
