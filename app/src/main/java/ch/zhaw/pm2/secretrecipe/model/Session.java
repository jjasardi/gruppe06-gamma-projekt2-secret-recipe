package ch.zhaw.pm2.secretrecipe.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * this class saves the temporary informations of the program that shouldn't be
 * saved after the programm is closed. It implements the singletone pattern so
 * that one instance of the class is used for the entire programm
 */
public class Session {

    private static Session session;
    private User loggedInUser;

    private BooleanProperty hasLoggedIn = new SimpleBooleanProperty(false);

    private Session() {
    }

    /**
     * This static method return the instance of {@link Session}. it creates an
     * instance of {@link Session} if it still doesn't exist.
     *
     * @return {@link Session} instance.
     */
    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        setHasLoggedIn(true);
    }

    public boolean getHasLoggedIn() {
        return hasLoggedIn.get();
    }

    public BooleanProperty hasLoggedInProperty() {
        return hasLoggedIn;
    }

    public void setHasLoggedIn(boolean hasLoggedIn) {
        this.hasLoggedIn.set(hasLoggedIn);
    }
}
