package exam.services.impl;

import exam.dto.binding.xml.PostAddXmlDto;
import exam.dto.view.json.UncommentedPostDto;
import exam.entities.Picture;
import exam.entities.Post;
import exam.entities.User;
import exam.repositories.PictureRepository;
import exam.repositories.PostRepository;
import exam.repositories.UserRepository;
import exam.services.api.PostService;
import exam.utils.DTOConvertUtil;
import exam.utils.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Post addPost(PostAddXmlDto postDto) {
        Post post = new Post();

        User user = this.userRepository.findUserByUsername(postDto.getUsername());
        Picture picture = this.pictureRepository.findFirstByPath(postDto.getPicture());

        post.setCaption(postDto.getCaption());
        post.setPicture(picture);
        post.setUser(user);

        if(DTOValidator.isValid(post)) {
            post = this.postRepository.save(post);
            return post;
        } else {
            return null;
        }
    }

    @Override
    public List<UncommentedPostDto> returnUncommentedPosts() {
        List<Post> allPostWithoutComments = this.postRepository.findAllPostWithoutComments();
        return DTOConvertUtil.convert(allPostWithoutComments, UncommentedPostDto.class);
    }
}
