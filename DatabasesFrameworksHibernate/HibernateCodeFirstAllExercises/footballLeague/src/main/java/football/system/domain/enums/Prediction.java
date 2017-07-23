package football.system.domain.enums;

public enum Prediction {
    HOME("Home team win"),
    AWAY("Away team win"),
    DRAW("Draw game");

    private String prediction;

    Prediction(String prediction) {
        this.prediction = prediction;
    }

    public String getPrediction() {
        return prediction;
    }
}
