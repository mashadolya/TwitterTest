package framework;

import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.WebDriver;

public abstract class BaseEntity {

    protected static WebDriver driver;
    private static int step = 1;
    protected static Log logger = Log.getInstance();

    @Before
    public void before() {
        logger.initStep(step);
        driver = BrowserFactory.getInstance().getDriver();
        Browser.maximize();
        Browser.openWebSite();
        Waits.implicitly(driver);
    }

    @After
    public void after() {
        BrowserFactory.quitDriver();
    }


    protected static String getLoc(final String key) {
        return logger.getLoc(key);
    }

    protected abstract String formatLogMsg(String message);

    protected void info(final String message) {
        logger.info(formatLogMsg(message));
    }

    protected void warn(final String message) {
        logger.warn(formatLogMsg(message));
    }

    protected void error(final String message) {
        logger.error(formatLogMsg(message));
    }

    protected void fatal(final String message) {
        logger.fatal(formatLogMsg(message));
    }
}
