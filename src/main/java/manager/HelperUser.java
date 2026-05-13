package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
        WebElement loginLink = wd.findElement(By.xpath("//a[@href='/login']"));
        loginLink.click();


    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.xpath("//input[@name='email']"), email);
        type(By.xpath("//input[@name='password']"), password);


    }

    public void fillLoginRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());
    }

    public void submitLoginForm() {
        click(By.xpath("//button[@name='login']"));

    }

    public boolean isLoggedIn() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logOut() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLoginForm();
    }

    public void submitRegistrationForm() {
        click(By.xpath("//button[@name='registration']"));
    }

    public boolean isOnContactsPage() {
        return new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlMatches("https://telranedu.web.app/contacts"));

    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }


}
