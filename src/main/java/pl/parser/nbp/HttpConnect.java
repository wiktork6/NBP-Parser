package pl.parser.nbp;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnect {
    private static HttpConnect httpConnect = null;


    private HttpConnect(){
    }

    public static HttpConnect getHttpConnect(){
        if(httpConnect==null)
            httpConnect = new HttpConnect();
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
