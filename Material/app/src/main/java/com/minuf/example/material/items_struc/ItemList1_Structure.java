package com.minuf.example.material.items_struc;

/**
 * Created by jorge on 14/08/15.
 */
public class ItemList1_Structure {
    private int image;
    private String title;
    private String subtitle;
    private int item_count;

    public ItemList1_Structure(int image, String title, String subtitle, int item_count) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.item_count = item_count;
    }
    public int getImage() { return image; }

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
