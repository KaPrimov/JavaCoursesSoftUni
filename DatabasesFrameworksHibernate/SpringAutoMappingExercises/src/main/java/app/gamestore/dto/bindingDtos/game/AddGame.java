package app.gamestore.dto.bindingDtos.game;

import app.gamestore.annotations.SizeMatching;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class AddGame {
    @Size(min = 3, max = 100, message = "The title should be between 3 and 100 symbols")
    @Pattern(regexp = "[A-Z]+.*", message = "Title should start with uppercase")
    private String title;
    @DecimalMin(value = "0.00", message = "Price should be positive")
    @Digits(integer = 100, fraction = 2)
    private BigDecimal price;
    @SizeMatching
    private Double size;
    @Size(min = 11, max = 11, message = "Trailer must be 11 symbols")
    private String trailer;
    @Pattern(regexp = "(http:\\/\\/|https:\\/\\/).+", message = "Thumbnail is not valid")
    private String thumbnailUrl;
    @Size(min = 20)
    private String description;
    private Date releaseDate;

    public AddGame() {
    }

    public AddGame(String title, BigDecimal price, Double size, String trailer, String thumbnailUrl, String description, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
