package ch.zhaw.pm2.secretrecipe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {
    private static DataManager dataManager;
    private List<User> userList;
    private List<Recipe> recipeList;

    private DataManager() {
        userList = Database.getUserListFromFile();
        recipeList = Database.getRecipeListFromFile();
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public void saveData() {
        Database.saveDataToFile(userList, recipeList);
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Recipe> getRecipeList() {
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

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }
}