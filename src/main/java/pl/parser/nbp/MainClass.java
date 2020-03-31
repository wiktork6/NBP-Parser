package pl.parser.nbp;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

public class MainClass {


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, ParseException {
        boolean flag = true;
        String LOWER_DATE_LIMIT = "2002-01-02";
	    while(flag){
            Scanner scanner = new Scanner(System.in);
            DataValidator dataValidator = new DataValidator();
            System.out.println("Please provide currency");
            System.out.println("Choose from \"USD\", \"EUR\", \"CHF\", \"GBP\"");
            String currency = scanner.next().toUpperCase();
            System.out.println("Chosen currency " + currency);
            if(dataValidator.validateCurrency(currency)){
                scanner.nextLine();
                System.out.println("Please input start date in yyyy-MM-dd format");
                String startDate = scanner.next();
                System.out.println("Chosen start date " + startDate);
                if(dataValidator.isDateValid(startDate) && (!dataValidator.isDateLater(LOWER_DATE_LIMIT,startDate))){
                    scanner.nextLine();
                    System.out.println("Please input end date in yyyy-MM-dd format");
                    String endDate = scanner.next();
                    System.out.println("Chosen end date: " + endDate);
                    if(dataValidator.isDateValid(endDate) && (!dataValidator.isDateLater(startDate,endDate)) && !dataValidator.isDateInFuture(endDate)){
                        DataAccess dataAccess = new DataAccess(currency,startDate,endDate);
                        Map<String,String> data = dataAccess.getData();
                        if(data.get("Avg Buy: ").equals("NaN")){
                            System.out.println("No data for provided period");
                        }else{
                            System.out.println(data.toString());
                        }
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
