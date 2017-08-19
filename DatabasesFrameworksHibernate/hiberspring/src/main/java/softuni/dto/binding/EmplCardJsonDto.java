package softuni.dto.binding;

import com.google.gson.annotations.Expose;

public class EmplCardJsonDto {
    @Expose
    private String number;

    public EmplCardJsonDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
