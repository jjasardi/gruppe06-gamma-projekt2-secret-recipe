package ch.zhaw.pm2.secretrecipe;

import java.util.List;

public class DataManager {
    private List<User> userList;
    private List<Recipe> recipeList;

    public DataManager() {
        userList = Database.getUserListFromFile();
        recipeList = Database.getRecipeListFromFile();
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
}