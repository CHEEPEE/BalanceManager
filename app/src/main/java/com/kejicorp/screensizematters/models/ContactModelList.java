package com.kejicorp.screensizematters.models;

/**
 * Created by Keji's Lab on 27/08/2017.
 */

public class ContactModelList {

    private String userName;
    private String total_balance;
    private String contact_number;

    public String getUserName()
    {
        return userName;
    }
    public String getTotal_balance()
    {
        return total_balance;
    }
    public String getContact_number()
    {
        return contact_number;
    }
    public void setUsername (String setUsername)
    {
        userName = setUsername;
    }
    public void setTotal_balance(String setBalance)
    {
        total_balance = setBalance;
    }
    public void setContact_number(String setContact)
    {
        contact_number = setContact;
    }

}
