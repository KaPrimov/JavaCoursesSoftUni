package militaryElite.models;

import militaryElite.interfaces.ISpecialisedSoldier;

public abstract class SpecialisedSoldier extends Private implements ISpecialisedSoldier {

    private String corps;

    public SpecialisedSoldier(String id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary);
        setCorps(corp);
    }

    public void setCorps(String corps) {
        if(!corps.equals("Airforces") && !corps.equals("Marines")) {
            throw new IllegalArgumentException("Illeagal corps");
        }
        this.corps = corps;
    }

    @Override
    public String getCorps() {
        return corps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Corps: " + this.corps).append(System.lineSeparator());
        return sb.toString();
    }
}
