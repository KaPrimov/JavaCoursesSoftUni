package exam.repositories;

import exam.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts AS p\n" +
            "LEFT JOIN comments AS c\n" +
            "ON c.post_id=p.id\n" +
            "WHERE c.post_id IS NULL\n" +
            "ORDER BY p.id", nativeQuery = true)
    List<Post> findAllPostWithoutComments();

    List<Post> findAllByUserId(Long user_id);
}
