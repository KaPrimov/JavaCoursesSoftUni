package listIterator;

import java.util.Arrays;

public class CommandIterpreter {

    private ListIterator listIterator;

    public void acceptCommand(String[] tokens) {

        String command = tokens[0];

        switch (command) {
            case "Create":
                try {
                    this.listIterator = new ListIterator(Arrays.copyOfRange(tokens, 1, tokens.length));

                } catch (IllegalArgumentException iae) {}
                break;
            case "Move":
                System.out.println(this.listIterator.move());
                break;
            case "Print":
                try {
                    System.out.println(this.listIterator.print());
                } catch (NullPointerException eiie) {
                    System.out.println(eiie.getMessage());
                }
                break;
            case "HasNext":
                System.out.println(this.listIterator.hasNext());
                break;
            case "PrintAll":
                System.out.println(this.listIterator.printAll());

        }

    }
}
