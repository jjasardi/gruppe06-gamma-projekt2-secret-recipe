package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class NewRecipeController {
    private User user;
    private Recipe recipe;
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
    void addPerson(ActionEvent event) {
        //TODO 1. rezept bei user einfügen [], 2. authorizedUsers updaten via listener []
        String recipeName = recipeName.getBytes(StandardCharsets.UTF_8);
        Recipe currentRecipe = new Recipe();
        user.getRecipeListe().add(currentRecipe);
    }

    @FXML
    void backToLastView(ActionEvent event) {
        root.getScene().setRoot(screens.get(App.START));
    }

    @FXML
    void deleteDraftRecipe(ActionEvent event) {
        root.getScene().setRoot(screens.get(App.START));
    }

    @FXML
    void removePerson(ActionEvent event) {
        //TODO 1. rezept bei user entfernen [], 2. authorizedUsers updaten via listener []
    }

    @FXML
    void saveRecipe(ActionEvent event) {
        //TODO mit file streams rezept objekt in file speichern
    }
}
