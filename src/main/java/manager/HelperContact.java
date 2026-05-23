package manager;

import com.google.common.io.Files;
import models.Contact;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;


public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddNewContactForm() {
        click(By.xpath("//a[@href='/add']"));
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
}
