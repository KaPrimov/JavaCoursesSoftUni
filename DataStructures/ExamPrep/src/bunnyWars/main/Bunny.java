package bunnyWars.main;

public class Bunny  {

    private static final int INITIAL_SCORE = 0;
    private static final int INITIAL_HEALTH = 100;
    public static final int ATTACK_DAMAGE = 30;

    private String name;
    private int team;
    private int roomId;
    private int health;
    private int score;

    public Bunny(String name, int team, int roomId) {
        this.setName(name);
        this.setTeam(team);
        this.setRoomId(roomId);
        this.setHealth(INITIAL_HEALTH);
        this.setScore(INITIAL_SCORE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void takeDamage() {
        this.setHealth(this.getHealth() - ATTACK_DAMAGE);
    }
}
