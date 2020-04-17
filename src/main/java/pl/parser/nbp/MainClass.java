package pl.parser.nbp;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class MainClass {


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, ParseException {
        boolean flag = true;
	    while(flag){
            Scanner scanner = new Scanner(System.in);
            DateValidator dateValidator = new DateValidator(Constants.DATE_FORMAT);
            Set<String> validCurrencies = new HashSet<>();
            validCurrencies.add("EUR");
            validCurrencies.add("GBP");
            validCurrencies.add("USD");
            validCurrencies.add("CHF");
            CurrencyValidator currencyValidator = new CurrencyValidator(validCurrencies);
            System.out.println(Constants.PROVIDE_CURRENCY);
            System.out.println(Constants.CHOOSE_CURRENCY);
            String currency = scanner.next().toUpperCase();
            System.out.println(Constants.CHOSEN_CURRENCY + currency);

            if(!currencyValidator.isValid(currency)) {
                System.out.println(Constants.INCORRECT_CURRENCY);
                continue;
            }
            scanner.nextLine();
            System.out.println(Constants.INPUT_START_DATE);
            String startDate = scanner.next();
            System.out.println(Constants.CHOSEN_START_DATE + startDate);
            if(!(dateValidator.isValid(startDate) && (!dateValidator.isDateLater(Constants.LOWER_DATE_LIMIT,startDate))&& !dateValidator.isDateInFuture(startDate))) {
                System.out.println(Constants.INCORRECT_START_DATE);
                continue;
            }
            scanner.nextLine();
            System.out.println(Constants.INPUT_END_DATE);
            String endDate = scanner.next();
            System.out.println(Constants.CHOSEN_END_DATE + endDate);
            if(!(dateValidator.isValid(endDate) && (!dateValidator.isDateLater(startDate,endDate)) && !dateValidator.isDateInFuture(endDate))) {
                System.out.println(Constants.INCORRECT_END_DATE);
                continue;
            }
            DataAccess dataAccess = new DataAccess(currency,startDate,endDate);
            Map<String,String> data = dataAccess.getData();
            if(isNaN(data)){
                System.out.println(Constants.NO_DATA);
            }else{
                System.out.println(data.toString());
            }
	    }
    }

    public static boolean isNaN(Map<String,String> data){
        return data.get(Constants.AVG_BUY).equals(Constants.NAN);
    }

}
