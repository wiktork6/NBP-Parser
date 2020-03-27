package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LinkScraper {

    public LinkScraper() {
    }

    public List<String> findLinks(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Elements linksOnPage = doc.select("a[href]");
            List<String> links = new LinkedList<>();
            for(Element element : linksOnPage){
                String urlText = element.attr("abs:href");
                links.add(urlText);
            }
            return links;
        } catch(IOException e){
            return null;
        }
    }
}
