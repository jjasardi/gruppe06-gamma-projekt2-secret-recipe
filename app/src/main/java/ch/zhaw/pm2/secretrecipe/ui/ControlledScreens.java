package ch.zhaw.pm2.secretrecipe.ui;

import javafx.scene.Parent;

import java.util.HashMap;

/**
 * This interface helps to control the different screens.
 */
public interface ControlledScreens {
    /**
     * sets the screen list
     * 
     * @param screens to be setted
     */
    public void setScreenList(HashMap<String, Parent> screens);
}
