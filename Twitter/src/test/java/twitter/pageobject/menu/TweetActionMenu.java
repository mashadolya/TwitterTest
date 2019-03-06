package twitter.pageobject.menu;

import framework.BasePage;
import framework.Log;
import framework.elements.Button;
import org.openqa.selenium.By;

public class TweetActionMenu extends BasePage {
    private Log log = Log.getInstance();
    private final String LOC_BTN_MORE_FORMAT = "%s//preceding::span[contains(@class,'Icon Icon--caretDownLight Icon--small')]";
    private final String LOC_NAVIGATE_FORMAT = "//button[contains(text(),'%s')]";

    private Button getBtnMore(String path) {
        return new Button(By.xpath(String.format(LOC_BTN_MORE_FORMAT, path)), "More");
    }

    private Button getBtnNavigate(TweetActionMenuItem tweetActionMenuItem) {
        return new Button(By.xpath(String.format(LOC_NAVIGATE_FORMAT, tweetActionMenuItem.getMenuItem())), tweetActionMenuItem.name());
    }

    public void navigateMenuBar(String pathToTweet, TweetActionMenuItem tweetActionMenuItem) {
        getBtnMore(pathToTweet).click();
        log.info(String.format("%1$s '%2$s' %3$s", log.getLoc("loc.navigate"), tweetActionMenuItem, "Tweet Action Menu"));
        getBtnNavigate(tweetActionMenuItem).moveAndClickByAction();
    }
}
