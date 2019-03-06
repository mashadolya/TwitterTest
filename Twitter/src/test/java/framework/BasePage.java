package framework;


import framework.elements.Label;
import org.junit.Assert;
import org.openqa.selenium.By;

public class BasePage {
    private Log log = Log.getInstance();
    private By pageLocator;
    private String title;

    public BasePage(By locator, String title) {
        init(locator, title);
        Assert.assertTrue(String.format("%s not open", title), isOpen());
        log.info(title + " " + log.getLoc("loc.open.page"));
    }

    public BasePage() {
    }

    private boolean isOpen() {
        return new Label(pageLocator).isElementPresent();
    }

    private void init(By locator, String title) {
        this.pageLocator = locator;
        this.title = title;
    }
}
