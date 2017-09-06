package exam.services.api;

import exam.dto.binding.json.PictureImportFromJsonDto;
import exam.entities.Picture;

public interface PictureService {

    Picture importPicture(PictureImportFromJsonDto picturesDto);

}
