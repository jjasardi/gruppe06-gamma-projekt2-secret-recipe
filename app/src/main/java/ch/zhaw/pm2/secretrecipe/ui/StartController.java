package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Recipe;
import ch.zhaw.pm2.secretrecipe.model.Session;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class StartController implements ControlledScreens {

    private HashMap<String, Parent> screens = new HashMap<>();
    private HashMap<AnchorPane, Recipe> anchorPaneToRecipe = new HashMap<>();

    private Session session;
    private User loggedInUser;
    private DataManager dataManager;

    @FXML
    public GridPane gridPane;

    @FXML
    private AnchorPane root;

    @FXML
    private Button newButton;

    @FXML
    void initialize() {
        session = Session.getInstance();
        dataManager = DataManager.getInstance();

        session.hasLoggedInProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                loggedInUser = session.getLoggedInUser();
                List<Recipe> usersRecipeList = dataManager.getRecipeList(loggedInUser);
                List<Recipe> authorizedRecipeList = loggedInUser.getRecipeListe();
                List<Recipe> recipesToShow = getRecipesToShow(usersRecipeList, authorizedRecipeList);

                fillGridPane(recipesToShow);
            }
        });
    }

    /**
     * @param event
     */
    @FXML
    void newRecipe(ActionEvent event) {
        setNewScene();
    }

    private List<Recipe> getRecipesToShow(List<Recipe> usersRecipeList, List<Recipe> authorizedRecipeList) {
        return Stream.of(usersRecipeList, authorizedRecipeList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private void fillGridPane(List<Recipe> recipeList) {
        int gridColumnCount = gridPane.getColumnCount();

        for (int index = 0; index < recipeList.size(); index++) {
            gridPane.add(createAnchorPaneRecipeElement(recipeList.get(index)),
                    getColumnOfRecipe(index, gridColumnCount),
                    getRowOfRecipe(index, gridColumnCount));
        }
    }

    private AnchorPane createAnchorPaneRecipeElement(Recipe recipe) {
        Label label = new Label(recipe.getName());
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(label);
        anchorPaneToRecipe.put(anchorPane, recipe);

        return anchorPane;
    }

    private int getColumnOfRecipe(int index, int columnCount) {
        return index % columnCount;
    }

    private int getRowOfRecipe(int index, int columnCount) {
        return index / columnCount;
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
