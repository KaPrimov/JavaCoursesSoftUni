package rpg_lab;

import java.util.Random;

public interface RandomProvider {

    int nextInt(int max);

    class Implementation implements RandomProvider {

        private Random random;


        public Implementation() {
            this.random = new Random();
        }

        public int nextInt(int max) {
            return this.random.nextInt(max);
        }
    }

}
