package exam.dto.view.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class MostCommentsXmlWrapper {

    @XmlElement(name = "user")
    private List<MostCommentsUserDto> mostCommentUserDtos;

    public MostCommentsXmlWrapper() {
    }

    public List<MostCommentsUserDto> getMostCommentUserDtos() {
        return mostCommentUserDtos;
    }

    public void setMostCommentUserDtos(List<MostCommentsUserDto> mostCommentUserDtos) {
        this.mostCommentUserDtos = mostCommentUserDtos;
    }
}
