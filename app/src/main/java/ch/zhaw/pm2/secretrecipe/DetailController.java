package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;

import java.util.HashMap;
import java.util.List;

public class DetailController {
    private Recipe recipe;
    private User user;
    private HashMap<String, Parent> screens = new HashMap<>();

    @FXML
    private TextArea authorizedUsers;

    @FXML
    private TextArea description;

    @FXML
    private TextArea ingredients;

    @FXML
    private TextField recipeName;

    @FXML
    private AnchorPane root;

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
        root.getScene().setRoot(screens.get(App.START));
    }

    @FXML
    void editRecipeDescribtion(ActionEvent event) {
        ingredients.setEditable(true);
        description.setEditable(true);
    }

    void setIngredientsText () {
        ingredients.setEditable(false);
        ingredients.setText(recipe.getIngredients());
    }

    void setDescription () {
        description.setEditable(false);
        description.setText(recipe.getDescription());
    }

    String getIngredients() {
        return ingredients.getText();
    }

    String getDescription() {
        return description.getText();
    }

    void printAuthorizedUsers(User user) {
        List<Recipe> reicpesForUser = user.getRecipeListe();

        if (reicpesForUser.contains(recipe)) {
            authorizedUsers.setText(user.getUsername() + "\n");
        }
    }

    void setRecipeName(Recipe recipe) {
        recipeName.setText(recipe.getName());
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }

}
