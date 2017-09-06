package exam.controllers;

import exam.dto.binding.xml.CommentAddXmlDto;
import exam.dto.binding.xml.wrappers.CommentAddXmlWrapper;
import exam.entities.Comment;
import exam.io.MessageWriter;
import exam.io.Serializer;
import exam.services.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {
    private static final String COMMENTS_XML_INPUT_PATH = "/xml/input/comments.xml";

    private final CommentService commentService;
    private final Serializer xmlSerializer;

    @Autowired
    public CommentController(CommentService commentService,@Qualifier("XmlParser") Serializer xmlSerializer) {
        this.commentService = commentService;
        this.xmlSerializer = xmlSerializer;
    }

    public void importComments() {
        CommentAddXmlWrapper commentAddXmlWrapper = this.xmlSerializer.deserialize(CommentAddXmlWrapper.class, COMMENTS_XML_INPUT_PATH);
        for (CommentAddXmlDto commentAddXmlDto : commentAddXmlWrapper.getCommentAddXmlDtoList()) {
            Comment comment = this.commentService.importComment(commentAddXmlDto);
            if(comment != null) {
                MessageWriter.getInstance().printSuccess(Comment.class, comment.getContent());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }
}
