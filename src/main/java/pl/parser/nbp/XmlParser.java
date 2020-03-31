package pl.parser.nbp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;

public class XmlParser {
    private HttpURLConnection conn;
    public static final String POSITION_TAG = "pozycja";
    public static final String PUBLICATION_DATE_TAG = "data_publikacji";
    public static final String CURRENCY_TAG = "kod_waluty";


    public XmlParser(HttpURLConnection conn) {
        this.conn = conn;
    }

    private NodeList getListOfPositions() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(POSITION_TAG);
        return nList;
    }

    public Double getPriceValue(String currency, String tagName) throws IOException, SAXException, ParserConfigurationException {
        NodeList nList = getListOfPositions();
        Double price = 0.0;
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element eElement = (Element) nNode;
            if(eElement.getElementsByTagName(CURRENCY_TAG).item(0).getTextContent().equals(currency)){
                DataValidator dataValidator = new DataValidator();
                String buyRate = eElement.getElementsByTagName(tagName).item(0).getTextContent();
                String numberBuyRate = dataValidator.replaceChar(buyRate,',', '.');
                price = Double.valueOf(numberBuyRate);
                break;
            }
        }
        return price;
    }

    public String getPublicationDate() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(PUBLICATION_DATE_TAG).item(0).getTextContent();
    }

}
