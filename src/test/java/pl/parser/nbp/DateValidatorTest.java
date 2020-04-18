package pl.parser.nbp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateValidatorTest {
    private DateValidator SUT = new DateValidator("yyyy-MM-dd");

    @Test
    public void testInvalidDateFormatIsValidReturnsFalse() throws Exception{
        //given
        String date = "2013/03/03";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(false,result);
    }

    @Test
    public void testValidDateFormatIsValidReturnsTrue() throws Exception{
        //given
        String date = "2013-03-03";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(true,result);
    }

    @Test
    public void testInvalidMonthDateIsValidReturnsFalse() throws Exception{
        //given
        String date = "2013-13-03";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(false,result);
    }

    @Test
    public void testInvalidDayDateIsValidReturnsFalse() throws Exception{
        //given
        String date = "2013-02-29";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(false,result);
    }

    @Test
    public void testValidDateIsValidReturnsTrue() throws Exception{
        //given
        String date = "2013-02-28";
        //when
        var result = SUT.isValid(date);
        //then
        assertEquals(true,result);
    }



}
