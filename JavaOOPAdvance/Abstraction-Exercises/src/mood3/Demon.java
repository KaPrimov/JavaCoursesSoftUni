package mood3;

public class Demon extends GameObject {
    public Demon(String username, int level) {
        super(username, level, "Demon");
    }

    @Override
    protected String generateHashPassword(String username) {
        return "" + (super.getUsername().length() * 217);
    }
}
