package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        WebElement loginLink = wd.findElement(By.xpath("//a[@href='/login']"));
        loginLink.click();


    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@name='email']"),email);
        type(By.xpath("//input[@name='password']"),password);


    }

    public void submitLoginForm() {
       click(By.xpath("//button[@name='login']"));


    }

    public void checkLoggedIn() {

        WebElement signOutButton = wd.findElement(By.xpath("//button[text()='Sign Out']"));

    }
}
