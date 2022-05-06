package ch.zhaw.pm2.secretrecipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Database {
    private static final String DATA_FILE_PATH = "database" + File.separator + "data.dat";

    private Database() {
        throw new IllegalStateException("Utility class");
    }

    public static List<User> getUserListFromFile() {
        List<User> userListFromFile = new ArrayList<>();
        try (ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH));) {
            userListFromFile = (ArrayList<User>) objInStream.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("class not found: " + e.getMessage());
        }
        return userListFromFile;
    }

    public static List<Recipe> getRecipeListFromFile() {
        List<Recipe> recipeListFromFile = new ArrayList<>();
        try (ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH));) {
            objInStream.readObject(); // to skip the user list
            recipeListFromFile = (ArrayList<Recipe>) objInStream.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("class not found: " + e.getMessage());
        }
        return recipeListFromFile;
    }

    public static void saveDataToFile(List<User> userList, List<Recipe> recipeList) {
        File dataFile = new File(DATA_FILE_PATH);
        dataFile.getParentFile().mkdirs();
        try (ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(dataFile));) {
            objOutStream.writeObject(userList);
            objOutStream.writeObject(recipeList);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}
