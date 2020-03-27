package com.example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataAccess {
    private final String CURRENCY;
    private final String START_DATE;
    private final String END_DATE;
    private final String NBP_URL;
    private static final String NBP_URL_C = "http://www.nbp.pl/kursy/xml/dir.aspx?tt=C";
    public static final String BUY_RATE_TAG = "kurs_kupna";
    public static final String SELL_RATE_TAG = "kurs_sprzedazy";

    public DataAccess(String CURRENCY, String START_DATE, String END_DATE, String NBP_URL) {
        this.CURRENCY = CURRENCY;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.NBP_URL = NBP_URL;
    }

    public Map<String,String> getData() throws ParserConfigurationException, SAXException, IOException {
        HttpConnect httpConnect = HttpConnect.getHttpConnect(NBP_URL);
        ParamBuilder paramBuilder = new ParamBuilder();
        List<String> listOfParam = paramBuilder.getParameters(this.START_DATE, this.END_DATE);
        Map<String,String> data = new HashMap<>();
        List<Double> avgBuyList = new LinkedList<>();
        List<Double> avgSellList = new LinkedList<>();
        LinkScraper linkScraper = new LinkScraper();
        List<String> links = linkScraper.findLinks(NBP_URL_C);
        DataValidator dataValidator = new DataValidator();
        for(String link: links) {
            HttpURLConnection con = httpConnect.createRequest(link);
            XmlParser xmlParser = new XmlParser(con);
            String publicationDate = xmlParser.getPublicationDate();
            if(dataValidator.isDateInRange(this.START_DATE, this.END_DATE, publicationDate)){
                con = httpConnect.createRequest(link);
                xmlParser = new XmlParser(con);
                Double avgBuyDay = xmlParser.getPriceValue(this.CURRENCY, BUY_RATE_TAG);
                con = httpConnect.createRequest(link);
                xmlParser = new XmlParser(con);
                Double avgSellDay = xmlParser.getPriceValue(this.CURRENCY, SELL_RATE_TAG);
                avgBuyList.add(avgBuyDay);
                avgSellList.add(avgSellDay);
                if(avgBuyList.size()==listOfParam.size()){
                    break;
                }
            }
        }
        DataCounter dataCounter = new DataCounter();
        Double avgBuyPeriod = dataCounter.average(avgBuyList);
        Double standardDeviationOfSellRates = dataCounter.standardDeviation(avgSellList);

        data.put("Currency: ", this.CURRENCY);
        data.put("Start Date: ", this.START_DATE);
        data.put("End Date: ", this.END_DATE);
        data.put("Avg Buy: ", avgBuyPeriod.toString());
        data.put("Standard Deviation of sell rates: ", standardDeviationOfSellRates.toString());

        return data;
    }

}
