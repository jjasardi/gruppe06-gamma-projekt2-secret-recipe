package ch.zhaw.pm2.secretrecipe.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This abstract class works as a database to save and load the list of the
 * users and the recipes in a file in {@link Database#DATA_FILE_PATH}
 */

public abstract class Database {
    private static final String DATA_FILE_PATH = "database" + File.separator + "data.dat";
    private static final Logger logger = Logger.getLogger("DatabaseLogger");

    private Database() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * gets the user list from the {@link Database#DATA_FILE_PATH}.
     *
     * @return the user list loaded from the file, or an empty list if file doesn't exist
     */
    public static List<User> getUserListFromFile() {
        List<User> userListFromFile = new ArrayList<>();
        try (ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH));) {
            userListFromFile = (ArrayList<User>) objInStream.readObject();
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "File not found! " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "class not found: " + e.getMessage());
        }
        return userListFromFile;
    }

    /**
     * gets the recipes list from the {@link Database#DATA_FILE_PATH}.
     *
     * @return the recipes list loaded from the file, or an empty list if file doesn't exist
     */
    public static List<Recipe> getRecipeListFromFile() {
        List<Recipe> recipeListFromFile = new ArrayList<>();
        try (ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH));) {
            objInStream.readObject(); // to skip the user list
            recipeListFromFile = (ArrayList<Recipe>) objInStream.readObject();
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "File not found! " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "class not found: " + e.getMessage());
        }
        return recipeListFromFile;
    }

    /**
     * saves the user and recipe list to the {@link Database#DATA_FILE_PATH}.
     *
     * @param userList the user list to be saved
     * @param recipeList the recipe list to be saved
     */
    public static void saveDataToFile(List<User> userList, List<Recipe> recipeList) {
        File dataFile = new File(DATA_FILE_PATH);
        dataFile.getParentFile().mkdirs();
        try (ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(dataFile));) {
            objOutStream.writeObject(userList);
            objOutStream.writeObject(recipeList);
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "File not found! " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error: " + e.getMessage());
        }
    }
}
