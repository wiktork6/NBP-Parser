package pl.parser.nbp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pozycja")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position {
    @XmlElement(name = "nazwa_waluty")
    private String currencyName;
    @XmlElement(name = "przelicznik")
    private String converter;
    @XmlElement(name = "kod_waluty")
    private String currencyTag;
    @XmlElement(name = "kurs_kupna")
    private String buyRate;
    @XmlElement(name = "kurs_sprzedazy")
    private String salesRate;

    public Position(){
        super();
    }
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getConverter() {
        return converter;
    }

    public void setConverter(String converter) {
        this.converter = converter;
    }

    public String getCurrencyTag() {
        return currencyTag;
    }

    public void setCurrencyTag(String currencyTag) {
        this.currencyTag = currencyTag;
    }

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }

    public String getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(String salesRate) {
        this.salesRate = salesRate;
    }

    @Override
    public String toString() {
        return "Position{" +
                "currencyName='" + currencyName + '\'' +
                ", converter='" + converter + '\'' +
                ", currencyTag='" + currencyTag + '\'' +
                ", buyRate='" + buyRate + '\'' +
                ", salesRate='" + salesRate + '\'' +
                '}';
    }
}
