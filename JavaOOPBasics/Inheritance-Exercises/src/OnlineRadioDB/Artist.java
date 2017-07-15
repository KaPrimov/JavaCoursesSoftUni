package OnlineRadioDB;

public class Artist {
    private String name;

    public Artist(String name) {
        this.setName(name);
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 3 || name.trim().length() > 20){
            throw new InvalidArtistNameException();
        }
        this.name = name;
    }
}