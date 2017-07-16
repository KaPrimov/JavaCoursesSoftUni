package bubbleSort;

public class Main {
    public static void main(String[] args) {
        Bubble<String> bubble = new Bubble<String>("Pesho", "Gosho", "Tosho");
        System.out.println(bubble.returnElements());
    }
}
