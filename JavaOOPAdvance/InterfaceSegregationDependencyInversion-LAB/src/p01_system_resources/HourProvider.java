package p01_system_resources;

import java.time.LocalTime;

public class HourProvider implements HourProvidable {

    private LocalTime localTime;

    public HourProvider() {
        this.localTime = LocalTime.now();
    }

    @Override
    public int getHour() {
        return localTime.getHour();
    }
}
