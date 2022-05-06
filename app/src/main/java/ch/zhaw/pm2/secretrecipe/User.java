package ch.zhaw.pm2.secretrecipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
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

    public void setRecipeAuthorization(Recipe currentRecipe) {
        recipeList.add(currentRecipe);
    }
}
