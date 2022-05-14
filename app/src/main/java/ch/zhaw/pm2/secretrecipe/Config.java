package ch.zhaw.pm2.secretrecipe;

/**
 * Utility class used only to store the name and the paths of the views
 */
public final class Config {

    private Config() {
        throw new IllegalStateException("Utility class");
    }

    public static final String START = "Start";
    public static final String START_FILE = "/views/Start.fxml";
    public static final String NEWRECIPE = "NewRecipe";
    public static final String NEWRECIPE_FILE = "/views/NewRecipe.fxml";
    public static final String LOGIN = "Login";
    public static final String LOGIN_FILE = "/views/Login.fxml";
    public static final String REGISTRATION = "Registration";
    public static final String REGISTRATION_FILE = "/views/Registration.fxml";
    public static final String DETAIL = "Detail";
    public static final String DETAIL_FILE = "/views/Detail.fxml";
}
