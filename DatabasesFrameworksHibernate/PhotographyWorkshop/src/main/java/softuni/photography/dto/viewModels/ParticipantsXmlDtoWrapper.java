package softuni.photography.dto.viewModels;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipantsXmlDtoWrapper {

    @XmlAttribute(name = "count")
    private Integer count;
    @XmlElement(name = "participant")
    private List<PhotographerParticipantDto> participants;

    public ParticipantsXmlDtoWrapper() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<PhotographerParticipantDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PhotographerParticipantDto> participants) {
        this.participants = participants;
    }
}
