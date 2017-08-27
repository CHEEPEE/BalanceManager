package com.kejicorp.screensizematters.models;

/**
 * Created by Keji's Lab on 27/08/2017.
 */

public class BalanceModelList {

    private String userName;
    private String balance;
    private String description;

    public String getUserName()
    {
        return userName;
    }
    public String getBalance()
    {
        return balance;
    }
    public String getDescription()
    {
        return description;
    }
    public void setUsername (String setUsername)
    {
        userName = setUsername;
    }
    public void setBalance(String setBalance)
    {
        balance = setBalance;
    }
    public void setDescription (String setDesciption)
    {
        description = setDesciption;
    }
}
