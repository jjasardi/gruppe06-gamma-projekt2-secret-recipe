package ch.zhaw.pm2.secretrecipe;

/**
 * class used to handle invalid user entries
 */
public class InvalidUserEntry extends Exception{

    public InvalidUserEntry(String errorMessage) {
        super(errorMessage);
    }
}
