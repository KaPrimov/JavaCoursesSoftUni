package froggy;

import java.util.Iterator;
import java.util.List;

public class Lake implements Iterable<Integer> {

    private List<Integer> numbers;

    public Lake(List<Integer> numbers) {
        this.numbers = numbers;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }

    private final class Frog implements Iterator<Integer> {

        private int evenIndex;
        private int oddIndex;

        public Frog() {
            this.oddIndex = 0;
            this.evenIndex = 1;
        }

        @Override
        public boolean hasNext() {
            return this.evenIndex < numbers.size() || this.oddIndex < numbers.size();
        }

        @Override
        public Integer next() {
            int current = 0;
            if(this.oddIndex < numbers.size()) {
                current = numbers.get(oddIndex);
                oddIndex += 2;
                return current;
            }
            current = numbers.get(evenIndex);
            evenIndex+=2;
            return current;
        }
    }
}
