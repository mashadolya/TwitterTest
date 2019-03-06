package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Label extends BaseElement {


    public Label(WebElement label) {
        super(label);
    }

    public Label(By by, String name) {
        super(by, name);
    }

    public Label(By by) {
        super(by);

    }

    public List<String> getTextElements() {
        List<String> labelList = new ArrayList<>();
        for (WebElement webElement : super.getElements()) {
            labelList.add(webElement.getText());
        }
        return labelList;
    }

    public List<Label> getLabels() {
        List<WebElement> webElements = super.getElements();
        List<Label> labels = new ArrayList<>();
        for (WebElement webElement : webElements) {
            labels.add(new Label(webElement));
        }
        return labels;
    }

    public Label getLabel(By by) {
        return new Label(getElement());
    }

    protected String getElementType() {
        return getLoc("loc.lb");
    }
}
