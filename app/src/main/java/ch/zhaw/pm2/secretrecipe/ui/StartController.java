package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
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
    private User currentUser;

    @FXML
    public GridPane gridPane;

    @FXML
    private AnchorPane root;

    @FXML
    private Button newButton;

    @FXML
    void initialize() {
        session = session.getInstance();

        session.hasLoggedInProperty().addListener((observable, oldvalue, newValue) -> {
            if (newValue) {
                currentUser = session.getLoggedInUser();
                List<Recipe> userRecipes = currentUser.getRecipeListe();

                int gridColumnCount = gridPane.getColumnCount();
                for (int index = 0; index < userRecipes.size(); index++) {
                    gridPane.add(createAnchorPaneRecipeElement(userRecipes.get(index)),
                            getColumnOfRecipe(index, gridColumnCount),
                            getRowOfRecipe(index, gridColumnCount));
                }
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

    private void setNewScene() {
        root.getScene().setRoot(screens.get(Config.NEWRECIPE));

        //we will use it later to generate the recipe icons
//        Label label = new Label("Wesh");
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().add(label);
//        gridPane.getChildren().add(anchorPane);
    }

    @Override
    public void setScreenList(HashMap<String, Parent> screens) {
        this.screens = screens;
    }
}
