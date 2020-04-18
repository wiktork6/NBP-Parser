package pl.parser.nbp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataCounterTest {
    private DataCounter SUT = new DataCounter();
    @Test
    public void testEmptyListAverageReturnsNaN() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        //when
        var result = SUT.average(numbers);
        //then
        assertEquals(Double.valueOf("NaN"),result);
    }

    @Test
    public void testLengthOneListAverageReturnsCorrectValue() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        //when
        var result = SUT.average(numbers);
        //then
        assertEquals(5.4,result);
    }

    @Test
    public void testListWithMultiplePositiveValuesAverageReturnsCorrectValue() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        numbers.add(1.1);
        numbers.add(3.2);
        numbers.add(11.3);
        numbers.add(5.5);
        //when
        var result = SUT.average(numbers);
        //then
        assertEquals(5.3,result);
    }

    @Test
    public void testListWithMultipleNegativeValuesAverageReturnsCorrectValue() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(-3.1);
        numbers.add(-1.4);
        numbers.add(-5.5);
        numbers.add(-12.3);
        numbers.add(-0.8);
        //when
        var result = SUT.average(numbers);
        //then
        assertEquals(-4.6200,result);
    }

    @Test
    public void testListWithMultiplePositiveAndNegativeValuesAverageReturnsCorrectValue() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        numbers.add(1.1);
        numbers.add(3.2);
        numbers.add(11.3);
        numbers.add(5.5);
        numbers.add(-3.1);
        numbers.add(-1.4);
        numbers.add(-5.5);
        numbers.add(-12.3);
        numbers.add(-0.8);
        numbers.add(0.0);
        //when
        var result = SUT.average(numbers);
        //then
        assertEquals(0.3091,result);
    }

    @Test
    public void testEmptyListStandardDeviationReturnsNaN() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        //when
        var result = SUT.standardDeviation(numbers);
        //then
        assertEquals(Double.valueOf("NaN"),result);
    }
    @Test
    public void testLengthOneListStandardDeviationReturnsZero() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        //when
        var result = SUT.standardDeviation(numbers);
        //then
        assertEquals(0,result);
    }
    @Test
    public void testListWithMultipleValuesStandardDeviationReturnsCorrectValue() throws Exception{
        //given
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.4);
        numbers.add(1.1);
        numbers.add(3.2);
        numbers.add(11.3);
        numbers.add(5.5);
        //when
        var result = SUT.standardDeviation(numbers);
        //then
        assertEquals(3.4088,result);
    }
}
