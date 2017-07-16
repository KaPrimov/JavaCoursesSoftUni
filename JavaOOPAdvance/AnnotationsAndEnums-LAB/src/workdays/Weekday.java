package workdays;

public enum Weekday {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    @Override
    public String toString() {
        String lowerLetters = super.name().substring(1).toLowerCase();

        return super.name().charAt(0) + lowerLetters;
    }
}
