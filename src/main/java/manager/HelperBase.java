package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String data) {
        WebElement field = wd.findElement(locator);
        field.click();
        field.clear();
        field.sendKeys(data);
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }
}
