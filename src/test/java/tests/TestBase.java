package tests;

import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @BeforeMethod
    public void startLogger(Method m){
        logger.info("Test name: " + m.getName());
    }
    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}
