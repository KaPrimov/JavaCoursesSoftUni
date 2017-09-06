package exam.services.api;

import exam.dto.binding.xml.CommentAddXmlDto;
import exam.entities.Comment;

public interface CommentService {
    Comment importComment(CommentAddXmlDto commentDto);

}
