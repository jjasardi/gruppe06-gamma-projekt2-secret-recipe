package ch.zhaw.pm2.secretrecipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailController {

    @FXML
    private TextArea authorizedPersons;

    @FXML
    private TextArea description;

    @FXML
    private TextArea ingredients;

    @FXML
    private TextField recipeName;

    @FXML
    private Menu showHelp;

    @FXML
    void addUser(ActionEvent event) {


    }

    @FXML
    void backToLastView(ActionEvent event) {

    }
}
