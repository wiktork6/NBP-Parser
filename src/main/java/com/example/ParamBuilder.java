package com.example;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ParamBuilder {

    public ParamBuilder(){
    }

    public List<String> getParameters(String startDate, String endDate){
        List<LocalDate> listOfDates = getAllDates(startDate, endDate);
        List<String> listOfParams = new LinkedList();
        for(int i=0; i<listOfDates.size();i++){
            System.out.println(listOfDates.get(i).toString());
            listOfParams.add(listOfDates.get(i).toString());
        }
        return listOfParams;
    }

    private List<LocalDate> getAllDates(String startDate, String endDate){
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<LocalDate> listOfDates = new LinkedList<>(start.datesUntil(end).collect(Collectors.toList()));
        listOfDates.add(end);
        return listOfDates;
    }

}
