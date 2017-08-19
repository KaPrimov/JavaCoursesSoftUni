package softuni.dto.binding;

import com.google.gson.annotations.Expose;

public class AddTownJsonDto {
    @Expose
    private String name;
    @Expose
    private Integer population;

    public AddTownJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
