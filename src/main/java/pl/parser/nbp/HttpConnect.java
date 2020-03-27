package com.example;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnect {
    private static HttpConnect httpConnect = null;
    private final String url;


    private HttpConnect(String url){
        this.url = url;
    }

    public static HttpConnect getHttpConnect(String url){
        if(httpConnect==null)
            httpConnect = new HttpConnect(url);
        return httpConnect;
    }


    public HttpURLConnection createRequest(String param){
        try{
            URL url = new URL(param);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            return con;
        }catch(Exception ex){

        }
        return null;
    }

}
