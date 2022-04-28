package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewRecipeController {
    private User user;
    private Recipe recipe;
    private HashMap<String, Parent> screens = new HashMap<>();
    private List<String> entredAuthiorizedUsers = new ArrayList<>();
    private static final Logger logger = Logger.getLogger("NewRecipeLogger");

    @FXML
    private TextArea authorizedUsers;

    @FXML
    private TextArea description;

    @FXML
    private TextField entredUserToAuthorize;

    @FXML
    private TextArea ingredients;

    @FXML
    private TextField recipeName;

    @FXML
    private AnchorPane root;

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
        String usersRecipe = authorizedUsers.getText();

        if(handleNameRecipeEmpty(nameRecipe) && handleIngredientsEmpty(ingredientsRecipe) && handleDescriptionEmpty(describtionRecipe)){
            Recipe currentRecipe = new Recipe(nameRecipe, ingredientsRecipe, describtionRecipe, user);
            user.getRecipeListe().add(currentRecipe);
            root.getScene().setRoot(screens.get(App.START));
            try {
                ObjectOutputStream recipe = new ObjectOutputStream(new FileOutputStream(currentRecipe.getName()));
                recipe.writeObject(currentRecipe);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addUser(ActionEvent event) {
        entredAuthiorizedUsers.add(entredUserToAuthorize.getText());
        entredUserToAuthorize.clear();
    }

    private boolean handleDescriptionEmpty(String describtionRecipe) {
        boolean inputIsEmtpy = true;
        if(describtionRecipe.matches("")) {
            errorInfo(Color.RED, description);
            return false;
        }
        return true;
    }

    private boolean handleIngredientsEmpty(String ingredientsRecipe) {
        boolean inputIsEmtpy = true;
        if(ingredientsRecipe.matches("")) {
            errorInfo(Color.RED, ingredients);
            return false;
        }
        return true;
    }

    private boolean handleNameRecipeEmpty(String nameRecipe) {
        boolean inputIsEmtpy = true;
        if(nameRecipe.matches("")) {
            errorInfo(Color.RED, recipeName);
            return false;
        }
        return true;
    }

    private void errorInfo(Color color, TextInputControl text) {
        text.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        text.setText("Bitte nicht leer lassen!");
    }

    private void goToLastView() {
        root.getScene().setRoot(screens.get(App.START));
    }
}
