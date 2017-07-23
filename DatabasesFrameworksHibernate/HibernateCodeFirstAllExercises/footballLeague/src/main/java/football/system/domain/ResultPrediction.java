package football.system.domain;

import football.system.domain.enums.Prediction;

import javax.persistence.*;

@Entity(name = "result_predictions")
public class ResultPrediction {
    private Long id;
    private Prediction prediction;

    public ResultPrediction() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
