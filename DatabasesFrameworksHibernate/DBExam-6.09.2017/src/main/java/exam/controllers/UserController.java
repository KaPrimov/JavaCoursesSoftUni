package exam.controllers;

import exam.dto.binding.json.FollowerDataImportJsonDto;
import exam.dto.binding.json.UserImportFromJsonDto;
import exam.dto.view.json.PopularUserJsonDto;
import exam.dto.view.xml.MostCommentsXmlWrapper;
import exam.entities.User;
import exam.io.MessageWriter;
import exam.io.Serializer;
import exam.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private static final String COMMENTS_USERS_OUTPUT_PATH = "./src/main/resources/xml/output/comments-on-posts.xml";
    private static final String POPULAR_USERS_OUTPUT_PATH = "./src/main/resources/json/output/popular-users.json";
    private static final String IMPORT_FOLLOWER_MESSAGE = "Successfully imported Follower %s to User %s.%n";
    private static final String USERS_JSON_INPUT_PATH = "/json/input/users.json";
    private static final String FOLLOWERS_JSON_INPUT_PATH = "/json/input/users_followers.json";

    private final UserService userService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public UserController(UserService userService, @Qualifier("JsonParser") Serializer jsonSerializer,@Qualifier("XmlParser") Serializer xmlSerializer) {
        this.userService = userService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importUsers() {
        UserImportFromJsonDto[] userDtos = this.jsonSerializer.deserialize(UserImportFromJsonDto[].class, USERS_JSON_INPUT_PATH);
        for (UserImportFromJsonDto userDto : userDtos) {
            User user = this.userService.importUser(userDto);
            if(user != null) {
                MessageWriter.getInstance().printSuccess(User.class, user.getUsername());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }

    public void registerFollowers() {
        FollowerDataImportJsonDto[] followersData = this.jsonSerializer.deserialize(FollowerDataImportJsonDto[].class ,FOLLOWERS_JSON_INPUT_PATH);
        for (FollowerDataImportJsonDto followerDto : followersData) {
            if (this.userService.registerFollower(followerDto)) {
                System.out.printf(IMPORT_FOLLOWER_MESSAGE, followerDto.getFollower(), followerDto.getUser());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }

    public void exportPopularUsers() {
        List<PopularUserJsonDto> popularUserJsonDtos = this.userService.returnAllPopularUsers();
        this.jsonSerializer.serialize(popularUserJsonDtos, POPULAR_USERS_OUTPUT_PATH);
    }

    public void exportMostComments() {
        MostCommentsXmlWrapper usersMostComments = this.userService.findUsersMostComments();
        this.xmlSerializer.serialize(usersMostComments, COMMENTS_USERS_OUTPUT_PATH);
    }
}
