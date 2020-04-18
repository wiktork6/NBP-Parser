package pl.parser.nbp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateValidatorTest {
    private DateValidator SUT = new DateValidator("yyyy-MM-dd");

    @Test
    public void testInvalidDateFormat_isValid_returnsFalse() throws Exception{
        //given
        String date = "2013/03/03";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(false,result);
    }

    @Test
    public void testValidDateFormat_isValid_returnsTrue() throws Exception{
        //given
        String date = "2013-03-03";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(true,result);
    }

    @Test
    public void testInvalidMonthDate_isValid_returnsFalse() throws Exception{
        //given
        String date = "2013-13-03";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(false,result);
    }

    @Test
    public void testInvalidDayDate_isValid_returnsFalse() throws Exception{
        //given
        String date = "2013-02-29";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(false,result);
    }

    @Test
    public void testValidDate_isValid_returnsTrue() throws Exception{
        //given
        String date = "2013-02-28";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(true,result);
    }



}
