package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    User currentUser = User.builder()
            .email("testuser@test.com")
            .password("aaA1234#")
            .build();

    @BeforeMethod
    public void precondition() throws InterruptedException {
        if (app.getHelperUser().isLoggedIn()) {
            app.getHelperUser().logOut();
        }
        app.getHelperUser().login(currentUser);
//        wait(500);

    }

    @Test
    public void AddNewContactAllFieldsPositiveTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone ="4522346623"+j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone(phone)
                .address("F")
                .description("F")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//div/h2[text()='Emma']")));
        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//div/h3[text()='"+phone+"']")));

    }


    @Test
    public void AddNewContactRequiredFieldsOnlyPositiveTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone ="4522346623"+j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone(phone)
                .address("F")
                .description("")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//div/h2[text()='Emma']")));
        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//div/h3[text()='"+phone+"']")));

    }


}
