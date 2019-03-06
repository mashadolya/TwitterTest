package twitter.pageobject.menu;

public enum NewTweetButtonMenuItem {
    /**
     * These variables can be used to navigate through the menu located on the right corner of the new tweet form.
     */
    ADD_ANOTHER_TWEET("Icon Icon--add Icon--medium"),
    TWEET("button-text tweeting-text");

    private final String menuItem;

    NewTweetButtonMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }
}
