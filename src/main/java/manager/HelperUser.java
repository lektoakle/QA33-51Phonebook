package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        WebElement loginLink = wd.findElement(By.xpath("//a[@href='/login']"));
        loginLink.click();


    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@name='email']"), email);
        type(By.xpath("//input[@name='password']"), password);


    }

    public void fillLoginForm(User user) {
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

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if (alert != null && alert.getText().contains(message)) {
            alert.accept();
            return true;
        }
        return false;
    }

//
//    public void checkLoggedIn() {
//
//        WebElement signOutButton = wd.findElement(By.xpath("//button[text()='Sign Out']"));
//
//    }
}
