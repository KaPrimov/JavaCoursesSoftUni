package app.gamestore.dto.viewDtos.game;

public class SingleBoughtGameView {

    private String title;

    public SingleBoughtGameView() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
