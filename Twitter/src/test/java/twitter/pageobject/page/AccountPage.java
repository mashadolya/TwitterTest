package twitter.pageobject.page;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;
import twitter.model.Tweet;
import twitter.model.User;
import twitter.pageobject.form.DeleteTweetForm;
import twitter.pageobject.form.NewTweetForm;
import twitter.pageobject.menu.HeaderRightActions;
import twitter.pageobject.menu.TweetActionMenu;

public class AccountPage extends BasePage {
    private static final String LOC_TWEET_FORMAT = "//div[contains(@class,'tweet js-stream-tweet') and @data-screen-name='%s']";
    private static final String LOC_TWEET_TEXT_FORMAT = "//div[contains(@class,'js-tweet-text-container')]//p[contains(text(),'%s')]";
    private TweetActionMenu tweetActionMenu = new TweetActionMenu();

    public AccountPage() {
        super(By.xpath("//li[contains(@id,'user-dropdown')]"), "Account Page");
    }

    public NewTweetForm getNewTweetForm() {
        return new NewTweetForm();
    }

    public DeleteTweetForm getDeleteTweetForm() {
        return new DeleteTweetForm();
    }

    public TweetActionMenu getTweetActionMenu() {
        return tweetActionMenu;
    }

    public HeaderRightActions getHeaderRightActions() {
        return new HeaderRightActions();
    }

    public static String getLocTweetUsername(User user) {
        return String.format(LOC_TWEET_FORMAT, user.getUsername());
    }

    private Label getLblTweetUsername(User user) {
        return new Label(By.xpath(getLocTweetUsername(user)), user.getUsername());
    }

    public boolean isTweetUsernameAsExpected(User user) {
        return getLblTweetUsername(user).isElementPresent();
    }

    public static String getLocTweetText(Tweet tweet) {
        return String.format(LOC_TWEET_TEXT_FORMAT, tweet.getText());
    }

    private Label getLblTweetText(User user, Tweet tweet) {
        return new Label(By.xpath(String.format("%s%s", getLocTweetUsername(user), getLocTweetText(tweet))));
    }

    public boolean isTweetTextPresent(User user, Tweet tweet) {
        return getLblTweetText(user, tweet).isElementPresent();
    }
}
