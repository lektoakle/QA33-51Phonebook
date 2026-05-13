package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String data) {
        WebElement field = wd.findElement(locator);
        field.click();
        clearNew(field);
        field.sendKeys(data);
    }

    public void clearNew(WebElement element){
        String os = System.getProperty("os.name");
//        System.out.println(os);


        element.sendKeys(" ");
//        element.sendKeys(Keys.BACK_SPACE);

        if(os.startsWith("Win")||os.startsWith("Lin")){
            element.sendKeys(Keys.CONTROL,"a");
        }else {
            element.sendKeys(Keys.COMMAND,"a");
        }
        element.sendKeys(Keys.DELETE);
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

    public String getErrorMessage() {

        WebElement error = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".login_login__3EHKB>div")));
        return error.getText();
    }


    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        if (alert != null && alert.getText().contains(message)) {
            // System.out.println(alert.getText());
            //click OK -->alert.accept();
            // click cancel -->alert.dismiss();
            //type into alert -->alert.sendKeys("text");
            //pause(2000);
            alert.accept();
            return true;
        }
        return false;
    }
//
//    public String getAlertMessage() {
//        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(10))
//                .until(ExpectedConditions.alertIsPresent());
//        if (alert != null)
//            return alert.getText();
//        return "";
//
//    }


    public void closeAlert() {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(1))
                .until(ExpectedConditions.alertIsPresent());
        if(alert!=null){
            alert.dismiss();
        }
    }
}
