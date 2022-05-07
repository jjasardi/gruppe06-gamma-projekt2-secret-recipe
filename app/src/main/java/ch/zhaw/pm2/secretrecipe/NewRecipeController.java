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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewRecipeController implements ScreenController {
    private User user;
    private Recipe recipe;
    private HashMap<String, Parent> screens = new HashMap<>();
    private DataManager dataManager;
    private List<String> entredAuthiorizedUsers = new ArrayList<>();

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

        if (!manageEmptyInput()) {
            Recipe currentRecipe = new Recipe(nameRecipe, ingredientsRecipe, describtionRecipe, user);
            dataManager = dataManager.getInstance();
            dataManager.addRecipe(currentRecipe);
            for(String userName : entredAuthiorizedUsers) {
                for(User user : dataManager.getUserList()) {
                    if(user.getUsername().equals(userName)) {
                        user.setRecipeAuthorization(currentRecipe);
                    }
                }
            }
            root.getScene().setRoot(screens.get(Config.START));
        }
    }

    @FXML
    void addUser(ActionEvent event) {
        entredAuthiorizedUsers.add(entredUserToAuthorize.getText());
        entredUserToAuthorize.clear();
    }

    private boolean manageEmptyInput() {
        boolean isEmpty = false;
        TextInputControl[] contents = {recipeName, ingredients, description};
        for (TextInputControl content : contents) {
            if (content.getText().equals("")) {
                isEmpty = true;
                errorInfo(Color.RED, content);
            }
        }
        return isEmpty;
    }

    private void errorInfo(Color color, TextInputControl content) {
        content.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        content.setText("Bitte nicht leer lassen!");
    }

    private void goToLastView() {
        root.getScene().setRoot(screens.get(Config.START));
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
