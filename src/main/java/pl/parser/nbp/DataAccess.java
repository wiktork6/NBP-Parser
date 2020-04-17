package pl.parser.nbp;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataAccess {
    private final String currency;
    private final String start_date;
    private final String end_date;
    private static final String FORMAT = "yyyy-MM-dd";

    public DataAccess(String currency, String start_date, String end_date) {
        this.currency = currency;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Map<String,String> getData() throws IOException{
        DateBuilder dateBuilder = new DateBuilder();
        List<String> dates = dateBuilder.getDates(this.start_date, this.end_date);
        Map<String,String> data = new HashMap<>();
        List<Double> avgBuyList = new LinkedList<>();
        List<Double> avgSellList = new LinkedList<>();
        LinkBuilder linkBuilder = new LinkBuilder();


        List<String> links = linkBuilder.getLinksToDocuments(this.start_date, this.end_date);
        DateValidator dateValidator = new DateValidator(FORMAT);
        XMLParserJaxB xmlParserJaxB = new XMLParserJaxB();

        for(String link: links) {
            URL url = new URL(link);
            CurrencyDocument currencyDocument = xmlParserJaxB.getCurrencyDocument(url);
            String publicationDate = currencyDocument.getPublicationDate();
            if(dateValidator.isDateInRange(this.start_date, this.end_date, publicationDate)){
                List<Position> positions = currencyDocument.getPositions();
                Double avgBuyRate = null;
                Double avgSellRate = null;

                for(Position position : positions){
                    if(position.getCurrencyTag().equals(currency)){
                        String avgBuy = position.getBuyRate();
                        avgBuy = avgBuy.replace(',','.');
                        avgBuyRate = Double.valueOf(avgBuy);
                        String avgSell = position.getSalesRate();
                        avgSell = avgSell.replace(',','.');
                        avgSellRate = Double.valueOf(avgSell);
                        break;
                    }
                }
                avgBuyList.add(avgBuyRate);
                avgSellList.add(avgSellRate);
                if(avgBuyList.size()==dates.size()){
                    break;
                }
            }
        }
        DataCounter dataCounter = new DataCounter();
        Double avgBuyPeriod = dataCounter.average(avgBuyList);
        Double standardDeviationOfSellRates = dataCounter.standardDeviation(avgSellList);

        data.put("Currency: ", this.currency);
        data.put("Start Date: ", this.start_date);
        data.put("End Date: ", this.end_date);
        data.put("Avg Buy: ", avgBuyPeriod.toString());
        data.put("Standard Deviation of sell rates: ", standardDeviationOfSellRates.toString());

        return data;
    }

}
