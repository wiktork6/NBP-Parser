package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidator {
    private static final String USD_CODE = "USD";
    private static final String EUR_CODE = "EUR";
    private static final String GBP_CODE = "GBP";
    private static final String CHF_CODE = "CHF";
    public static final String DATE_FORMAT = "yyyy-MM-dd";


    public DataValidator() {
    }

    public String replaceChar(String oldWord, char oldChar, char newChar ){
        StringBuilder stringBuilder = new StringBuilder(oldWord);
        int index = 0;
        for(Character character : oldWord.toCharArray()){
            if(character.equals(oldChar)){
                break;
            }
            index++;
        }
        stringBuilder.setCharAt(index,newChar);
        return stringBuilder.toString();
    }


    public boolean isDateValid(String date){
        try {
            DateFormat df = new SimpleDateFormat(this.DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    public boolean validateCurrency(String currency){
        return currency.equals("USD") || currency.equals("EUR") || currency.equals("CHF") || currency.equals("GBP");
    }

    public boolean isDateRangeValid(String startDate, String endDate){
        try{
            SimpleDateFormat sdformat = new SimpleDateFormat(DATE_FORMAT);
            Date d1 = sdformat.parse(startDate);
            Date d2 = sdformat.parse(endDate);
            return !(d1.compareTo(d2)>0);
        }catch(ParseException e){
            return false;
        }
    }
    public boolean isDateInRange(String startDate, String endDate, String date){
        try{
            SimpleDateFormat sdformat = new SimpleDateFormat(DATE_FORMAT);
            Date start = sdformat.parse(startDate);
            Date end = sdformat.parse(endDate);
            Date d = sdformat.parse(date);
            return (d.compareTo(start)>=0 && d.compareTo(end)<=0);
        }catch(ParseException e){
            return false;
        }


    }
}

