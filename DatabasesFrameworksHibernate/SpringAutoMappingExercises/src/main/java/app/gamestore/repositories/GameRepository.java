package app.gamestore.repositories;

import app.gamestore.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);

    @Query("SELECT u.boughtGames FROM User AS u WHERE u.id = :id")
    List<Game> findAllGamesOwnedBy(@Param("id") Long id);
}
