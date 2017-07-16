package workdays;

import java.util.Comparator;

public class WeeklyEntry{

    public static final Comparator<WeeklyEntry> WEEKLY_ENTRY_COMPARATOR = getComparator();

    private Weekday weekday;
    private String notes;

    public WeeklyEntry(String weekday, String notes) {
        this.weekday = Enum.valueOf(Weekday.class, weekday.toUpperCase());
        this.notes = notes;
    }

    @Override
    public String toString() {
        return this.weekday + " - " + this.notes;
    }

    private static Comparator<WeeklyEntry> getComparator() {
        return Comparator.comparing(e -> e.weekday);
    }
}
