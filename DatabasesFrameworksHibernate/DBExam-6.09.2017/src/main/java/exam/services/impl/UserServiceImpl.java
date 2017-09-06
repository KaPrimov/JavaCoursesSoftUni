package exam.services.impl;

import exam.dto.binding.json.FollowerDataImportJsonDto;
import exam.dto.binding.json.UserImportFromJsonDto;
import exam.dto.view.json.PopularUserJsonDto;
import exam.dto.view.xml.MostCommentsUserDto;
import exam.dto.view.xml.MostCommentsXmlWrapper;
import exam.entities.Comment;
import exam.entities.Picture;
import exam.entities.Post;
import exam.entities.User;
import exam.repositories.CommentRepository;
import exam.repositories.PictureRepository;
import exam.repositories.PostRepository;
import exam.repositories.UserRepository;
import exam.services.api.UserService;
import exam.utils.DTOConvertUtil;
import exam.utils.DTOValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public User importUser(UserImportFromJsonDto userDto) {
        if(this.userRepository.findUserByUsername(userDto.getUsername()) != null) {
            return null;
        }
        User user = DTOConvertUtil.convert(userDto, User.class);
        Picture picture = null;
        if(user.getProfilePicture() == null) {
            picture = this.pictureRepository.findFirstByPath(userDto.getPath());
        }
        user.setProfilePicture(picture);
        if(DTOValidator.isValid(user)) {
            user = this.userRepository.save(user);
            return user;
        }
        return null;
    }

    @Override
    public boolean registerFollower(FollowerDataImportJsonDto followerData) {
        User user = this.userRepository.findUserByUsername(followerData.getUser());
        User follower = this.userRepository.findUserByUsername(followerData.getFollower());
        if(user == null || follower == null) {
            return false;
        }
        user.getFollowers().add(follower);
        follower.getFollowing().add(user);
        this.userRepository.save(user);
        this.userRepository.save(follower);
        return true;
    }

    @Override
    public MostCommentsXmlWrapper findUsersMostComments() {
        List<User> users = this.userRepository.findAll();
        MostCommentsXmlWrapper mostCommentsXmlWrapper = new MostCommentsXmlWrapper();
        List<MostCommentsUserDto> listUsersComments = new ArrayList<>();
        for (User user : users) {
            List<Post> allUsersPosts = this.postRepository.findAllByUserId(user.getId());

            OptionalInt isPresentComments = allUsersPosts.stream().sorted((p1, p2) -> Integer.compare(p2.getComments().size(), p1.getComments().size())).mapToInt(p -> p.getComments().size()).findFirst();
            int mostComments = 0;
            if(isPresentComments.isPresent()) {
                mostComments = isPresentComments.getAsInt();
            }
            MostCommentsUserDto userDto = DTOConvertUtil.convert(user, MostCommentsUserDto.class);
            userDto.setMostComments(mostComments);
            listUsersComments.add(userDto);
        }
        listUsersComments.sort(Comparator.comparing(MostCommentsUserDto::getMostComments).reversed());
        mostCommentsXmlWrapper.setMostCommentUserDtos(listUsersComments);

        return mostCommentsXmlWrapper;
    }

    @Override
    public List<PopularUserJsonDto> returnAllPopularUsers() {
        List<User> allUsers = this.userRepository.findAll();
        List<PopularUserJsonDto> popularUserJsonDtos = new ArrayList<>();
        for (User user : allUsers) {
            boolean isFoundFollower = false;
            List<Post> posts = this.postRepository.findAllByUserId(user.getId());
            for (Post post : posts) {
                List<Comment> comments = this.commentRepository.findAllByPostId(post.getId());
                boolean isFoundComment = false;
                for (Comment comment : comments) {
                    if (user.getFollowers().contains(comment.getUser())) {
                        PropertyMap<User, PopularUserJsonDto> propertyMap = new PropertyMap<User, PopularUserJsonDto>() {
                            @Override
                            protected void configure() {
                                map().setFollowers(source.getFollowers().size());
                            }
                        };
                        ModelMapper modelMapper = new ModelMapper();
                        modelMapper.addMappings(propertyMap);
                        PopularUserJsonDto popularUserJsonDto = modelMapper.map(user, PopularUserJsonDto.class);
                        popularUserJsonDtos.add(popularUserJsonDto);
                        isFoundComment =  true;
                    }
                    if (isFoundComment) {
                        isFoundFollower = true;
                        break;
                    }
                }
                if(isFoundFollower) {
                    break;
                }
            }
        }
        popularUserJsonDtos.sort(Comparator.comparingLong(PopularUserJsonDto::getId));
        return popularUserJsonDtos;
    }
}
