package ch.zhaw.pm2.secretrecipe.model;

public class Session {
    private static Session session;
    private User loggedInUser;

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
    }
}
