package ch.zhaw.pm2.secretrecipe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class manages only the data loaded from the {@link Database} (userList
 * and recipeList). It implements the singletone pattern so that one instance of
 * the class is used for the entire programm
 */

public class DataManager {
    private static DataManager dataManager;
    private List<User> userList;
    private ObservableList<Recipe> recipeList;

    private DataManager() {
        userList = Database.getUserListFromFile();
        recipeList = FXCollections.observableArrayList(Database.getRecipeListFromFile());
    }

    /**
     * This static method return the instance of {@link DataManager}. it creates an
     * instance of {@link DataManager} if it still doesn't exist.
     *
     * @return {@link DataManager} instance.
     */
    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    /**
     * This method saves the data to the {@link Database}
     *
     */
    public void saveData() {
        Database.saveDataToFile(userList, recipeList.stream().collect(Collectors.toList()));
    }

    public List<User> getUserList() {
        return userList;
    }

    /**
     * This method return the list of the authorized users for one recipe
     *
     * @param recipe the recipe from which we want to get the authorized users
     * @return the authorized user list for the given recipe
     */
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

    /**
     * returns the recipese created by one specific user
     *
     * @param user the user that we want the created recipes from
     * @return the recipes of the given user
     */
    public List<Recipe> getRecipeList(User user) {
        List<Recipe> usersRecipeList = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            if (recipe.getOwner().getUsername().equals(user.getUsername())) {
                usersRecipeList.add(recipe);
            }
        }
        return usersRecipeList;
    }

    /**
     * get the next unique id for the recipes. id is null if there are no recipes
     *
     * @return the next unique id
     */
    public int getNewId() {
        int id = 0;
        List<Integer> ids = recipeList.stream().map(Recipe::getId).collect(Collectors.toList());
        if (!ids.isEmpty()) {
            id = Collections.max(ids) + 1;
        }
        return id;
    }

    /**
     * adds a user to the user list 
     * @param user the user to be added
     */
    public void addUser(User user) {
        userList.add(user);
    }

    /**
     * adds a recipe to the recipe list 
     * @param recipe the recipe to be added
     */
    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }

    /**
     * deletes a recipe from the user list 
     * @param recipe the recipe to be deleted
     */
    public void deleteRecipe(Recipe recipe) {
        recipeList.removeIf(recipeOnList -> (recipeOnList.getId() == recipe.getId()));
    }

    /**
     * updates the attributes of the recipe in the recipe list 
     * @param recipe the updated recipe
     */
    public void updateRecipe(Recipe recipe) {
        recipeList.forEach(recipeOnList -> {
            recipeOnList.setName(recipe.getName());
            recipeOnList.setIngredients(recipe.getIngredients());
            recipeOnList.setDescription(recipe.getDescription());
        });
    }
}