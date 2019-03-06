package twitter.pageobject.menu;

public enum ProfileAndSettingsItem {
    /**
     * These variables can be used to navigate through the profile and settings menu located on the top right corner of the webpage.
     */
    LOG_OUT("Log out"),
    KEYBORD_SHORTCUTS("Keyboard shortcuts");

    private final String menuItem;

    ProfileAndSettingsItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }
}
