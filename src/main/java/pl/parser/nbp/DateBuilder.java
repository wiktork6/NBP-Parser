package pl.parser.nbp;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DateBuilder {

    public DateBuilder(){
    }

    public List<String> getDates(String startDate, String endDate){
        List<String> dates = new LinkedList();
        List<LocalDate> listOfDates = getLocalDates(startDate, endDate);
        for(int i=0; i<listOfDates.size();i++){
            dates.add(listOfDates.get(i).toString());
        }
        return dates;
    }

    private List<LocalDate> getLocalDates(String startDate, String endDate){
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<LocalDate> dates = new LinkedList<>(start.datesUntil(end).collect(Collectors.toList()));
        dates.add(end);
        return dates;
    }

}
