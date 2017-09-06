package exam.controllers;

import exam.dto.binding.json.PictureImportFromJsonDto;
import exam.entities.Picture;
import exam.io.MessageWriter;
import exam.io.Serializer;
import exam.services.api.PictureService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PictureController {
    
    private static final String PICTURES_JSON_INPUT_PATH = "/json/input/pictures.json"; 
    
    private final Serializer jsonSerilizer;
    private final PictureService pictureService;

    public PictureController(@Qualifier("JsonParser") Serializer jsonSerilizer, PictureService pictureService) {
        this.jsonSerilizer = jsonSerilizer;
        this.pictureService = pictureService;
    }


    public void importPictures() {
        PictureImportFromJsonDto[] pictures = this.jsonSerilizer.deserialize(PictureImportFromJsonDto[].class, PICTURES_JSON_INPUT_PATH);
        for (PictureImportFromJsonDto pictureDto : pictures) {
            Picture picture = this.pictureService.importPicture(pictureDto);
            if(picture != null) {
                MessageWriter.getInstance().printSuccess(Picture.class, picture.getPath());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }
    
}
