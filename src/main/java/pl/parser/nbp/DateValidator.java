package pl.parser.nbp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator implements Validator {
    
    private final DateFormat dateFormat;


    public DateValidator(String dateFormatString) {
        this.dateFormat = new SimpleDateFormat(dateFormatString);
        
    }

    @Override
    public boolean isValid(String date){
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public Integer compareDates(String startDate, String endDate){
        try{
            Date d1 = dateFormat.parse(startDate);
            Date d2 = dateFormat.parse(endDate);
            return d1.compareTo(d2);
        }catch(ParseException e){
            return null;
        }
    }


    public boolean isDateInFuture(String date) {
        try {
            Date d1 = dateFormat.parse(date);


            Date dateNow = new Date();
            Date d2 = dateFormat.parse(dateFormat.format(dateNow));

            return d1.compareTo(d2) >= 0;
        }catch (ParseException e){
            return false;
        }
    }

    public boolean isDateInRange(String startDate, String endDate, String date){
        try{
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            Date d = dateFormat.parse(date);
            return (d.compareTo(start)>=0 && d.compareTo(end)<=0);
        }catch(ParseException e){
            return false;
        }
    }


}

