package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private static final Logger logger = Logger.getLogger("NewRecipeLogger"); 

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
    void backToLastView(ActionEvent event) {
        root.getScene().setRoot(screens.get(App.START));
    }

    @FXML
    void deleteDraftRecipe(ActionEvent event) {
        root.getScene().setRoot(screens.get(App.START));
    }

    @FXML
    void saveRecipe(ActionEvent event) {
        String nameRecipe = recipeName.getText();
        String ingredientsRecipe = ingredients.getText();
        String describtionRecipe = description.getText();
        String usersRecipe = authorizedUsers.getText().;
        Recipe currentRecipe = new Recipe(nameRecipe, ingredientsRecipe, describtionRecipe, user);

        if(handleNameRecipeEmpty(nameRecipe) && handleIngredientsEmpty(ingredientsRecipe) && handleDescriptionEmpty(describtionRecipe) && handlerUsersEmpty(usersRecipe)){
            user.getRecipeListe().add(currentRecipe);
            String[] authozizedUsers = getAuthorizedUsers(authorizedUsers.getText());
            try {
                ObjectOutputStream recipe = new ObjectOutputStream(new FileOutputStream(currentRecipe.getName()));
                recipe.writeObject(currentRecipe);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] getAuthorizedUsers(String text) {
        //TODO hier die username aus dem string hollen
        String[] authorizedUsers = text.split("");
        return authorizedUsers;
    }

    private boolean handlerUsersEmpty(String usersRecipe) {
        boolean inputIsEmtpy = true;
        if(usersRecipe.matches("")) {
            authorizedUsers.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            authorizedUsers.setText("Bitte nicht leer lassen!");
            return false;
        }
        return true;
    }

    private boolean handleDescriptionEmpty(String describtionRecipe) {
        boolean inputIsEmtpy = true;
        if(describtionRecipe.matches("")) {
            description.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            description.setText("Bitte nicht leer lassen!");
            return false;
        }
        return true;
    }

    private boolean handleIngredientsEmpty(String ingredientsRecipe) {
        boolean inputIsEmtpy = true;
        if(ingredientsRecipe.matches("")) {
            ingredients.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            ingredients.setText("Bitte nicht leer lassen!");
            return false;
        }
        return true;
    }

    private boolean handleNameRecipeEmpty(String nameRecipe) {
        boolean inputIsEmtpy = true;
        if(nameRecipe.matches("")) {
            recipeName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            recipeName.setText("Bitte nicht leer lassen!");
            return false;
        }
        return true;
    }
}
