package exam.controllers;

import exam.dto.binding.xml.PostAddXmlDto;
import exam.dto.binding.xml.wrappers.PostsAddWrapper;
import exam.dto.view.json.UncommentedPostDto;
import exam.entities.Post;
import exam.io.MessageWriter;
import exam.io.Serializer;
import exam.services.api.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostController {

    private static final String POSTS_XML_INPUT_PATH = "/xml/input/posts.xml";
    private static final String UNCOMMENTED_POSTS_JSON_OUTPUT_PATH = "./src/main/resources/json/output/uncommented-posts.json";

    private final PostService postService;
    private final Serializer xmlSerializer;
    private final Serializer jsonSerializer;

    @Autowired
    public PostController(PostService postService, @Qualifier("XmlParser") Serializer xmlSerializer,@Qualifier("JsonParser") Serializer jsonSerializer) {
        this.postService = postService;
        this.xmlSerializer = xmlSerializer;
        this.jsonSerializer = jsonSerializer;
    }

    public void addPosts() {
        PostsAddWrapper postsAddWrapper = this.xmlSerializer.deserialize(PostsAddWrapper.class, POSTS_XML_INPUT_PATH);
        for (PostAddXmlDto postAddXmlDto : postsAddWrapper.getPostAddXmlDtos()) {
            Post post = this.postService.addPost(postAddXmlDto);
            if(post != null) {
                MessageWriter.getInstance().printSuccess(Post.class, post.getCaption());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }

    public void exportUncommentedPost() {
        List<UncommentedPostDto> uncommentedPostDtos = this.postService.returnUncommentedPosts();
        this.jsonSerializer.serialize(uncommentedPostDtos, UNCOMMENTED_POSTS_JSON_OUTPUT_PATH);
    }
}
