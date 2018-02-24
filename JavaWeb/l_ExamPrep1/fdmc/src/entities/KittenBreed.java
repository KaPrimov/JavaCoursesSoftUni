package entities;

public enum KittenBreed {
    STREET_TRANSCENDED,
    MUNCHKIN,
    SIAMESE,
    AMERICAN_SHORTHAIR;

    public static KittenBreed parseValue(String string) {
        if(string.equals("Munchkin")) return MUNCHKIN;
        else if(string.equals("Street Transcended")) return STREET_TRANSCENDED;
        else if(string.equals("Siamese")) return SIAMESE;
        else if(string.equals("American Shorthair")) return AMERICAN_SHORTHAIR;
        return null;
    }

    public static String getSimpleValue(KittenBreed breed) {
        return breed.toString().toLowerCase().replace("_", "-");
    }

    public static String getComplexValue(KittenBreed breed) {
        if(breed.equals(MUNCHKIN)) return "Munchkin";
        else if (breed.equals(STREET_TRANSCENDED)) return "Street Transcended";
        else if (breed.equals(SIAMESE)) return "Siamese";
        else if (breed.equals(AMERICAN_SHORTHAIR)) return "American Shorthair";
        return null;
    }
}
