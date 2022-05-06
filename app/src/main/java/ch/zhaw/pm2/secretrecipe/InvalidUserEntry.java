package ch.zhaw.pm2.secretrecipe;

public class InvalidUserEntry extends Exception{

    InvalidUserEntry(String errorMessage) {
        super(errorMessage);
    }
}
