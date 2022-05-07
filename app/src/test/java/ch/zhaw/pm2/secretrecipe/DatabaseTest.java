package ch.zhaw.pm2.secretrecipe;

import ch.zhaw.pm2.secretrecipe.model.Database;
import ch.zhaw.pm2.secretrecipe.model.Recipe;
import ch.zhaw.pm2.secretrecipe.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    @Mock
    private User mockedUser1;

    @Mock
    private User mockedUser2;

    @Mock
    private User mockedUser3;

    @Mock
    private Recipe mockedRecipe1;

    @Mock
    private Recipe mockedRecipe2;

    @Mock
    private Recipe mockedRecipe3;

    private List<User> userList = new ArrayList<>();
    private List<Recipe> recipeList = new ArrayList<>();



    @BeforeEach
    void setup() {
        mockedUser1 = new User("Bruce", "Lee", "brlee", "12345");
        mockedUser2 = new User("Jean-Claude", "Van Damme", "jcvdWesh", "admin123");

        mockedRecipe1 = new Recipe("Lasagne", "Zwiebel, Milch", "Beschreibung", mockedUser1);
        mockedRecipe2 = new Recipe("Macarons Schokolade", "gemahlene Mandeln, Puderzucker, Schokolade", "Zubereitung", mockedUser2);
        mockedRecipe3 = new Recipe("Macarons Vanille", "gemahlene Mandeln, Puderzucker, Vanille", "Zubereitung", mockedUser1);

        userList.add(mockedUser1);
        userList.add(mockedUser2);

        recipeList.add(mockedRecipe1);
        recipeList.add(mockedRecipe2);
        recipeList.add(mockedRecipe3);
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
        mockedRecipe1.setName("Cêpes");

        Database.saveDataToFile(userList, recipeList);
        List<Recipe> readedRecipeList = Database.getRecipeListFromFile();

        //assert
        assertEquals(readedRecipeList.get(0).getName(), mockedRecipe1.getName());
    }

    @Test
    void testFileIsAlwaysOverwrittenWhenSave() {
        //do
        mockedUser3 = new User("Chuck", "Norris", "chnorris", "54321Test");
        userList.add(mockedUser3);

        userList.remove(0);
        userList.remove(1);

        Database.saveDataToFile(userList, recipeList);
        List<User> readedUserList = Database.getUserListFromFile();

        //assert
        assertEquals(userList.size(), readedUserList.size());
    }
}
