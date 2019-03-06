package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class TextBox extends BaseElement {
    public TextBox(By by, String name) {
        super(by, name);
    }

    public void sendKeys(String text) {
        info(formatLogMessage(text));
        getElement().sendKeys(text);
    }

    public void sendKeysByJs(String text) {
        info(formatLogMessage(text));
        element = getElement();
        if (element != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", element, text);
        }
    }

    protected String getElementType() {
        return getLoc("loc.tb");
    }

    private String formatLogMessage(String text) {
        return String.format("%1$s '%2$s'", getLoc("loc.enter.text"), text);
    }
}
