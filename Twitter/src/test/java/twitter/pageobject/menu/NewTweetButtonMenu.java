package twitter.pageobject.menu;

import framework.BasePage;
import framework.Log;
import framework.elements.Button;
import org.openqa.selenium.By;

public class NewTweetButtonMenu extends BasePage {
    private Log log = Log.getInstance();
    private final String LOC_NAVIGATE_FORMAT ="//div[contains(@class,'TweetBoxToolbar-tweetButton tweet-button')]//span[contains(@class,'%s')]";

    private Button getBtnNavigate(NewTweetButtonMenuItem newTweetMenuItem) {
        return new Button(By.xpath(String.format(LOC_NAVIGATE_FORMAT, newTweetMenuItem.getMenuItem())),newTweetMenuItem.name());
    }

    public void navigateMenuBar(NewTweetButtonMenuItem newTweetMenuItem) {
        log.info(String.format("%1$s '%2$s' %3$s", log.getLoc("loc.navigate"), newTweetMenuItem, "Tweet Button Menu"));
        getBtnNavigate(newTweetMenuItem).click();
    }
}
