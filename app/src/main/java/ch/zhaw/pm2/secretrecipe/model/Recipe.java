package ch.zhaw.pm2.secretrecipe.model;

import java.io.Serializable;

/**
 * this class represents a recipe object.
 */
public class Recipe implements Serializable {
    private static final long serialVersionUID = 20L;
    private int id;
    private String name;
    private String ingredients;
    private String description;
    private final User owner;

    /**
     * @param name
     * @param ingredients
     * @param description
     * @param owner
     */
    public Recipe(int id, String name, String ingredients, String description, User owner) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getId() {
        return id;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }
}
