package ch.zhaw.pm2.secretrecipe.ui;

import ch.zhaw.pm2.secretrecipe.Config;
import ch.zhaw.pm2.secretrecipe.model.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * this class contains the main method and launches the GUI application.
 */
public class App extends Application {

    private DataManager dataManager;
    private HashMap<String, Parent> screens = new HashMap<>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadAllScreens();
        startView(primaryStage);
    }

    @Override
    public void stop() {
        dataManager = DataManager.getInstance();
        dataManager.saveData();
    }

    private void startView(Stage stage) {
        try {
            Scene scene = new Scene(screens.get(Config.LOGIN));

            // configure and show stage
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllScreens() {
        loadScreen(Config.LOGIN, Config.LOGIN_FILE);
        loadScreen(Config.REGISTRATION, Config.REGISTRATION_FILE);
        loadScreen(Config.START, Config.START_FILE);
        loadScreen(Config.NEWRECIPE, Config.NEWRECIPE_FILE);
        loadScreen(Config.DETAIL, Config.DETAIL_FILE);
    }

    private void loadScreen(String name, String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            Parent loadScreen = (Parent) loader.load();
            ControlledScreens controlledScreen = (ControlledScreens) loader.getController();
            controlledScreen.setScreenList(screens);
            screens.put(name, loadScreen);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.getMessage();
        }
    }
}
