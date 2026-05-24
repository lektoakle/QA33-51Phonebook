package manager;

import com.google.common.io.Files;
import models.Contact;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddNewContactForm() {
        click(By.xpath("//a[@href='/add']"));
        logger.info("tried to open new contact form");
    }

    public void fillAddNewContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());

    }

    public void submitAddNewContactForm() {
        click(By.xpath("//button/b[text()='Save']"));
    }

    public boolean isContactAdded(Contact contact) {
        openContacts();
        return isElementPresent(By.xpath("//div/h2[text()='" + contact.getName() + "']"))
                && isElementPresent(By.xpath("//div/h3[text()='" + contact.getPhone() + "']"));


    }

    public boolean isSaveButtonDisabled() {
        WebElement saveButton = wd.findElement(By.xpath("//button/b[text()='Save']"));
        return !saveButton.isEnabled();

    }

    public void openContacts() {
        click(By.xpath("//a[@href='/contacts']"));
    }

    public boolean isAddButtonSelected() {
        WebElement addButton = wd.findElement(By.xpath("//a[@href='/add']"));
        return !addButton.isSelected();
    }

    public void getScreen(String filepath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void provideContacts() {
        while (getContactsListSize() < 3) {
            Random random = new Random();
            int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
            Contact contact = Contact.builder()
                    .name("1A")
                    .lastName("1b")
                    .email("sdfasdf" + z + "@mail.com")
                    .phone(z + "2323423423")
                    .address("asdf")
                    .description("")
                    .build();
            addContact(contact);
        }
    }


    private void addContact(Contact contact) {
        openAddNewContactForm();
        fillAddNewContactForm(contact);
        submitAddNewContactForm();
    }

    public List<WebElement> getContactsList() {
//        openContacts();
        return wd.findElements(By.xpath("//div[contains(@class,'contact-item_card')]"));

    }

    public int getContactsListSize() {
        return getContactsList().size();
    }

    public void removeCurrentContact() {
        WebElement removeButton = wd.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();
    }

    public void removeFirstContact() {
        WebElement firstContact = wd.findElement(By.xpath("//div[contains(@class,'contact-item_card')]"));
        firstContact.click();
        WebElement removeButton = wd.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();

    }

    public void removeAllContacts() {
        By locator = By.xpath("//div[contains(@class,'contact-item_card')]");
        while (isElementPresent(locator)) {
            removeFirstContact();
            pause(10000);
//TODO get rid of the pause

//        while (getContactsListSize() > 0) {
//            removeFirstContact();
        }

    }

    public boolean isNoContactsMessagePresent() {
        return isElementPresent(By.xpath(("//div/h1[text()=' No Contacts here!']")));
    }
}
