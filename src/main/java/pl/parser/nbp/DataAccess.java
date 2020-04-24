package pl.parser.nbp;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataAccess {
    private final String currency;
    private final String startDate;
    private final String endDate;

    public DataAccess(String currency, String startDate, String endDate) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Map<String,String> getData() throws IOException{
        DateBuilder dateBuilder = new DateBuilder();
        List<String> dates = dateBuilder.getDates(this.startDate, this.endDate);
        Map<String,String> data = new HashMap<>();
        List<Double> avgBuyList = new LinkedList<>();
        List<Double> avgSellList = new LinkedList<>();
        LinkBuilder linkBuilder = new LinkBuilder();


        List<String> links = linkBuilder.getLinksToDocuments(this.startDate, this.endDate);
        DateValidator dateValidator = new DateValidator(Constants.DATE_FORMAT);
        XMLParserJaxB xmlParserJaxB = new XMLParserJaxB();

        for(String link: links) {
            URL url = new URL(link);
            CurrencyDocument currencyDocument = xmlParserJaxB.getCurrencyDocument(url);
            String publicationDate = currencyDocument.getPublicationDate();
            if(dateValidator.isDateInRange(this.startDate, this.endDate, publicationDate)){
                List<Position> positions = currencyDocument.getPositions();
                Double avgBuyRate = null;
                Double avgSellRate = null;

                for(Position position : positions){
                    if(position.getCurrencyTag().equals(currency)){
                        String avgBuy = position.getBuyRate();
                        if(!avgBuy.equals("") && avgBuy!=null){
                            avgBuyRate = parseDouble(avgBuy);
                        }
                        String avgSell = position.getSalesRate();
                        if(!avgSell.equals("") && avgSell!=null){
                            avgSellRate = parseDouble(avgSell);
                        }
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

        data.put(Constants.CURRENCY_KEY, this.currency);
        data.put(Constants.START_DATE_KEY, this.startDate);
        data.put(Constants.END_DATE_KEY, this.endDate);
        data.put(Constants.AVG_BUY, avgBuyPeriod.toString());
        data.put(Constants.STANDARD_DEVIATION_KEY, standardDeviationOfSellRates.toString());

        return data;
    }

    private Double parseDouble(String number){
        return Double.valueOf(number.replace(',','.'));
    }

}
