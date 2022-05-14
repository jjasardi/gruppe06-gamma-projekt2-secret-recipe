package ch.zhaw.pm2.secretrecipe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a user object. In this class the recipes from other
 * users that he is authorized to see are saved.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 10L;
    private String firstname;
    private String surname;
    private String username;
    private String password;
    private List<Recipe> recipeList;

    /**
     * @param firstname
     * @param surname
     * @param username
     * @param password
     */
    public User(String firstname, String surname, String username, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.recipeList = new ArrayList<>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Recipe> getRecipeListe() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    /**
     * adds one recipe to the authorized recipe list. the same recipe can be added
     * only once to the list
     *
     * @param currentRecipe the recipe to be added to the user's authorized recipes
     */
    public void addRecipeAuthorization(Recipe currentRecipe) {
        if (!isRecipeAuthorized(currentRecipe)) {
            recipeList.add(currentRecipe);
        }
    }

    /**
     * removes one recipe from the authorized list.
     *
     * @param the recipe to be removed
     */
    public void removeRecipeAuthorization(Recipe recipe) {
        recipeList.removeIf(recipeOnList -> (recipeOnList.getId() == recipe.getId()));
    }

    /**
     * checks if a recipe is already in the authorized recipe list.
     *
     * @param recipe the recipe to be checked
     * @return true if recipe is already in the authorized recipe list. false
     *         otherwise
     */
    public boolean isRecipeAuthorized(Recipe recipe) {
        for (Recipe recipeOnList : recipeList) {
            if (recipeOnList.getId() == recipe.getId()) {
                return true;
            }
        }
        return false;
    }
}
