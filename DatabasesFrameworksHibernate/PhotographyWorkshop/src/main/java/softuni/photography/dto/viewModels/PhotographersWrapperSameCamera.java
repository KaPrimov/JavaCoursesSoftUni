package softuni.photography.dto.viewModels;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographersWrapperSameCamera {

    @XmlElement(name = "photographer")
    private List<PhotographerXmlSameCamera> photographers;

    public PhotographersWrapperSameCamera() {
    }

    public List<PhotographerXmlSameCamera> getPhotographers() {
        return photographers;
    }

    public void setPhotographers(List<PhotographerXmlSameCamera> photographers) {
        this.photographers = photographers;
    }
}
