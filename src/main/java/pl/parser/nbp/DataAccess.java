package pl.parser.nbp;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataAccess {
    private final String currency;
    private final String start_date;
    private final String end_date;
    public static final String BUY_RATE_TAG = "kurs_kupna";
    public static final String SELL_RATE_TAG = "kurs_sprzedazy";

    public DataAccess(String currency, String start_date, String end_date) {
        this.currency = currency;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Map<String,String> getData() throws ParserConfigurationException, SAXException, IOException, ParseException {
        HttpConnect httpConnect = HttpConnect.getHttpConnect();
        DateBuilder dateBuilder = new DateBuilder();
        List<String> dates = dateBuilder.getDates(this.start_date, this.end_date);
        Map<String,String> data = new HashMap<>();
        List<Double> avgBuyList = new LinkedList<>();
        List<Double> avgSellList = new LinkedList<>();
        LinkBuilder linkBuilder = new LinkBuilder();


        List<String> links = linkBuilder.getLinksToDocuments(this.start_date, this.end_date);
        DataValidator dataValidator = new DataValidator();
        for(String link: links) {
            HttpURLConnection con = httpConnect.createRequest(link);
            XmlParser xmlParser = new XmlParser(con);
            String publicationDate = xmlParser.getPublicationDate();
            if(dataValidator.isDateInRange(this.start_date, this.end_date, publicationDate)){
                con = httpConnect.createRequest(link);
                xmlParser = new XmlParser(con);
                Double avgBuyDay = xmlParser.getPriceValue(this.currency, BUY_RATE_TAG);
                con = httpConnect.createRequest(link);
                xmlParser = new XmlParser(con);
                Double avgSellDay = xmlParser.getPriceValue(this.currency, SELL_RATE_TAG);
                avgBuyList.add(avgBuyDay);
                avgSellList.add(avgSellDay);
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
