package com.example.defense_souls;

public class OrderListItem {
    int listId;
    String title;
    int imageNo;
    String description;
    String level;
    
    public OrderListItem(int listId, String title, int imageNo, String description, String level) {
        this.listId = listId;
        this.title = title;
        this.imageNo = imageNo;
        this.description = description;
        this.level = level;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }


}
