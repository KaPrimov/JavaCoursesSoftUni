package tuple;

public class Tuple<F, S, T> {

    private F firstElement;
    private S secondElement;
    private T thirdElement;

    public Tuple(F firstElement, S secondElement, T thirdElement) {
       this.setFirstElement(firstElement);
        this.setSecondElement(secondElement);
        this.setThirdElement(thirdElement);
    }

    public T getThirdElement() {
        return thirdElement;
    }

    public void setThirdElement(T thirdElement) {
        this.thirdElement = thirdElement;
    }

    public F getFirstElement() {
        return firstElement;
    }

    private void setFirstElement(F firstElement) {
        this.firstElement = firstElement;
    }

    public S getSecondElement() {
        return secondElement;
    }

    private void setSecondElement(S secondElement) {
        this.secondElement = secondElement;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> %s", this.firstElement, this.secondElement, this.thirdElement);
    }
}
