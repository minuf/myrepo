package com.selfiedrop.selfiedrop.selfiedrop.items_struc;

/**
 * Created by jorge on 14/08/15.
 */
public class ItemListMainStruc {
    private String title;
    private String subtitle;
    private int item_count;

    public ItemListMainStruc(String title, String subtitle, int item_count) {
        this.title = title;
        this.subtitle = subtitle;
        this.item_count = item_count;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getItem_count() {
        return item_count;
    }
}
