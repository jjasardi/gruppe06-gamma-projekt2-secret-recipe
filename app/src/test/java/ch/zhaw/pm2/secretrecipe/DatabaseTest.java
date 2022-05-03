package ch.zhaw.pm2.secretrecipe;

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

    private Database database;
    private List<User> userList = new ArrayList<>();
    private List<Recipe> recipeList = new ArrayList<>();



    @BeforeEach
    void setup() {
        mockedUser1 = new User("Bruce", "Lee", "brlee", "12345");
        mockedUser2 = new User("Jean-Claude", "Van Damme", "jcvdWesh", "admin123");

        mockedRecipe1 = new Recipe("Lasagne", "Zwiebel, Milch", "Beschreibung", mockedUser1);
        mockedRecipe2 = new Recipe("Macarons", "gemahlene Mandeln, Puderzucker", "Zubereitung", mockedUser2);

        userList.add(mockedUser1);
        userList.add(mockedUser2);

        recipeList.add(mockedRecipe1);
        recipeList.add(mockedRecipe2);
    }

    @Test
    void testUsersAreSavedInFile() {
        //do
        database.saveUsers(userList);
        userList = database.loadUsers();

        //assert
        assertEquals(userList.get(0), mockedUser1);
    }

    @Test
    void testRecipesAreSavedInFile() {
        //do
        database.saveRecipes(recipeList);
        recipeList = database.loadRecipes();

        //assert
        assertEquals(recipeList.get(0), mockedRecipe1);
    }

    @Test
    void testRecipeOwnerHasTheSameReferenceThanTheUser() {
        //do
        database.saveUsers(userList);
        database.saveRecipes(recipeList);

        userList = database.loadUsers();
        recipeList = database.loadRecipes();

        //assert
        assertEquals(userList.get(0), recipeList.get(0).getOwner());
    }

    @Test
    void testRecipeAttributeHasChanged() {
        //do
        mockedRecipe1.setName("Cêpes");

        database.saveRecipes(recipeList);
        recipeList = database.loadRecipes;

        //assert
        assertEquals(recipeList.get(0).getName(), mockedRecipe1.getName());
    }

    @Test
    void testFileIsAlwaysOvewrittenWhenSave() {
        //do
        mockedUser3 = new User("Chuck", "Norris", "chnorris", "54321Test");
        userList.add(mockedUser3);

        userList.remove(0);
        userList.remove(1);

        database.saveUsers(userList);
        userList = database.loadUsers;

        //assert
        assertEquals(1, userList.size());
    }
}
