package framework;

import framework.utils.PropertyReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Browser extends BaseEntity {
    private final static String URL = "url";
    private final static String SCREENSHOT_PATH = PropertyReader.getProperty("pathForScreenshots");
    private final static String SCREENSHOT_EXTENSION = ".png";

    public static void openWebSite() {
        driver.get(PropertyReader.getProperty(URL));
    }

    public static void navigate(final String url) {
        driver.navigate().to(url);
    }

    public static void refresh() {
        driver.navigate().refresh();
    }

    public static void maximize() {
        driver.manage().window().maximize();
    }

    public static void captureScreenshot(String screenShotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File file = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(String.format("%s%s%s", SCREENSHOT_PATH, screenShotName, SCREENSHOT_EXTENSION)));
            logger.info(getLoc("loc.screenshot"));
        } catch (Exception e) {
            logger.error(getLoc("loc.err.screenshot"));
        }
    }

    @Override
    protected String formatLogMsg(final String message) {
        return String.format("%1$s '%2$s' %3$s", message, Log.LOG_DELIMITER, message);
    }
}
