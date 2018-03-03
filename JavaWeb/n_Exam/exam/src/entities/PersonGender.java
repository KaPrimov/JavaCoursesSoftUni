package entities;

public enum PersonGender {
    MALE, FEMALE;

    public static PersonGender parseValue(String value) {
        return PersonGender.valueOf(value.toUpperCase());
    }

    public static String getSimpleValue(PersonGender gender) {
        return gender.toString().toLowerCase();
    }

    public static String getComplexValue(PersonGender gender) {
        String stringRepresentation = gender.toString().toLowerCase();
        return stringRepresentation.toUpperCase().charAt(0)
                + stringRepresentation.substring(1);
    }
}
