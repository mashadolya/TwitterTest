package framework;

import framework.utils.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Waits extends BaseEntity {
    private final static String TIME_OUT = "timeOut";
    private final static String TIME_SECONDS = "timeSeconds";
    private final static String TIME_MILLISECONDS = "timeMilliseconds";
    private final static String DEFAULT_TIME_OUT = "15";
    private final static String DEFAULT_TIME_SECONDS = "15";
    private final static String DEFAULT_TIME_MILLISECONDS = "10";


    private static <V> void fluentWait(Function<? super WebDriver, V> expectedConditions) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Long.parseLong(PropertyReader.getPropertyOrDefault(TIME_SECONDS, DEFAULT_TIME_SECONDS))))
                .pollingEvery(Duration.ofMillis(Long.parseLong(PropertyReader.getPropertyOrDefault(TIME_MILLISECONDS, DEFAULT_TIME_MILLISECONDS))))
                .ignoring(NoSuchElementException.class);
        if (expectedConditions != null) {
            fluentWait.until(expectedConditions);
        }
    }

    public static void elementToBeClickable(By by) {
        fluentWait(ExpectedConditions.elementToBeClickable(by));
    }

    public static void invisibilityOfElementLocated(By by) {
        fluentWait(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void presenceOfElementLocated(By by) {
        fluentWait(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void visibilityOfElementLocated(By by) {
        fluentWait(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void implicitly(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyReader.getPropertyOrDefault(TIME_OUT, DEFAULT_TIME_OUT)), TimeUnit.SECONDS);
    }


    public static void waitForPageLoad() {
        new WebDriverWait(driver, Long.parseLong(PropertyReader.getPropertyOrDefault(TIME_SECONDS, DEFAULT_TIME_SECONDS))).until((ExpectedCondition<Boolean>) wd ->
        {
            return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
        });
    }

    public static void explicitWait(By by) {
        new WebDriverWait(driver, Long.parseLong(PropertyReader.getPropertyOrDefault(TIME_SECONDS, DEFAULT_TIME_SECONDS)))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForLoad() {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, Long.parseLong(PropertyReader.getPropertyOrDefault(TIME_OUT, DEFAULT_TIME_OUT)));
            ExpectedCondition<Boolean> expectation;
            expectation = driverjs -> {
                JavascriptExecutor js = (JavascriptExecutor) driverjs;
                return js.executeScript("return(jQuery.active == 0)").equals("true");
            };
            driverWait.until(expectation);
        } catch (WebDriverException ignored) {
            logger.error("loc.err.wd.ex");
        }
    }

    @Override
    protected String formatLogMsg(String message) {
        return message;
    }
}