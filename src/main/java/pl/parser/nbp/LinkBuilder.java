package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class LinkBuilder {
    public static final String URL = "https://www.nbp.pl/kursy/xml/";
    public static final String CATEGORY = "c";
    public static final String DIR = "dir";
    public static final String TXT = ".txt";
    public static final String XML = ".xml";

    public LinkBuilder() {
    }

    public List<String> getLinksToDocuments(String startDate, String endDate) {

        List<String> params = getAllYearsParams(startDate,endDate);
        List<String> linksToListOfDocuments = new LinkedList<>();
        for(int i=0; i<params.size(); i++){
            linksToListOfDocuments.add(URL+params.get(i));
        }

        List<String> linksToDocuments = new LinkedList<>();
        for(String link : linksToListOfDocuments){
            List<String> categorisedParams = getCategorisedParams(link, CATEGORY);
            for(int i=0; i<categorisedParams.size(); i++){
                linksToDocuments.add(URL+categorisedParams.get(i)+ XML);
            }
        }

        return linksToDocuments;
    }

    private List<String> getParams(String url)  {
        try{
            URL url2 = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(url2.openStream()));
            List<String> params = new LinkedList<>();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                params.add(inputLine);
            in.close();

            return params;
        } catch(IOException e){
            return null;
        }
    }

    private List<String> getCategorisedParams(String url, String category){
        List<String> allDocuments = getParams(url);
        List<String> categorisedDocuments = new LinkedList<>();
        for(int i =0; i<allDocuments.size(); i++){
            if(allDocuments.get(i).startsWith(category)){
                categorisedDocuments.add(allDocuments.get(i));
            }
        }
        return categorisedDocuments;
    }

    private List<String> getAllYearsParams(String startDate, String endDate){
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<String> yearsParams = new LinkedList<>();
        List<Integer> years = new LinkedList<>();
        int startYear = start.getYear();
        int endYear = end.getYear();
        for (int i = startYear; i <= endYear; i++) {
                years.add(i);
            }
        int yearNow = LocalDate.now().getYear();
        for(int i=0; i<years.size(); i++){
            yearsParams.add(DIR + (years.get(i)==yearNow ? "" : years.get(i).toString()) + TXT);
        }

        return yearsParams;
    }
}
