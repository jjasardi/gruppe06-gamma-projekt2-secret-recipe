package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.List;

public class DetailController implements ControlledScreens{
    private Recipe recipe;
    private User user;
    private HashMap<String, Parent> screens = new HashMap<>();

    @FXML
    private TextArea authorizedUsers;

    @FXML
    private Label recipeName;

    @FXML
    private TextArea description;

    @FXML
    private TextArea ingredients;

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
        root.getScene().setRoot(screens.get(Config.START));
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

    void printAuthorizedUsers(List<User> users) {
        for(User user : users) {
            if (user.getRecipeListe().contains(recipe)) {
                authorizedUsers.setText(user.getUsername() + "\n");
            }
        }
    }

    void setRecipeName(Recipe recipe) {
        recipeName.setText(recipe.getName());
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }

    public void deleteRecipe(ActionEvent actionEvent) {
    }
}
