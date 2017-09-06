package exam.services.impl;

import exam.dto.binding.xml.CommentAddXmlDto;
import exam.entities.Comment;
import exam.entities.Post;
import exam.entities.User;
import exam.repositories.CommentRepository;
import exam.repositories.PostRepository;
import exam.repositories.UserRepository;
import exam.services.api.CommentService;
import exam.utils.DTOConvertUtil;
import exam.utils.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment importComment(CommentAddXmlDto commentDto) {
        Comment comment = DTOConvertUtil.convert(commentDto, Comment.class);
        User user = this.userRepository.findUserByUsername(commentDto.getUser());
        if (commentDto.getPost() == null) {
            return null;
        }
        Post post = this.postRepository.findOne(commentDto.getPost().getId());

        comment.setPost(post);
        comment.setUser(user);

        if(DTOValidator.isValid(comment)) {
            comment = this.commentRepository.save(comment);
            return comment;
        } else {
            return null;
        }
    }
}
