package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        this.browser = browser;

    }


    public void init() {
        if (browser.equals(Browser.CHROME.browserName())) {
            try {
                ChromeOptions options = new ChromeOptions();
//            String headless = System.getProperty("headless");
//
//            if ("true".equalsIgnoreCase(headless)) {
//                options.addArguments("--headless=new");
//            }

                options.setBinary("/opt/chrome/chrome-linux64/chrome");

                options.addArguments("--window-size=1920,1080");
                options.addArguments("--headless=new");

                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--disable-gpu");
//                options.addArguments("--disable-features=UseOzonePlatform");
//            options.addArguments("--disable-features=VizDisplayCompositor");
//            options.addArguments("--user-data-dir=/tmp/chrome-" + System.nanoTime());
//            options.addArguments("--user-data-dir=/tmp/chrome-jenkins");
//
//                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//                System.setProperty("webdriver.chrome.verboseLogging", "true");
//                System.setProperty("webdriver.chrome.logfile", "/tmp/chromedriver.log");

                wd = new ChromeDriver(options);
                System.out.println("Driver created: " + wd);
                logger.info("All tests run in Chrome browser, driver  created " + wd);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("/opt/firefox/firefox");
            options.addArguments("--headless");
//            options.addArguments("--headless=new");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-gpu");
            System.setProperty("webdriver.firefox.verboseLogging", "true");
            System.setProperty(
                    "webdriver.gecko.driver",
                    "/usr/local/bin/geckodriver-clean"
            );
            wd = new FirefoxDriver(options);
            System.out.println("driver in setup = " + wd);
            System.out.println("browser = firefox");
            logger.info("All tests run in Firefox browser");
        } else if (browser.equals(Browser.EDGE.browserName())) {
            wd = new EdgeDriver();
            logger.info("All tests run in Edge browser");
        } else if (browser.equals(Browser.IE.browserName())) {
            wd = new InternetExplorerDriver();
            logger.info("All tests run in IE browser");
        } else if (browser.equals(Browser.SAFARI.browserName())) {
            wd = new SafariDriver();
            logger.info("All tests run in Safari browser");
        }


//        wd.manage().window().maximize();
        System.out.println("navigate to the app");
        wd.navigate().to("https://telranedu.web.app/");
        System.out.println("done navigating to the app");
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
