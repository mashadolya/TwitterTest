package twitter.pageobject.menu;

public enum TweetActionMenuItem {
    /**
     * These variables can be used to navigate through the tweet menu located on the top right side of the tweet.
     */
    SHARE_VIA_DIRECT_MESSAGE("Share via Direct Message"),
    COPY_LINK_TO_TWEET("Copy link to Tweet"),
    EMBED_TWEET("Embed Tweet"),
    PIN_TWEET("Pin to your profile page"),
    UNPIN_TWEET("Unpin from profile page"),
    DELETE_TWEET("Delete Tweet");

    private final String menuItem;

    TweetActionMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }
}
