package weddingplanner.weddingplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import weddingplanner.weddingplanner.dto.binding.xml.AddPresentXmlWrapper;
import weddingplanner.weddingplanner.io.Serializer;
import weddingplanner.weddingplanner.services.api.PresentService;

@Controller
public class PresentsController {

    private static final String PRESENTS_INPUT_PATH = "/input/presents.xml";

    private final PresentService presentService;
    private final Serializer xmlSerializer;

    @Autowired
    public PresentsController(PresentService presentService,@Qualifier("XmlParser") Serializer xmlSerializer) {
        this.presentService = presentService;
        this.xmlSerializer = xmlSerializer;
    }

    public void savePresents() {
        AddPresentXmlWrapper wrapper = this.xmlSerializer.deserialize(AddPresentXmlWrapper.class, PRESENTS_INPUT_PATH);
        this.presentService.savePresents(wrapper);
    }

}
