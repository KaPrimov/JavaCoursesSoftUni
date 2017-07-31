package app.gamestore.dto.viewDtos.game;

public class BoughGameView {
    private String title;

    public BoughGameView() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return " -" + this.title;
    }
}
