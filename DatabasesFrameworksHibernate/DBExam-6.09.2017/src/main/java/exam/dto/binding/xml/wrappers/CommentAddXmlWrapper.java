package exam.dto.binding.xml.wrappers;

import exam.dto.binding.xml.CommentAddXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "comments")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentAddXmlWrapper {

    @XmlElement(name = "comment")
    private List<CommentAddXmlDto> commentAddXmlDtoList;

    public CommentAddXmlWrapper() {
    }

    public List<CommentAddXmlDto> getCommentAddXmlDtoList() {
        return commentAddXmlDtoList;
    }

    public void setCommentAddXmlDtoList(List<CommentAddXmlDto> commentAddXmlDtoList) {
        this.commentAddXmlDtoList = commentAddXmlDtoList;
    }
}
