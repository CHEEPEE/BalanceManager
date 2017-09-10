package com.kejicorp.screensizematters.utils;

import android.widget.Switch;

import java.lang.reflect.Array;

/**
 * Created by Keji's Lab on 27/08/2017.
 */

public class UtilDatabaseStrings {
    public static String tb_balance_manager = "tb_balance_manager";
    public static String tb_b_users = "user";
    public static String tb_b_balance = "balance";
    public static String tb_b_date = "date";
    public static String tb_b_time = "time";
    public static String tb_b_status = "status";
    public static String tb_b_description = "description";
    public static String tb_b_id = "id";


//    for users table

    public static String tb_users_manager="tb_user_manager";
    public static String tb_u_users = "user_name";
    public static String tb_u_totalBalance = "user_balance";
    public static String tb_u_user_contact = "user_contact_number";

    public static String formatTheDate(String date){
    // "yyyy.MM.dd.HH.mm.ss" present format to be converted
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        month = UtilDatabaseStrings.getMonthInWords(month);



        return month+" "+day+", "+year;
    }

    private static String getMonthInWords(String num){
        int num_month = Integer.parseInt(num);
        String[] months = {"January","Febuary","March","April","May","June","July","August","September","October","November","December"};
        return months[num_month-1];
    }



}
