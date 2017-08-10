package softuni.photography.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import softuni.photography.dto.bindingModels.add.AddAccessoryXmlDto;
import softuni.photography.dto.bindingModels.add.AddCameraJsonDto;
import softuni.photography.dto.bindingModels.add.AddLensJsonDto;
import softuni.photography.dto.bindingModels.add.AddPhotographerJsonDto;
import softuni.photography.dto.bindingModels.add.xmlWrappers.AccessoryXmlWrapper;
import softuni.photography.dto.bindingModels.add.xmlWrappers.WorkshopXmlWrapper;
import softuni.photography.dto.viewModels.PhotographerXmlSameCamera;
import softuni.photography.dto.viewModels.PhotographersWrapperSameCamera;
import softuni.photography.entities.Camera;
import softuni.photography.entities.DslrCamera;
import softuni.photography.entities.MirrorlessCamera;
import softuni.photography.entities.Photographer;
import softuni.photography.io.Serializer;
import softuni.photography.services.api.*;
import softuni.photography.utils.DTOConvertUtil;
import softuni.photography.utils.DTOValidator;

import java.util.List;

@Component
@Transactional
public class Terminal implements CommandLineRunner {
    public static final String OUT_PATH = "src\\main\\resources\\out\\";


    private final Serializer jsonParser;
    private final Serializer xmlParser;
    private final DTOValidator dtoValidator;
    private final LensService lensService;
    private final CameraService cameraService;
    private final PhotographerService photographerService;
    private final AccessoryService accessoryService;
    private final WorkshopService workshopService;

    @Autowired
    public Terminal(DTOValidator dtoValidator, @Qualifier("XmlParser") Serializer xmlParser, @Qualifier("JsonParser") Serializer jsonParser, LensService lensService, CameraService cameraService, PhotographerService photographerService, AccessoryService accessoryService, WorkshopService workshopService) {
        this.dtoValidator = dtoValidator;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
        this.lensService = lensService;
        this.cameraService = cameraService;
        this.photographerService = photographerService;
        this.accessoryService = accessoryService;
        this.workshopService = workshopService;
    }

    @Override
    public void run(String... strings) throws Exception {
        //importLenses();
        //importCameras();
        //importPhotographers();
        //importAccessoires();
        //importWorkshops();

        //1st Query
        //List<OrderedPhotographerDto> orderedPhotographersDtos = this.photographerService.findAllPhotographersOrderedByFirstNameAscLastNameDesc();
        //this.jsonParser.serialize(orderedPhotographersDtos, OUT_PATH + "photographers-ordered.json");

        //2nd Query
        //List<LandscapePhotographersDto> landscapePhotographers = this.photographerService.findAllLandscapePhotographers();
        //this.jsonParser.serialize(landscapePhotographers, OUT_PATH + "landscape-photographers.json");

        //3rd Query
        exportPhotographersWithSameCameras();
    }

    private void exportPhotographersWithSameCameras() {
        List<Photographer> photographersWithSameCameras = this.photographerService.photographersWithSameCameras();
        List<PhotographerXmlSameCamera> convert = DTOConvertUtil.convert(photographersWithSameCameras, PhotographerXmlSameCamera.class);
        PhotographersWrapperSameCamera wrapperSameCamera = new PhotographersWrapperSameCamera();
        wrapperSameCamera.setPhotographers(convert);
        this.xmlParser.serialize(wrapperSameCamera, OUT_PATH + "same-cameras-photographers.xml");
    }

    private void importWorkshops() {

        WorkshopXmlWrapper workshopXmlWrapper = this.xmlParser.deserialize(WorkshopXmlWrapper.class, "/in/workshops.xml");
        this.workshopService.save(workshopXmlWrapper);
        String debug = "";

    }

    private void importAccessoires() {

        AccessoryXmlWrapper accessoryXmlWrapper = this.xmlParser.deserialize(AccessoryXmlWrapper.class, "/in/accessories.xml");
        for (AddAccessoryXmlDto xmlDto : accessoryXmlWrapper.getAddAccessoryXmlDtos()) {
            this.accessoryService.save(xmlDto);
        }
    }

    private void importPhotographers() {
        AddPhotographerJsonDto[] photographerJsonDtos = jsonParser.deserialize(AddPhotographerJsonDto[].class, "/in/photographers.json");
        for (AddPhotographerJsonDto photographerJsonDto : photographerJsonDtos) {
            this.photographerService.save(photographerJsonDto, photographerJsonDto.getLenses());
        }
    }

    private void importCameras() {
        AddCameraJsonDto[] addCameraJsonDtos = this.jsonParser.deserialize(AddCameraJsonDto[].class, "/in/cameras.json");
        for (AddCameraJsonDto addCameraJsonDto : addCameraJsonDtos) {
            Camera camera = null;
            if ("DSLR".equalsIgnoreCase(addCameraJsonDto.getType())) {
                camera = DTOConvertUtil.convert(addCameraJsonDto, DslrCamera.class);
                this.cameraService.save(camera);
            } else if ("Mirrorless".equalsIgnoreCase(addCameraJsonDto.getType())) {
                camera = DTOConvertUtil.convert(addCameraJsonDto, MirrorlessCamera.class);
                this.cameraService.save(camera);
            }

        }
    }

    private void importLenses() {

        AddLensJsonDto[] addLensJsonDtos = this.jsonParser.deserialize(AddLensJsonDto[].class, "/in/lenses.json");

        for (AddLensJsonDto addLensJsonDto : addLensJsonDtos) {
            this.lensService.save(addLensJsonDto);
        }

    }
}
