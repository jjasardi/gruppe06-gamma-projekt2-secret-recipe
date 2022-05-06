package ch.zhaw.pm2.secretrecipe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 *
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
        dataManager = dataManager.getInstance();
        dataManager.saveData();
    }


    private void startView(Stage stage) {
        try {
            Scene scene = new Scene(screens.get(START));

            // configure and show stage
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllScreens() {
        loadScreen(START, START_FILE);
        loadScreen(NEWRECIPE, NEWRECIPE_FILE);
    }

    private void loadScreen(String name, String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            Parent loadScreen = (Parent) loader.load();
            ControlledScreens controlledScreen = (ControlledScreens) loader.getController();
            controlledScreen.setScreenList(screens);
            screens.put(name, loadScreen);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
            e.getMessage();
        }
    }
}
