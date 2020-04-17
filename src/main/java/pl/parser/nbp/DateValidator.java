package pl.parser.nbp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator implements Validator {

    public String dateFormat;


    public DateValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String date){
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

//    public boolean isValid(String startDate, String endDate){
//
//    }

    public boolean isDateLater(String startDate, String endDate){
        try{
            SimpleDateFormat sdformat = new SimpleDateFormat(dateFormat);
            Date d1 = sdformat.parse(startDate);
            Date d2 = sdformat.parse(endDate);
            return d1.compareTo(d2)>0;
        }catch(ParseException e){
            return false;
        }
    }


    public boolean isDateInFuture(String date) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat(dateFormat);
        Date d1 = sdformat.parse(date);

        Date dateNow = new Date();
        Date d2 = sdformat.parse(sdformat.format(dateNow));

        return d1.compareTo(d2)>=0;
    }

    public boolean isDateInRange(String startDate, String endDate, String date){
        try{
            SimpleDateFormat sdformat = new SimpleDateFormat(dateFormat);
            Date start = sdformat.parse(startDate);
            Date end = sdformat.parse(endDate);
            Date d = sdformat.parse(date);
            return (d.compareTo(start)>=0 && d.compareTo(end)<=0);
        }catch(ParseException e){
            return false;
        }
    }


}

