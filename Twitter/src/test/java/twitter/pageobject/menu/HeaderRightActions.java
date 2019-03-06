package twitter.pageobject.menu;

import framework.BasePage;
import framework.Log;
import framework.elements.Button;
import org.openqa.selenium.By;

public class HeaderRightActions extends BasePage {
    private Log log = Log.getInstance();
    private String LOC_PROFILE_AND_SETTING_ITEM_FORMAT = "//div[contains(@class,'DashUserDropdown dropdown-menu')]//button[contains(text(),'%s')]";

    private Button getBtnNavigate(HeaderRightActionsItem headerRightActionsItem) {
        return new Button(By.id(String.format("%s", headerRightActionsItem.getMenuItem())), headerRightActionsItem.name());
    }

    private Button getBtnNavigateProfileAndSettingsItem(ProfileAndSettingsItem profileAndSettingsItem) {
        return new Button(By.xpath(String.format(LOC_PROFILE_AND_SETTING_ITEM_FORMAT, profileAndSettingsItem.getMenuItem())), profileAndSettingsItem.getMenuItem());

    }

    public void navigateUserProfileMenu(HeaderRightActionsItem headerRightActionsItem, ProfileAndSettingsItem profileAndSettingsItem) {
        log.info(String.format("%1$s '%2$s' %3$s", log.getLoc("loc.navigate"), headerRightActionsItem, "Top Header Right Menu"));
        getBtnNavigate(headerRightActionsItem).clickAndWait();
        getBtnNavigateProfileAndSettingsItem(profileAndSettingsItem).click();
    }
}
