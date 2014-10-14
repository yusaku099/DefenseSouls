package com.example.defense_souls;

public class ListItem {
    int listId;
    String title;
    int imageNo;
    String description;
    String result;
    int cost;
    String date;
    
    public ListItem(int listId, String title, int imageNo, String description, String result, int cost, String date) {
        this.listId = listId;
        this.title = title;
        this.imageNo = imageNo;
        this.description = description;
        this.result = result;
        this.cost = cost;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
