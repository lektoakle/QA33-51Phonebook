package tests;


import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {

    String email = "testuser@test.com";
    String password = "aaA1234#";

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLoggedIn()) {
            app.getHelperUser().logOut();
        }
    }

    @Test
    public void positiveTest() throws InterruptedException {
        User user = new User().setEmail(email).setPassword(password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLoginForm();
        Assert.assertTrue(app.getHelperUser().isLoggedIn());


    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("estuser@test.com",password);
        app.getHelperUser().submitLoginForm();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email,"aaA1234");
        app.getHelperUser().submitLoginForm();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm('1'+email,password+'1');
        app.getHelperUser().submitLoginForm();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

}
