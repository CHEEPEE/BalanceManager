package com.kejicorp.screensizematters.models;

/**
 * Created by Keji's Lab on 27/08/2017.
 */

public class BalanceModelList {

    private String userName;
    private String itemId;
    private String balance;
    private String description;
    private String date;

    public String getUserName() {
        return userName;
    }
    public String getDate(){
        return date;
    }
    public String getId(){
        return itemId;
    }
    public void setItemId(String setId){
        itemId  = setId;
    }

    public String getBalance() {
        return balance;
    }

    public String getDescription() {
        return description;
    }

    public void setUsername(String setUsername) {
        userName = setUsername;
    }

    public void setBalance(String setBalance) {
        balance = setBalance;
    }

    public void setDescription(String setDesciption) {
        description = setDesciption;
    }

    public void setDate(String setDate){
        date = setDate;
    }
}
