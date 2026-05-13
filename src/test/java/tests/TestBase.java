package tests;

import manager.ApplicationManager;
import org.testng.annotations.*;

public class TestBase {
    static ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}
