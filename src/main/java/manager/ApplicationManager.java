package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    WebDriver wd;
    HelperUser helperUser;

    HelperContact helperContact;


    public void init() {
        wd = new ChromeDriver();
        logger.info("All tests run in Chrome browser");
        wd.manage().window().maximize();
        wd.navigate().to("https://telranedu.web.app/");
        logger.info("url:" + wd.getCurrentUrl());
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);
    }

    ;


    public void stop() {
        wd.quit();
    }

    ;

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }


}
