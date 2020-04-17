package pl.parser.nbp;

import java.util.List;

public class DataCounter {

    public DataCounter() {
    }

    public double average(List<Double> listOfValues){
        return listOfValues.stream().reduce(0.0,Double::sum)/listOfValues.size();
    }

    public double standardDeviation(List<Double> listOfValues){
        double avg = average(listOfValues);
        return Math.sqrt(listOfValues.stream().reduce(0.0,(a,b)-> a + Math.pow(b-avg,2))/listOfValues.size());
    }
}
