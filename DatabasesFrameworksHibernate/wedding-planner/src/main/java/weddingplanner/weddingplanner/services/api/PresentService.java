package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.xml.AddPresentXmlWrapper;

public interface PresentService {
    void savePresents(AddPresentXmlWrapper addPresentXmlWrapper);
}
