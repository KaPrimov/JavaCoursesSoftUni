package app.gamestore.services.api;

import app.gamestore.dto.bindingDtos.game.AddGame;
import app.gamestore.dto.bindingDtos.game.EditGame;
import app.gamestore.dto.viewDtos.game.GameDetailView;
import app.gamestore.dto.viewDtos.game.GameModelView;
import app.gamestore.dto.viewDtos.game.SingleBoughtGameView;

import java.util.List;

public interface GameService {
    void addGame(AddGame addGame);
    void updateGame(EditGame editGame);
    List<GameModelView> getAll();
    GameDetailView findByTitle(String title);
    <T> T findById(Long id, Class<T> gameType);
    List<SingleBoughtGameView> findBoughtGames(Long id);
}
