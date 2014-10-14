package com.example.defense_souls;

public class StatusListItem {
    int listId;
    String title;
    int imageNo;
    
    public StatusListItem(int listId, String title, int imageNo) {
        this.listId = listId;
        this.title = title;
        this.imageNo = imageNo;
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

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }


}
