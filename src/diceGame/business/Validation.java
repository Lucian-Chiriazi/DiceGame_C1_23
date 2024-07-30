package diceGame.business;

import java.util.ArrayList;
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

    public boolean validation3 (String input, ArrayList<Integer> currentThrow, ArrayList<Integer> currentDiceKept) {
        String regex = "^[16]$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(input).matches()) {
            if (validation4(input, currentThrow)) {
                if (validation4(input, currentDiceKept)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean validation4 (String input, ArrayList<Integer> list) {
        if (list.contains(Integer.parseInt(input))) {
            return true;
        }
        return false;
    }
}
