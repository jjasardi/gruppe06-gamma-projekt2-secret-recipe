package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailController {
    Recipe recipe;
    User user;

    @FXML
    private TextArea authorizedPersons;

    @FXML
    private TextArea description; //DONE

    @FXML
    private TextArea ingredients; //DONE

    @FXML
    private TextField recipeName;

    @FXML
    private Menu showHelp;

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

    }

    void setIngredientsText () {
        ingredients.setText(recipe.getIngredients());
    }

    void setDescription () {
        description.setText(recipe.getDescription());
    }

    String getIngredients() {
        return ingredients.getText();
    }

    String getDescription() {
        return description.getText();
    }

    void showHelp(){
    }
}
