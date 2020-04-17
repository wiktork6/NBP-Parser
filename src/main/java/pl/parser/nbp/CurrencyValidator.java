package pl.parser.nbp;

import java.util.Set;

public class CurrencyValidator implements Validator{
    private Set<String> validCurrencies;
    public CurrencyValidator(Set<String> validCurrencies) {
        this.validCurrencies = validCurrencies;
    }

    public boolean isValid(String currency){
        return validCurrencies.contains(currency);

    }
}
