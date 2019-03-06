package twitter.pageobject.menu;

public enum HeaderRightActionsItem {
    /**
     * These variables can be used to navigate through the menu located on the top right corner of the webpage.
     */
    ME_DROP_DOWN("user-dropdown"),
    TWEET("global-new-tweet-button");

    private final String menuItem;

    HeaderRightActionsItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }
}
