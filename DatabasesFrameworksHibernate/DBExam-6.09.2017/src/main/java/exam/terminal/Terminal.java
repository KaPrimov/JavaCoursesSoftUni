package exam.terminal;

import exam.controllers.CommentController;
import exam.controllers.PictureController;
import exam.controllers.PostController;
import exam.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final UserController userController;
    private final CommentController commentController;
    private final PostController postController;
    private final PictureController pictureController;

    @Autowired
    public Terminal(UserController userController, CommentController commentController, PostController postController, PictureController pictureController) {
        this.userController = userController;
        this.commentController = commentController;
        this.postController = postController;
        this.pictureController = pictureController;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.pictureController.importPictures();
        this.userController.importUsers();
        this.userController.registerFollowers();
        this.postController.addPosts();
        this.commentController.importComments();
        this.postController.exportUncommentedPost();
        this.userController.exportPopularUsers();
        this.userController.exportMostComments();

    }
}
