package twitter.step;

import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import twitter.model.Tweet;
import twitter.model.User;
import twitter.pageobject.form.DeleteTweetForm;
import twitter.pageobject.form.NewTweetForm;
import twitter.pageobject.menu.HeaderRightActionsItem;
import twitter.pageobject.menu.NewTweetButtonMenuItem;
import twitter.pageobject.menu.ProfileAndSettingsItem;
import twitter.pageobject.menu.TweetActionMenuItem;
import twitter.pageobject.page.AccountPage;
import twitter.pageobject.page.LoginPage;

public class CustomSteps {

    public static void deleteTweet(ExtentTest extentTest, User user, Tweet tweet, AccountPage accountPage) {
        extentTest.info(String.format("Delete tweet with text '%s' from user with username '%s'", tweet.getText(), user.getUsername()));
        String pathToNecessaryTweet = String.format("%s%s", AccountPage.getLocTweetUsername(user), AccountPage.getLocTweetText(tweet));
        accountPage.getTweetActionMenu().navigateMenuBar(pathToNecessaryTweet, TweetActionMenuItem.DELETE_TWEET);
        DeleteTweetForm deleteTweetForm = accountPage.getDeleteTweetForm();
        deleteTweetForm.makeSureToDelete(DeleteTweetForm.ChoiceType.DELETE);
    }

    public static void createNewTweet(ExtentTest extentTest, AccountPage accountPage, Tweet tweet) {
        NewTweetForm newTweetForm = accountPage.getNewTweetForm();
        extentTest.info(String.format("Create new tweet with text '%s'", tweet.getText()));
        newTweetForm.writeText(tweet.getText());
        newTweetForm.getNewTweetButtonMenu().navigateMenuBar(NewTweetButtonMenuItem.TWEET);
    }

    public static void tweetVerification(ExtentTest extentTest, User user, Tweet tweet, AccountPage accountPage) {
        extentTest.info(String.format("Make sure that the from user with username '%s' exists", user.getUsername()));
        Assert.assertTrue(String.format("Username must be '%s'", user.getUsername()), accountPage.isTweetUsernameAsExpected(user));
        extentTest.info(String.format("Make sure that the tweet with text '%s' from user with username '%s' exists", tweet.getText(), user.getUsername()));
        Assert.assertTrue(String.format("Tweet text must be '%s'", tweet.getText()), accountPage.isTweetTextPresent(user, tweet));
    }

    public static void logOut(ExtentTest extentTest, User user, AccountPage accountPage) {
        extentTest.info(String.format("User '%s' logged out", user.getUsername()));
        accountPage.getHeaderRightActions().navigateUserProfileMenu(HeaderRightActionsItem.ME_DROP_DOWN, ProfileAndSettingsItem.LOG_OUT);
        new LoginPage();
    }
}
