package ch.zhaw.pm2.secretrecipe.model;

import java.util.List;

public class DataManager {
    private static DataManager dataManager;
    private List<User> userList;
    private List<Recipe> recipeList;

    private DataManager() {
        userList = Database.getUserListFromFile();
        recipeList = Database.getRecipeListFromFile();
    }

    public static DataManager getInstance() {
        if(dataManager == null) {
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