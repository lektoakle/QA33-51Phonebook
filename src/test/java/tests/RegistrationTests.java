package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (app.getHelperUser().isLoggedIn())
            app.getHelperUser().logOut();
    }

    @Test
    public void testRegistrationPositive() {
        Random random = new Random();
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);

        User user = User.builder()
                .email("fhdjrhfj" + z + "@gmail.com")
                .password("aAsS23$$$$")
                .build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isOnContactsPage());
        Assert.assertTrue(app.getHelperUser().isSignOutButtonPresent());

    }

    @Test
    public void testRegistrationWrongEmail() {

        User user = User.builder()
                .email("fhdjrhfjgmail.com")
                .password("aAsS23$$$$")
                .build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistrationForm();
        String expectedAlertMessage = "Wrong email or password format";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(expectedAlertMessage));

        String expectedErrorMessage = "Registration failed with code 400";
        Assert.assertEquals(app.getHelperUser().getErrorMessage(), expectedErrorMessage);

    }
//
//    this test passes when ran independently
//    but when it is run as part of a test suite - the browser autofills the email field using one of the emails used in other tests

    @Test
    public void testRegistrationEmptyEmail() {

        User user = User.builder()
                .email("")
                .password("aAsS23$$$$")
                .build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistrationForm();
        String expectedAlertMessage = "Wrong email or password format";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(expectedAlertMessage));

        String expectedErrorMessage = "Registration failed with code 400";
        Assert.assertEquals(app.getHelperUser().getErrorMessage(), expectedErrorMessage);

    }


    @Test
    public void testRegistrationInvalidPassword() {
//        Random random = new Random();
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);

        User user = User.builder()
                .email("fsdgasdg" + z + "@gmail.com")
                .password("as23$$$$")
                .build();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistrationForm();
        String expectedAlertMessage = "Wrong email or password format";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(expectedAlertMessage));
        String expectedErrorMessage = "Registration failed with code 400";
        Assert.assertEquals(app.getHelperUser().getErrorMessage(), expectedErrorMessage);
    }


    @Test
    public void testRegistrationEmptyPassword() {

        User user = User.builder()
                .email("fhdjsdfsrhfj@gmail.com")
                .password("")
                .build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistrationForm();
        String expectedAlertMessage = "Wrong email or password format";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(expectedAlertMessage));
        String expectedErrorMessage = "Registration failed with code 400";
        Assert.assertEquals(app.getHelperUser().getErrorMessage(), expectedErrorMessage);
    }


    @Test
    public void testRegistrationRegisteredUser() {
        User user = User.builder()
                .email("testuser@test.com")
                .password("aaA1234#")
                .build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistrationForm();
        String expectedAlertMessage = "User already exist";
        String expectedErrorMessage = "Registration failed with code 409";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(expectedAlertMessage));
        Assert.assertEquals(app.getHelperUser().getErrorMessage(), expectedErrorMessage);


    }

    @Test
    public void testRegisteredUserNewPassword() {
        User user = User.builder()
                .email("testuser@test.com")
                .password("aaA1234??").build();
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistrationForm();
        String expectedAlertMessage = "Wrong email or password format";
        Assert.assertTrue(app.getHelperUser().isAlertPresent(expectedAlertMessage));

        String expectedErrorMessage = "Registration failed with code 400";
        Assert.assertEquals(app.getHelperUser().getErrorMessage(), expectedErrorMessage);
    }


}
