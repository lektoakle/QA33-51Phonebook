package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
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
        String phone = "4522346623" + j;
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
        Assert.assertTrue(app.getHelperContact().isContactAdded(contact));
    }


    @Test
    public void AddNewContactRequiredFieldsOnlyPositiveTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
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
        Assert.assertTrue(app.getHelperContact().isContactAdded(contact));

    }

    @Test
    public void AddNewContactEmptyNameFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone(phone)
                .address("F")
                .description("F")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isSaveButtonDisabled();
        app.getHelperContact().isAddButtonSelected();

    }


    @Test
    public void AddNewContactEmptyLastNameFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("")
                .email("emma" + i + "@gmail.com")
                .phone(phone)
                .address("F")
                .description("F")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isSaveButtonDisabled();
        app.getHelperContact().isAddButtonSelected();
    }


    @Test
    public void AddNewContactInvalidEmailFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("emma" + i + "gmail.com")
                .phone(phone)
                .address("F")
                .description("F")
                .build();

        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isAlertPresent("Email not valid: must be a well-formed email address");
        app.getHelperContact().isAddButtonSelected();

    }


    @Test(description = "bugreport")
    public void AddNewContactEmptyEmailFieldsNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("")
                .phone(phone)
                .address("F")
                .description("F")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isAlertPresent("Email not valid: must be a well-formed email address");
        app.getHelperContact().isAddButtonSelected();

    }


    @Test(description = "bugreport")
    public void AddNewContactRepeatedEmailFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone(phone)
                .address("F")
                .description("F")
                .build();
        Contact contact2 = Contact.builder()
                .name("Anna")
                .lastName("Green")
                .email("emma" + i + "@gmail.com")
                .phone(j + "4522346623")
                .address("F")
                .description("F")
                .build();

        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAdded(contact));
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact2);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isAlertPresent("Email must be unique");
        app.getHelperContact().isAddButtonSelected();
        Assert.assertFalse(app.getHelperContact().isContactAdded(contact2));


    }


    @Test
    public void AddNewContactEmptyPhoneFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone("")
                .address("F")
                .description("F")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!");
        app.getHelperContact().isAddButtonSelected();
    }


    @Test
    public void AddNewContactInvalidPhoneFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone("123456")
                .address("F")
                .description("F")
                .build();

        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!");
        app.getHelperContact().isAddButtonSelected();
    }


    @Test(description = "bugreport")
    public void AddNewContactRepeatedPhoneFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone(phone)
                .address("F")
                .description("F")
                .build();
        Contact contact2 = Contact.builder()
                .name("Anna")
                .lastName("Green")
                .email("anna" + i + "@gmail.com")
                .phone(phone)
                .address("F")
                .description("F")
                .build();

        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();

        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact2);
        app.getHelperContact().submitAddNewContactForm();

        app.getHelperContact().isAlertPresent("Phone must be unique");

        app.getHelperContact().isAddButtonSelected();
        Assert.assertFalse(app.getHelperContact().isContactAdded(contact2));
    }


    public void AddNewContactEmptyAddressFieldNegativeTest() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        int j = random.nextInt(99999);
        String phone = "4522346623" + j;
        Contact contact = Contact.builder()
                .name("Emma")
                .lastName("Smith")
                .email("emma" + i + "@gmail.com")
                .phone(phone)
                .address("")
                .description("F")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        app.getHelperContact().isSaveButtonDisabled();
        app.getHelperContact().isAddButtonSelected();
    }
}
