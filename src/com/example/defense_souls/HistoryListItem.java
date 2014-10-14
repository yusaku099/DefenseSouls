package com.example.defense_souls;

public class HistoryListItem {
    int listId;
    String title;
    int imageNo;
    String result;
    int cost;
    String date;
    
    public HistoryListItem(int listId, String title, int imageNo, String result, int cost, String date) {
        this.listId = listId;
        this.title = title;
        this.imageNo = imageNo;
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
