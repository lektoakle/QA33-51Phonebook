package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return !list.isEmpty();
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
