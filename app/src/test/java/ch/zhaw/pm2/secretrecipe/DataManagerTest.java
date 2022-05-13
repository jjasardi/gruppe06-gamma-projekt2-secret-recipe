package ch.zhaw.pm2.secretrecipe;

import ch.zhaw.pm2.secretrecipe.model.DataManager;
import ch.zhaw.pm2.secretrecipe.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DataManagerTest {

    private DataManager datamanager = DataManager.getInstance();

    @Test
    void getRecipeListTest() {
        User mockedUser = Mockito.mock(User.class);
        datamanager.getRecipeList(mockedUser);
        int amountOfRecipes = datamanager.getRecipeList().size();

        Mockito.verify(mockedUser, Mockito.times(amountOfRecipes)).getUsername();
    }
}
