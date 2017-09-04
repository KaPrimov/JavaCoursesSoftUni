package weddingplanner.weddingplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import weddingplanner.weddingplanner.dto.binding.json.AddPersonDto;
import weddingplanner.weddingplanner.io.Serializer;
import weddingplanner.weddingplanner.services.api.PeopleService;

@Controller
public class PeopleController {

    private static final String PEOPLE_INPUT_PATH = "/input/people.json";

    private final Serializer jsonSerializer;
    private final PeopleService peopleServicel;

    @Autowired
    public PeopleController(@Qualifier("JsonParser") Serializer jsonSerializer, PeopleService peopleServicel) {
        this.jsonSerializer = jsonSerializer;
        this.peopleServicel = peopleServicel;
    }

    public void addPeople() {
        AddPersonDto[] personDtos = this.jsonSerializer.deserialize(AddPersonDto[].class, PEOPLE_INPUT_PATH);
        this.peopleServicel.addPeople(personDtos);
    }
}
