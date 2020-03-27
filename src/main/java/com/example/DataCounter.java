package com.example;

import java.util.List;

public class DataCounter {

    public DataCounter() {
    }

    public double average(List<Double> listOfValues){
        double sum = 0;
        int size = listOfValues.size();
        for(int i=0; i<size; i++){
            sum +=listOfValues.get(i);
        }

        return sum/size;
    }

    public double standardDeviation(List<Double> listOfValues){
        double squaredSum = 0;
        int size = listOfValues.size();
        double avg = average(listOfValues);
        for(int i=0; i<size; i++){
            squaredSum+=Math.pow(listOfValues.get(i)-avg,2);
        }
        return Math.sqrt(squaredSum/size);
    }
}
