package repositories;

import entities.Kitten;

import java.util.List;

public class KittenRepository extends BaseRepository {
    public void createKitten(Kitten kitten) {
        this.execute(actionResult -> {
            this.em.persist(kitten);
        });
    }

    public Kitten[] allKittens() {
        Kitten[] allKittens = (Kitten[]) this.execute(actionResult -> {
            List<Kitten> kittens =
                    this.em
                    .createNativeQuery("SELECT * FROM kittens AS k", Kitten.class)
                    .getResultList();
            
            Kitten[] kittenArray = new Kitten[kittens.size()];

            for (int i = 0; i < kittens.size(); i++) {
                kittenArray[i] = kittens.get(i);
            }

            actionResult.setResult(kittenArray);
        }).getResult();

        return allKittens;
    }
}
