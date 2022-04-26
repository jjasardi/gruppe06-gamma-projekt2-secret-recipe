package ch.zhaw.pm2.secretrecipe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> userList;

    public UserManager() {
        initializeUserListFromFile();
    }

    private void initializeUserListFromFile() {
        userList = new ArrayList<>();
        try (ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("userData.dat"));) {
            userList = (ArrayList<User>) objInStream.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("class not found: " + e.getMessage());
        }
    }

    private void saveUserListToFile() {
        try (ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream("userData.dat"));) {
            objOutStream.writeObject(userList);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User getUserMatchingCredentials(String username, String password) {
        for (User user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
