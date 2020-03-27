package com.example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class MainClass {
    private static final String NBP_URL = "http://www.nbp.pl/kursy/xml/";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        boolean flag = true;
	    while(flag){
            Scanner scanner = new Scanner(System.in);
            DataValidator dataValidator = new DataValidator();
            System.out.println("Please provide currency");
            System.out.println("Choose from \"USD\", \"EUR\", \"CHF\", \"GBP\"");
            String currency = scanner.next().toUpperCase();
            System.out.println("Chosen currency " + currency);
            if(dataValidator.validateCurrency(currency)){
                scanner.nextLine();
                System.out.println("Please input start date");
                String startDate = scanner.next();
                System.out.println("Chosen start date " + startDate);
                if(dataValidator.isDateValid(startDate)){
                    scanner.nextLine();
                    System.out.println("Please input end date");
                    String endDate = scanner.next();
                    System.out.println("Chosen end date: " + endDate);
                    if(dataValidator.isDateValid(endDate) && dataValidator.isDateRangeValid(startDate,endDate)){
                        DataAccess dataAccess = new DataAccess(currency,startDate,endDate,NBP_URL);
                        Map<String,String> data = dataAccess.getData();
                        System.out.println(data.toString());
                    }else{
                        System.out.println("Incorrect end date");
                    }
                }else{
                    System.out.println("Incorrect start date");
                }
            }else{
                System.out.println("Incorrect currency");
            }
        }

    }

}
