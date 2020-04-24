package pl.parser.nbp;

import java.text.DecimalFormat;
import java.util.List;

public class DataCounter {

    public Double average(List<Double> listOfValues){
        return roundUp(listOfValues.stream().reduce(0.0,Double::sum)/listOfValues.size());
    }

    public Double standardDeviation(List<Double> listOfValues){
        Double avg = average(listOfValues);
        return roundUp(Math.sqrt(listOfValues.stream().reduce(0.0,(a,b)-> a + Math.pow(b-avg,2))/listOfValues.size()));
    }
    private Double roundUp(Double number){
        DecimalFormat df = new DecimalFormat("###.####");
        return Double.valueOf(df.format(number));
    }
}
