package ch.zhaw.pm2.secretrecipe;

import ch.zhaw.pm2.secretrecipe.model.Database;
import ch.zhaw.pm2.secretrecipe.model.Recipe;
import ch.zhaw.pm2.secretrecipe.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    private User user1;
    private User user2;
    private User user3;

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;

    private List<User> userList = new ArrayList<>();
    private List<Recipe> recipeList = new ArrayList<>();



    @BeforeEach
    void setup() {
        user1 = new User("Bruce", "Lee", "brlee", "12345");
        user2 = new User("Jean-Claude", "Van Damme", "jcvdWesh", "admin123");

        recipe1 = new Recipe(0, "Lasagne", "Zwiebel, Milch", "Beschreibung", user1);
        recipe2 = new Recipe(1, "Macarons Schokolade", "gemahlene Mandeln, Puderzucker, Schokolade", "Zubereitung", user2);
        recipe3 = new Recipe(2, "Macarons Vanille", "gemahlene Mandeln, Puderzucker, Vanille", "Zubereitung", user1);

        userList.add(user1);
        userList.add(user2);

        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
    }

    @Test
    void testUsersAreSavedInFile() {
        //do
        Database.saveDataToFile(userList, recipeList);
        List<User> readedUserList = Database.getUserListFromFile();

        //assert
        assertEquals(userList.size(), readedUserList.size());
    }

    @Test
    void testRecipesAreSavedInFile() {
        //do
        Database.saveDataToFile(userList, recipeList);
        List<Recipe> readedRecipeList = Database.getRecipeListFromFile();

        //assert
        assertEquals(recipeList.size(), readedRecipeList.size());
    }

    @Test
    void testRecipeOwnerHasTheSameReferenceThanTheUser() {
        //do
        Database.saveDataToFile(userList, recipeList);
        List<Recipe> readedRecipeList = Database.getRecipeListFromFile();

        //assert
        assertEquals(readedRecipeList.get(0).getOwner(), readedRecipeList.get(2).getOwner());
    }

    @Test
    void testRecipeAttributeHasChanged() {
        //do
        recipe1.setName("Cêpes");

        Database.saveDataToFile(userList, recipeList);
        List<Recipe> readedRecipeList = Database.getRecipeListFromFile();

        //assert
        assertEquals(readedRecipeList.get(0).getName(), recipe1.getName());
    }

    @Test
    void testFileIsAlwaysOverwrittenWhenSave() {
        //do
        user3 = new User("Chuck", "Norris", "chnorris", "54321Test");
        userList.add(user3);

        userList.remove(0);
        userList.remove(1);

        Database.saveDataToFile(userList, recipeList);
        List<User> readedUserList = Database.getUserListFromFile();

        //assert
        assertEquals(userList.size(), readedUserList.size());
    }
}
