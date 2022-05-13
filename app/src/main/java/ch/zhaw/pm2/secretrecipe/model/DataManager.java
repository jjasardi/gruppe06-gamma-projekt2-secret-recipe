package ch.zhaw.pm2.secretrecipe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {
    private static DataManager dataManager;
    private List<User> userList;
    private ObservableList<Recipe> recipeList;

    private DataManager() {
        userList = Database.getUserListFromFile();
        recipeList = FXCollections.observableArrayList(Database.getRecipeListFromFile());
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public void saveData() {
        Database.saveDataToFile(userList, recipeList.stream().collect(Collectors.toList()));
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<User> getAuthorizedUserList(Recipe recipe) {
        List<User> authorizedUserList = new ArrayList<>();
        for (User user : userList) {
            if (user.isRecipeAuthorized(recipe)) {
                authorizedUserList.add(user);
            }
        }
        return authorizedUserList;
    }

    public ObservableList<Recipe> getRecipeList() {
        return recipeList;
    }

    public List<Recipe> getRecipeList(User user) {
        List<Recipe> usersRecipeList = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            if (recipe.getOwner().getUsername().equals(user.getUsername())) {
                usersRecipeList.add(recipe);
            }
        }
        return usersRecipeList;
    }

    public int getNewId() {
        int id = 0;
        List<Integer> ids = recipeList.stream().map(Recipe::getId).collect(Collectors.toList());
        if (!ids.isEmpty()) {
            id = Collections.max(ids) + 1;
        }
        return id;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipeList.removeIf(recipeOnList -> (recipeOnList.getId() == recipe.getId()));
    }

    public void updateRecipe(Recipe recipe) {
        recipeList.forEach(recipeOnList -> {
            recipeOnList.setName(recipe.getName());
            recipeOnList.setIngredients(recipe.getIngredients());
            recipeOnList.setDescription(recipe.getDescription());
        });
    }
}