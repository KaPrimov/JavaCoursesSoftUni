package app.gamestore.dto.viewDtos.game;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameDetailView {

    private String title;
    private String trailer;
    private String thumbnailUrl;
    private Double size;
    private BigDecimal price;
    private String description;
    private Date releaseDate;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sb.append(String.format("Title: %s", this.title)).append(System.lineSeparator());
        sb.append(String.format("Price: %s", this.price)).append(System.lineSeparator());
        sb.append(String.format("Size: %.2f", this.size)).append(System.lineSeparator());
        sb.append(String.format("Thumbnail URL: %s", this.thumbnailUrl)).append(System.lineSeparator());
        sb.append(String.format("Description: %s", this.description)).append(System.lineSeparator());
        sb.append(String.format("Release date: %s", sdf.format(this.releaseDate)));
        return sb.toString();
    }
}
