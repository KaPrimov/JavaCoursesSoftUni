package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.json.AddPersonDto;

public interface PeopleService {

    void addPeople(AddPersonDto[] personDtos);

}
