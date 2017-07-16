package hack;

public class Hack {

    public double mathAbs(double number) {
        return Math.abs(number);
    }

    public double mathFloor(double number) {
        return Math.floor(number);
    }

    public String newLine(String one, String two) {
        return one + System.lineSeparator() + two;
    }

    public static void main(String[] args) {
        Hack hack = new Hack();
        System.out.println(hack.mathFloor(2.64543));
    }
}
