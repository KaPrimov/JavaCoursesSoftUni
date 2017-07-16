package mood3;

public abstract class GameObject implements IGameObject {
    private String username;
    private String hashedPassword;
    private int level;
    private String specialPoints;

    public GameObject(String username, int level, String specialPoints) {
        this.username = username;
        this.hashedPassword = this.generateHashPassword(username);
        this.level = level;
        this.specialPoints = specialPoints;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String gethashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getPoints() {
        return this.specialPoints;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" | \"%s\" -> %s", this.username, this.hashedPassword, this.specialPoints);
    }

    protected abstract String generateHashPassword(String username);


}
