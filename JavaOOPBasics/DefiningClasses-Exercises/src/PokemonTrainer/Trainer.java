package PokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private int badges;
    private List<Pokemon> pokemons;

    public Trainer(String name) {
        this.name = name;
        this.badges = 0;
        this.pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public void tournamentRound(String round) {
        boolean isPresent = false;
        for (Pokemon pokemon : pokemons) {
            if(pokemon.getElement().equals(round) && pokemon.getHealth() > 0) {
                badges++;
                isPresent = true;
                break;
            }
        }

        if(!isPresent) {
            hurtPokemons();
        }
    }

    private void hurtPokemons() {
        for (Pokemon pokemon : pokemons) {
            pokemon.decreaseHP();
        }
    }

    private int returnSize() {
        int size = 0;
        for (Pokemon pokemon : pokemons) {
            if(pokemon.getHealth() > 0) {
                size++;
            }
        }
        return size;
    }

    public int getBadges() {
        return badges;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.badges, returnSize());
    }
}
