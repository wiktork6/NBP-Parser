package pl.parser.nbp;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class MainClass {
    public static final String LOWER_DATE_LIMIT = "2002-01-02";
    public static final String PROVIDE_CURRENCY = "Please provide currency";
    public static final String CHOSE_CURRENCY = "Choose from \"USD\", \"EUR\", \"CHF\", \"GBP\"";
    public static final String CHOSEN_CURRENCY = "Chosen currency ";
    public static final String INPUT_START_DATE = "Please input start date in yyyy-MM-dd format";
    public static final String CHOSEN_START_DATE = "Chosen start date ";
    public static final String INPUT_END_DATE = "Please input end date in yyyy-MM-dd format";
    public static final String CHOSEN_END_DATE = "Chosen end date: ";
    public static final String AVG_BUY = "Avg Buy: ";
    public static final String NAN = "NaN";
    public static final String NO_DATA = "No data for provided period";
    public static final String INCORRECT_END_DATE = "Incorrect end date";
    public static final String INCORRECT_START_DATE = "Incorrect start date";
    public static final String INCORRECT_CURRENCY = "Incorrect currency";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, ParseException {
        boolean flag = true;
        String LOWER_DATE_LIMIT = "2002-01-02";
	    while(flag){
            Scanner scanner = new Scanner(System.in);
            DateValidator dateValidator = new DateValidator(DATE_FORMAT);
            Set<String> validCurrencies = new HashSet<>();
            validCurrencies.add("EUR");
            validCurrencies.add("GBP");
            validCurrencies.add("USD");
            validCurrencies.add("CHF");
            CurrencyValidator currencyValidator = new CurrencyValidator(validCurrencies);
            System.out.println("Please provide currency");
            System.out.println("Choose from \"USD\", \"EUR\", \"CHF\", \"GBP\"");
            String currency = scanner.next().toUpperCase();
            System.out.println("Chosen currency " + currency);

            if(!currencyValidator.isValid(currency)) {
                System.out.println("Incorrect currency");
                continue;
            }
            scanner.nextLine();
            System.out.println("Please input start date in yyyy-MM-dd format");
            String startDate = scanner.next();
            System.out.println("Chosen start date " + startDate);
            if(!(dateValidator.isValid(startDate) && (!dateValidator.isDateLater(LOWER_DATE_LIMIT,startDate))&& !dateValidator.isDateInFuture(startDate))) {
                System.out.println("Incorrect start date");
                continue;
            }
            scanner.nextLine();
            System.out.println("Please input end date in yyyy-MM-dd format");
            String endDate = scanner.next();
            System.out.println("Chosen end date: " + endDate);
            if(!(dateValidator.isValid(endDate) && (!dateValidator.isDateLater(startDate,endDate)) && !dateValidator.isDateInFuture(endDate))) {
                System.out.println("Incorrect end date");
                continue;
            }
            DataAccess dataAccess = new DataAccess(currency,startDate,endDate);
            Map<String,String> data = dataAccess.getData();
            if(isNaN(data)){
                System.out.println("No data for provided period");
            }else{
                System.out.println(data.toString());
            }
	    }
    }

    public static boolean isNaN(Map<String,String> data){
        return data.get("Avg Buy: ").equals("NaN");
    }

}
