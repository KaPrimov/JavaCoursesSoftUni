package JarOfT;

public class Main {
    public static void main(String[] args) {
        Jar<Pickle> pickleJar = new Jar<>();

        pickleJar.add(new Pickle());
        pickleJar.add(new Pickle());

        Pickle pickle = pickleJar.remove();
    }
}