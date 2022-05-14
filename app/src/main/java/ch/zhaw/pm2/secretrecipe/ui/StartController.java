package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.Recipe;
import ch.zhaw.pm2.secretrecipe.model.Session;
import ch.zhaw.pm2.secretrecipe.model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is the controller of the Start view, where all the visible recipes
 * of the logged in user are shown.
 */
public class StartController implements ControlledScreens {

    private HashMap<String, Parent> screens = new HashMap<>();
    private HashMap<AnchorPane, Recipe> anchorPaneToRecipe = new HashMap<>();

    private Session session;
    private User loggedInUser;
    private DataManager dataManager;
    private static Recipe clickedRecipe;

    private static BooleanProperty recipeClicked = new SimpleBooleanProperty(false);

    @FXML
    public GridPane gridPane;

    @FXML
    private AnchorPane root;

    @FXML
    private Button newButton;

    /**
     * gets the {@link DataManager} instance and {@link Session} instance and adds
     * listeners to update the shown recipes on changes.
     */
    @FXML
    public void initialize() {
        session = Session.getInstance();
        dataManager = DataManager.getInstance();

        session.hasLoggedInProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                updateGridPane();
            }
        });

        dataManager.getRecipeList().addListener((ListChangeListener<? super Recipe>) observable -> updateGridPane());
    }

    private void updateGridPane() {
        loggedInUser = session.getLoggedInUser();
        List<Recipe> usersRecipeList = dataManager.getRecipeList(loggedInUser);
        List<Recipe> authorizedRecipeList = loggedInUser.getRecipeListe();
        List<Recipe> recipesToShow = getRecipesToShow(usersRecipeList, authorizedRecipeList);

        fillGridPane(recipesToShow);
    }

    /**
     * @param event
     */
    @FXML
    private void newRecipe() {
        setNewScene(Config.NEWRECIPE);
    }

    private void setNewScene(String view) {
        root.getScene().setRoot(screens.get(view));
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
        anchorPaneClickEvent(anchorPane);

        return anchorPane;
    }

    private void anchorPaneClickEvent(AnchorPane anchorPane) {
        anchorPane.setOnMouseClicked(event -> {
            AnchorPane clickedAnchorPane = (AnchorPane) event.getSource();
            clickedRecipe = anchorPaneToRecipe.get(clickedAnchorPane);
            setNewScene(Config.DETAIL);
            recipeClicked.set(true);
        });
    }

    public static Recipe getClickedRecipe() {
        return clickedRecipe;
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

    public boolean isRecipeClicked() {
        return recipeClicked.get();
    }

    public static BooleanProperty recipeClickedProperty() {
        return recipeClicked;
    }

    public static void setRecipeClicked(boolean recipeClicked) {
        StartController.recipeClicked.set(recipeClicked);
    }
}
