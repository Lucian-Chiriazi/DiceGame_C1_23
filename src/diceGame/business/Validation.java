package diceGame.business;

import java.util.regex.Pattern;

public class Validation {

    public boolean validation1 (String input) {
        String regex = "^[01]$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    public boolean validation2 (String input) {
        String regex = "^[t]$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }
}
