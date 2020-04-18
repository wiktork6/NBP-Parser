package pl.parser.nbp;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataAccessTest {
    @Test
    public void testRangeOfDatesFromSameYearGetDataReturnsCorrectValues() throws Exception{
        //given
        String currency = "EUR";
        String startDate = "2013-01-28";
        String endDate= "2013-01-31";
        DataAccess SUT = new DataAccess(currency,startDate,endDate);

        Map<String,String> correctData = new HashMap<>(Map.of("Currency: ",currency,"Start Date: ",startDate,"End Date: ",endDate,"Avg Buy: ","4.1505","Standard Deviation of sell rates: ","0.0125"));
        //when
        var result = SUT.getData();
        //then
        assertEquals(correctData,result);
    }
    @Test
    public void testRangeOfDatesFromDifferentYearsGetDataReturnsCorrectValues() throws Exception{
        //given
        String currency = "USD";
        String startDate = "2013-12-31";
        String endDate= "2014-01-03";
        DataAccess SUT = new DataAccess(currency,startDate,endDate);

        Map<String,String> correctData = new HashMap<>(Map.of("Currency: ",currency,"Start Date: ",startDate,"End Date: ",endDate,"Avg Buy: ","2.9941","Standard Deviation of sell rates: ","0.0221"));
        //when
        var result = SUT.getData();
        //then
        assertEquals(correctData,result);
    }
    @Test
    public void testStartDateAndEndDateAreSameGetDataReturnsCorrectValues() throws Exception{
        //given
        String currency = "CHF";
        String startDate = "2013-01-02";
        String endDate= "2013-01-02";
        DataAccess SUT = new DataAccess(currency,startDate,endDate);

        Map<String,String> correctData = new HashMap<>(Map.of("Currency: ",currency,"Start Date: ",startDate,"End Date: ",endDate,"Avg Buy: ","3.3529","Standard Deviation of sell rates: ","0.0"));
        //when
        var result = SUT.getData();
        //then
        assertEquals(correctData,result);
    }
    @Test
    public void testDateRangeWithNoDataGetDataReturnsNaN() throws Exception{
        //given
        String currency = "EUR";
        String startDate = "2013-01-01";
        String endDate= "2013-01-01";
        DataAccess SUT = new DataAccess(currency,startDate,endDate);

        Map<String,String> correctData = new HashMap<>(Map.of("Currency: ",currency,"Start Date: ",startDate,"End Date: ",endDate,"Avg Buy: ","NaN","Standard Deviation of sell rates: ","NaN"));
        //when
        var result = SUT.getData();
        //then
        assertEquals(correctData,result);
    }
}
