package pl.parser.nbp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyDocument implements Serializable {

    private static final Long serialVersionUid= 1L;
    @XmlElement(name="numer_tabeli")
    private String tableNumber;
    @XmlElement(name="data_notowania")
    private String recordDate;
    @XmlElement(name="data_publikacji")
    private String publicationDate;
    @XmlElement(name="pozycja")
    private List<Position> positions;

    public CurrencyDocument(){
        super();
    }

    public static Long getSerialVersionUid() {
        return serialVersionUid;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "CurrencyDocument{" +
                "tableNumber='" + tableNumber + '\'' +
                ", recordDate='" + recordDate + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", positions=" + positions +
                '}';
    }
}
