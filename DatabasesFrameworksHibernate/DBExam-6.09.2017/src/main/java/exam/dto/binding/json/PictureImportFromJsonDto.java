package exam.dto.binding.json;

import com.google.gson.annotations.Expose;

public class PictureImportFromJsonDto {

    @Expose
    private String path;
    @Expose
    private String size;

    public PictureImportFromJsonDto() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
