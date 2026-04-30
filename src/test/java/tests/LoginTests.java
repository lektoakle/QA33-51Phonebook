package tests;

import org.testng.annotations.Test;



public class LoginTests extends TestBase {

    String email = "testuser@test.com";
    String password = "aaA1234#";

    @Test
    public void positiveTest() throws InterruptedException {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submitLoginForm();
        app.getHelperUser().checkLoggedIn();


    }
}
