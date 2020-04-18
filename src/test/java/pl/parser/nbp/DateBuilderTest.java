package pl.parser.nbp;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateBuilderTest {
    private DateBuilder SUT = new DateBuilder();

    @Test
    public void testValidPairOfDates_getDates_returnsExpectedListOfDates() throws Exception{
        //given
        String startDate = "2013-12-30";
        String endDate = "2014-01-02";

        List<String> dates = List.of("2013-12-30","2013-12-31","2014-01-01","2014-01-02");
        //when
        var result = SUT.getDates(startDate,endDate);
        //then
        assertEquals(dates,result);
    }

    @Test
    public void testInvalidPairOfDates_getDates_returnsEmptyList() throws Exception{
        //given
        String startDate = "2013-01-28";
        String endDate = "2013-01-27";

        List<String> dates = new LinkedList();
        //when
        var result = SUT.getDates(startDate,endDate);
        //then
        assertEquals(dates,result);
    }

    @Test
    public void testInvalidFormatOfDates_getDates_returnsEmptyList() throws Exception{
        //given
        String startDate = "Not a date";
        String endDate = "Not a date too";

        List<String> dates = new LinkedList();
        //when
        var result = SUT.getDates(startDate,endDate);
        //then
        assertEquals(dates,result);
    }

    @Test
    public void testSameDates_getDates_returnsLengthOneList() throws Exception{
        //given
        String startDate = "2013-12-28";
        String endDate = "2013-12-28";

        List<String> dates = List.of("2013-12-28");
        //when
        var result = SUT.getDates(startDate,endDate);
        //then
        assertEquals(dates,result);
    }

    @Test
    public void testFebruaryDatesInLeapYear_getDates_returnsCorrectList() throws Exception{
        //given
        String startDate = "2016-02-27";
        String endDate = "2016-03-01";

        List<String> dates = List.of("2016-02-27","2016-02-28","2016-02-29","2016-03-01");
        //when
        var result = SUT.getDates(startDate,endDate);
        //then
        assertEquals(dates,result);
    }
    @Test
    public void testFebruaryDatesInNotLeapYear_getDates_returnsCorrectList() throws Exception{
        //given
        String startDate = "2015-02-27";
        String endDate = "2015-03-01";

        List<String> dates = List.of("2015-02-27","2015-02-28","2015-03-01");
        //when
        var result = SUT.getDates(startDate,endDate);
        //then
        assertEquals(dates,result);
    }
}
