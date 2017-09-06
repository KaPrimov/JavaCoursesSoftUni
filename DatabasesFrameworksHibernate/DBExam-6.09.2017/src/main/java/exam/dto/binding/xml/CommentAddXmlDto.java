package exam.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CommentAddXmlDto {

    @XmlElement(name = "content")
    private String content;
    @XmlElement(name = "user")
    private String user;
    @XmlElement(name = "post")
    private PostInCommentXmlDto post;

    public CommentAddXmlDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public PostInCommentXmlDto getPost() {
        return post;
    }

    public void setPost(PostInCommentXmlDto post) {
        this.post = post;
    }
}
