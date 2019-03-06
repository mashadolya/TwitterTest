package twitter.pageobject.form;

import framework.BasePage;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import twitter.pageobject.menu.NewTweetButtonMenu;

public class NewTweetForm extends BasePage {
    private TextBox tbTweetText = new TextBox(By.xpath("//div[contains(@id,'tweet-box-home-timeline')]"), "New tweet text field");

    public NewTweetForm() {
        super(By.xpath("//div[contains(@class,'timeline-tweet-box')]"), "New Tweet Form");
    }

    public void writeText(String tweetText) {
        tbTweetText.sendKeys(tweetText);
    }

    public NewTweetButtonMenu getNewTweetButtonMenu() {
        return new NewTweetButtonMenu();
    }

}
