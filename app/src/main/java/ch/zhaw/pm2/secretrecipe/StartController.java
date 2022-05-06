package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

/**
 *
 */
public class StartController implements ControlledScreens {

    private HashMap<String, Parent> screens = new HashMap<>();

    @FXML
    public GridPane gridPane;

    @FXML
    private AnchorPane root;

    @FXML
    private Button newButton;

    /**
     * @param event
     */
    @FXML
    void newRecipe(ActionEvent event) {
        setNewScene();
    }

    private void setNewScene() {
        root.getScene().setRoot(screens.get(App.NEWRECIPE));

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
