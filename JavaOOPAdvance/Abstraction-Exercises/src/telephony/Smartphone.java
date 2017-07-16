package telephony;

import java.util.regex.Pattern;

public class Smartphone implements Browsable, Callable {
    @Override
    public String call(String number) {
        boolean isValid = Pattern.compile("\\D").matcher(number).find();

        if(isValid) {
            return "Invalid number!";
        }
        return String.format("Calling... %s", number);
    }

    @Override
    public String browseWeb(String address) {
        boolean isValid = Pattern.compile("\\d").matcher(address).find();

        if(isValid) {
            return "Invalid URL!";
        }

        return String.format("Browsing: %s!", address);
    }
}
