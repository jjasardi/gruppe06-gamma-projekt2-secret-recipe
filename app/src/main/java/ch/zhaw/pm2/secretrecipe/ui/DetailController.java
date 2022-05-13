package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Recipe;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class DetailController implements ControlledScreens {
    private Recipe recipe;
    private User user;
    private HashMap<String, Parent> screens = new HashMap<>();
    private DataManager dataManager;

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

    @FXML
    public void initialize() {
        dataManager = DataManager.getInstance();
        StartController.recipeClickedProperty().addListener((observable) -> {
            recipe = StartController.getClickedRecipe();
        });
    }

    @FXML
    void addUser(ActionEvent event) {
        user.getRecipeListe().add(recipe);
    }

    @FXML
    void removeUser(ActionEvent event) {
        user.getRecipeListe().remove(recipe);
    }

    @FXML
    void backToLastView(ActionEvent event) {
        StartController.setRecipeClicked(false);
        root.getScene().setRoot(screens.get(Config.START));
    }

    @FXML
    void editRecipeDescribtion(ActionEvent event) {
        // ingredients.setEditable(true);
        // description.setEditable(true);
    }
    
    @FXML
    public void deleteRecipe(ActionEvent actionEvent) {
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

    // void setIngredientsText() {
    //     ingredients.setEditable(false);
    //     ingredients.setText(recipe.getIngredients());
    // }

    // void setDescription() {
    //     description.setEditable(false);
    //     description.setText(recipe.getDescription());
    // }

    // String getIngredients() {
    //     return ingredients.getText();
    // }

    // String getDescription() {
    //     return description.getText();
    // }

    // void printAuthorizedUsers(List<User> users) {
    //     for (User user : users) {
    //         if (user.getRecipeListe().contains(recipe)) {
    //             authorizedUsers.setText(user.getUsername() + "\n");
    //         }
    //     }
    // }

    // void setRecipeName(Recipe recipe) {
    //     recipeName.setText(recipe.getName());
    // }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
