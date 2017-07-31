package app.gamestore.services.impl;

import app.gamestore.dto.bindingDtos.game.AddGame;
import app.gamestore.dto.bindingDtos.game.EditGame;
import app.gamestore.dto.viewDtos.game.GameDetailView;
import app.gamestore.dto.viewDtos.game.GameModelView;
import app.gamestore.dto.viewDtos.game.SingleBoughtGameView;
import app.gamestore.entities.Game;
import app.gamestore.repositories.GameRepository;
import app.gamestore.services.api.GameService;
import app.gamestore.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void addGame(AddGame addGame) {
        Game game = ModelParser.getInstance().map(addGame, Game.class);
        this.gameRepository.saveAndFlush(game);
    }

    @Override
    public void updateGame(EditGame editGame) {
        Game game = ModelParser.getInstance().map(editGame, Game.class);
        this.gameRepository.saveAndFlush(game);
    }

    @Override
    public List<GameModelView> getAll() {
        List<Game> games = this.gameRepository.findAll();
        List<GameModelView> gameViews = new ArrayList<>();

        for (Game game : games) {
            gameViews.add(ModelParser.getInstance().map(game, GameModelView.class));
        }
        return gameViews;
    }

    @Override
    public GameDetailView findByTitle(String title) {
        Game game = this.gameRepository.findByTitle(title);
        if (game == null) {
            return null;
        }
        return ModelParser.getInstance().map(game, GameDetailView.class);

    }

    @Override
    public <T> T findById(Long id, Class<T> gameType) {
        Game game = this.gameRepository.findOne(id);
        if (game == null) {
            return null;
        }
        return ModelParser.getInstance().map(game, gameType);
    }

    @Override
    public List<SingleBoughtGameView> findBoughtGames(Long id) {
        List<Game> games = this.gameRepository.findAllGamesOwnedBy(id);
        List<SingleBoughtGameView> singleBoughtGameViews = new ArrayList<>();

        for (Game game : games) {
            singleBoughtGameViews.add(ModelParser.getInstance().map(game, SingleBoughtGameView.class));
        }

        return singleBoughtGameViews;
    }
}
