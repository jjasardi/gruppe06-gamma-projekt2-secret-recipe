package ch.zhaw.pm2.secretrecipe.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Session {

    private static Session session;
    private User loggedInUser;

    private BooleanProperty hasLoggedIn = new SimpleBooleanProperty(false);

    private Session() {}

    public static Session getInstance(){
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
