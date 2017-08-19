package softuni.dto.view.json;

import com.google.gson.annotations.Expose;

public class FreeCardsViewDto {

    @Expose
    private String number;

    public FreeCardsViewDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
