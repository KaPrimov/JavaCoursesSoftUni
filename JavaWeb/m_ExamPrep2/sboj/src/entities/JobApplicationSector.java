package entities;

public enum JobApplicationSector {
    MEDICINE,
    SECURITY,
    DOMESTIC,
    FOOD,
    CAR;

    public static JobApplicationSector parseValue(String value) {
        return JobApplicationSector.valueOf(value.toUpperCase());
    }

    public static String getSimpleValue(JobApplicationSector sector) {
        return sector.toString().toLowerCase();
    }

    public static String getComplexValue(JobApplicationSector sector) {
        String stringRepresentation = sector.toString().toLowerCase();
        String result = stringRepresentation.toUpperCase().charAt(0)
                + stringRepresentation.substring(1);
        return result;
    }
}
