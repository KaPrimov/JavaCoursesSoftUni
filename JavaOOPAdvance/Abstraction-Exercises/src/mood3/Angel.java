package mood3;

public class Angel extends GameObject {
    public Angel(String username, int level) {
        super(username, level, "Archangel");
    }

    @Override
    protected String generateHashPassword(String username) {
        long count = super.getUsername().length() * 21;
        return new StringBuilder(getUsername()).reverse().toString() + count;
    }
}
