/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ch.zhaw.pm2.secretrecipe;

import ch.zhaw.pm2.secretrecipe.ui.App;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
