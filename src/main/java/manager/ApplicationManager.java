package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    WebDriver wd;
    HelperUser helperUser;
    HelperContact helperContact;
    String browser;

    public ApplicationManager(String browser) {
        logger.info(">>> JVM browser = " + System.getProperty("browser"));
        logger.info(">>> constructor browser = " + browser);
        this.browser = browser;
    }


    public void init() {
        if (browser.equals(Browser.CHROME.browserName())){
            wd = new ChromeDriver();
            logger.info("All tests run in Chrome browser");
        } else if (browser.equals(Browser.FIREFOX.browserName())){
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox-bin");
            wd = new FirefoxDriver(options);
            logger.info("All tests run in Firefox browser");
        } else if (browser.equals(Browser.EDGE.browserName())){
            wd = new EdgeDriver();
            logger.info("All tests run in Edge browser");
        }
        else if (browser.equals(Browser.IE.browserName())){
            wd = new InternetExplorerDriver();
            logger.info("All tests run in IE browser");
        }
        else if (browser.equals(Browser.SAFARI.browserName())){
            wd = new SafariDriver();
            logger.info("All tests run in Safari browser");
        }


        wd.manage().window().maximize();
        wd.navigate().to("https://telranedu.web.app/");
        logger.info("url:" + wd.getCurrentUrl());
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverListener webDriverListener = new ListenerWD();
        wd = new EventFiringDecorator(webDriverListener).decorate(wd);

        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);


    }

    ;


    public void stop() {
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }




    ;


}
