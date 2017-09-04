package weddingplanner.weddingplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import weddingplanner.weddingplanner.dto.binding.json.AddWeddingDto;
import weddingplanner.weddingplanner.io.Serializer;
import weddingplanner.weddingplanner.services.api.WeddingService;

@Controller
public class WeddingController {

    private static final String WEDDINGS_INPUT_PATH = "/input/weddings.json";

    private final WeddingService weddingService;
    private final Serializer jsonSerializer;

    @Autowired
    public WeddingController(WeddingService weddingService,@Qualifier("JsonParser") Serializer jsonSerializer) {
        this.weddingService = weddingService;
        this.jsonSerializer = jsonSerializer;
    }

    public void saveWeddings() {
        AddWeddingDto[] addWeddingDtos = this.jsonSerializer.deserialize(AddWeddingDto[].class, WEDDINGS_INPUT_PATH);
        this.weddingService.saveWeddings(addWeddingDtos);
    }

    public void addVenuesToWeddings() {
        this.weddingService.addVenues();
    }
}
