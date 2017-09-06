package exam.dto.binding.xml.wrappers;

import exam.dto.binding.xml.PostAddXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsAddWrapper {

    @XmlElement(name = "post")
    private List<PostAddXmlDto> postAddXmlDtos;

    public PostsAddWrapper() {
    }

    public List<PostAddXmlDto> getPostAddXmlDtos() {
        return postAddXmlDtos;
    }

    public void setPostAddXmlDtos(List<PostAddXmlDto> postAddXmlDtos) {
        this.postAddXmlDtos = postAddXmlDtos;
    }
}
