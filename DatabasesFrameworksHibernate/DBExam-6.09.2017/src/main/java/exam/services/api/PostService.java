package exam.services.api;

import exam.dto.binding.xml.PostAddXmlDto;
import exam.dto.view.json.UncommentedPostDto;
import exam.entities.Post;

import java.util.List;

public interface PostService {
    Post addPost(PostAddXmlDto postDto);

    List<UncommentedPostDto> returnUncommentedPosts();
}
