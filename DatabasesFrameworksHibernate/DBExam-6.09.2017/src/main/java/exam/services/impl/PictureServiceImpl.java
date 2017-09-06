package exam.services.impl;

import exam.dto.binding.json.PictureImportFromJsonDto;
import exam.entities.Picture;
import exam.repositories.PictureRepository;
import exam.services.api.PictureService;
import exam.utils.DTOConvertUtil;
import exam.utils.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    @Override
    public Picture importPicture(PictureImportFromJsonDto picturesDto) {
        Picture picture = DTOConvertUtil.convert(picturesDto, Picture.class);
        if(DTOValidator.isValid(picture)) {
            picture = this.pictureRepository.save(picture);
            return picture;
        }
        return null;
    }
}
