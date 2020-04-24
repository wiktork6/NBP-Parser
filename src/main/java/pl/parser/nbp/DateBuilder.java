package pl.parser.nbp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DateBuilder {

    public List<String> getDates(String startDate, String endDate){
        List<String> dates = new LinkedList();
        List<LocalDate> listOfDates = getLocalDates(startDate, endDate);
        for(int i=0; i<listOfDates.size();i++){
            dates.add(listOfDates.get(i).toString());
        }
        return dates;
    }

    private List<LocalDate> getLocalDates(String startDate, String endDate){
        LocalDate start;
        LocalDate end;
        List<LocalDate> dates;
        try{
            start = LocalDate.parse(startDate);
            end = LocalDate.parse(endDate);
            dates = new LinkedList<>(start.datesUntil(end).collect(Collectors.toList()));
        }catch(IllegalArgumentException | DateTimeParseException e){
            return new LinkedList<>();
        }
        dates.add(end);
        return dates;
    }

}
