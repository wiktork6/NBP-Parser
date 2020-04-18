package pl.parser.nbp;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyValidatorTest {
    private CurrencyValidator SUT = new CurrencyValidator(new HashSet<>(Set.of("EUR","GBP","CHF","USD")));
    @Test
    public void testValidCurrencyIsValidReturnsTrue() throws Exception{
        //given
        String currencyEur = "EUR";
        String currencyUsd = "USD";
        String currencyGbp = "GBP";
        String currencyChf = "CHF";
        //when
        var resultEur = SUT.isValid(currencyEur);
        var resultUsd = SUT.isValid(currencyUsd);
        var resultGbp = SUT.isValid(currencyGbp);
        var resultChf = SUT.isValid(currencyChf);
        //then
        assertEquals(true,resultEur);
        assertEquals(true,resultUsd);
        assertEquals(true,resultGbp);
        assertEquals(true,resultChf);

    }
    @Test
    public void testInvalidCurrencyisValidReturnsFalse() throws Exception{
        //given
        String currency = "PLN";
        //when
        var result = SUT.isValid(currency);
        //then
        assertEquals(false,result);
    }
}
