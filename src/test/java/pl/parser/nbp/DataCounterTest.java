package pl.parser.nbp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataCounterTest {
    private DataCounter CUT = new DataCounter();
    @Test
    public void test_empty_list_average_returns_NaN() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        //when
        var result = CUT.average(numbers);
        //then
        assertEquals(Double.valueOf("NaN"),result);
    }

    @Test
    public void test_length_one_list_average_returns_number() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        //when
        var result = CUT.average(numbers);
        //then
        assertEquals(5.4,result);
    }

    @Test
    public void test_list_with_multiple_values_average_returns_correct_value() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        numbers.add(1.1);
        numbers.add(3.2);
        numbers.add(11.3);
        numbers.add(5.5);
        //when
        var result = CUT.average(numbers);
        //then
        assertEquals(5.3,result);
    }

    @Test
    public void test_empty_list_standardDeviation_returns_NaN() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        //when
        var result = CUT.standardDeviation(numbers);
        //then
        assertEquals(Double.valueOf("NaN"),result);
    }
    @Test
    public void test_length_one_list_standardDeviation_returns_zero() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        //when
        var result = CUT.standardDeviation(numbers);
        //then
        assertEquals(0,result);
    }
    @Test
    public void test_list_with_multiple_values_standardDeviation_returns_correct_value() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        numbers.add(1.1);
        numbers.add(3.2);
        numbers.add(11.3);
        numbers.add(5.5);
        //when
        var result = CUT.standardDeviation(numbers);
        //then
        assertEquals(3.4088,result);
    }
}
