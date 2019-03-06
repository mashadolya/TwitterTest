package twitter.pageobject.form;

import framework.BasePage;
import framework.Browser;
import framework.elements.Button;
import org.openqa.selenium.By;

public class DeleteTweetForm extends BasePage {
    private final String LOC_BTN_FORMAT = "//div[contains(@id,'delete-tweet-dialog-dialog')]//button[contains(@class,'%s-action')]";

    public DeleteTweetForm() {
        super(By.id("delete-tweet-dialog-dialog"), "Delete tweet form");
    }

    public enum ChoiceType {
        CANCEL,
        DELETE
    }

    private Button getBtnForChoice(ChoiceType choice) {
        return new Button(By.xpath(String.format(LOC_BTN_FORMAT, choice.name().toLowerCase())), choice.name());
    }

    public void makeSureToDelete(ChoiceType choice) {
        getBtnForChoice(choice).click();
        Browser.refresh();
    }
}
