package ch.zhaw.pm2.secretrecipe;

public class InvalidUserEntry extends Exception{

    public InvalidUserEntry(String errorMessage) {
        super(errorMessage);
    }
}
