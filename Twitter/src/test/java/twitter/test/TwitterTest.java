package twitter.test;

import framework.BaseTest;
import framework.CSVUtils;
import framework.utils.PropertyReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import twitter.model.Tweet;
import twitter.model.User;
import twitter.pageobject.page.AccountPage;
import twitter.pageobject.page.LoginPage;
import twitter.step.CustomSteps;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.UUID;

@RunWith(Parameterized.class)
public class TwitterTest extends BaseTest {
    private static final String FILE_PATH = PropertyReader.getProperty("dataStorageFilePath");
    private static final String FILE_NAME = PropertyReader.getProperty("dataStorageFileName");
    private static Properties testData = CSVUtils.getProperties(FILE_PATH, FILE_NAME);
    private User user;
    private Tweet tweet;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {testData.get("username"),
                        testData.get("password"),
                },};
        return Arrays.asList(data);
    }

    public TwitterTest(String username, String password) {
        user = new User(username, password);
        tweet = new Tweet(UUID.randomUUID().toString());
    }

    @Test
    public void checkCreatingDeletingTweet() {
        extentTest.info("Navigate to 'https://twitter.com/'");
        LoginPage loginPage = new LoginPage();
        extentTest.info(String.format("Log in with username '%s' and password '%s'", user.getUsername(), user.getPassword()));
        loginPage.logIn(user);

        AccountPage accountPage = new AccountPage();
        CustomSteps.createNewTweet(extentTest,accountPage,tweet);

        CustomSteps.tweetVerification(extentTest,user,tweet,accountPage);

        CustomSteps.deleteTweet(extentTest, user, tweet, accountPage);

        extentTest.info(String.format("Make sure that the tweet with text '%s' from user with username '%s' was deleted", tweet.getText(), user.getUsername()));
        Assert.assertTrue(!accountPage.isTweetTextPresent(user, tweet));

        CustomSteps.logOut(extentTest,user,accountPage);
    }
}
