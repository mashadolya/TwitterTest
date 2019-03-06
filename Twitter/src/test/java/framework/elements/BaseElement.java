package framework.elements;


import framework.BaseEntity;
import framework.Log;
import framework.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public abstract class BaseElement extends BaseEntity {
    protected WebElement element;
    private By by;
    private String name;

    public BaseElement(By by) {
        this.by = by;
    }

    public BaseElement(By by, String name) {
        this.by = by;
        this.name = name;
    }

    public BaseElement(WebElement element) {
        this.element = element;
    }

    protected abstract String getElementType();

    public WebElement getElement() {
        waitForIsElementPresent();
        return driver.findElement(by);
    }

    public List<WebElement> getElements() {
        Waits.waitForPageLoad();
        return driver.findElements(by);
    }

    public boolean isDisplayed() {
        element = getElement();
        return element.isDisplayed();
    }

    public boolean isElementPresent() {
        try {
            waitForIsElementPresent();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private By getLocator() {
        return this.by;
    }

    private String getName() {
        return this.name;
    }

    public void clearAll() {
        info(getLoc("loc.text.removed"));
        getElement().clear();

    }

    public String getAttribute(String attribute) {
        return getElement().getAttribute(attribute);
    }

    protected String formatLogMsg(final String message) {
        return String.format("%1$s '%2$s' %3$s %4$s", getElementType(), name, Log.LOG_DELIMITER, message);
    }

    public void click() {
        element = getElement();
        Waits.elementToBeClickable(by);
        info(getLoc("loc.clicking"));
        element.click();
    }

    public void clickAndWait() {
        element = getElement();
        info(getLoc("loc.clicking"));
        Waits.elementToBeClickable(by);
        element.click();
        Waits.waitForPageLoad();
    }

    public String getText() {
        element = getElement();
        return element.getText();
    }

    public String getElementText() {
        return this.element.getText();
    }


    public void moveAndClickByAction() {
        info(getLoc("loc.move.click"));
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement()).build().perform();
        actions.click(getElement()).build().perform();

    }

    public void moveToElement() {
        info(getLoc("loc.move.to"));
        Actions actions = new Actions(driver);
        waitForIsElementPresent();
        actions.moveToElement(getElement()).build().perform();

    }

    public void clickByAction() {
        info(getLoc("loc.clicking"));
        Waits.elementToBeClickable(by);
        Actions actions = new Actions(driver);
        actions.click(getElement()).build().perform();
        Waits.waitForPageLoad();
    }

    private void waitForIsElementPresent() {
        Waits.presenceOfElementLocated(by);
    }

    public void clickByJS() {
        info(getLoc("loc.clicking"));
        WebElement mapObject = getElement();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mapObject);
    }
}