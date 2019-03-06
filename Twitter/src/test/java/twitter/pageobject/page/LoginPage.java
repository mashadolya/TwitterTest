package twitter.pageobject.page;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import twitter.model.User;

public class LoginPage extends BasePage {
    private final String LOC_INPUT_LOGIN_DATA_FORMAT = "//input[contains(@autocomplete,'%s')]";
    private final String TYPE_USERNAME = "username";
    private final String TYPE_PASSWORD = "password";
    private Button btnLogIn = new Button(By.xpath("//input[contains(@type,'submit')]"), "Log in");

    public LoginPage() {
        super(By.className("StaticLoggedOutHomePage-signupBlock"), "Twitter Login Page");
    }

    private TextBox getLogInTb(String type) {
        return new TextBox(By.xpath(String.format(LOC_INPUT_LOGIN_DATA_FORMAT, type)), type);
    }

    private void enterLogInData(String type, String text) {
        getLogInTb(type).sendKeys(text);
    }

    public void logIn(User user) {
        enterLogInData(TYPE_USERNAME, user.getUsername());
        enterLogInData(TYPE_PASSWORD, user.getPassword());
        btnLogIn.click();
    }
}
