package pl.parser.nbp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

public class XMLParserJaxB {


    public CurrencyDocument getCurrencyDocument(URL url){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyDocument.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (CurrencyDocument) jaxbUnmarshaller.unmarshal(url);
        } catch (JAXBException e){
            e.printStackTrace();
            return null;
        }

    }


}
