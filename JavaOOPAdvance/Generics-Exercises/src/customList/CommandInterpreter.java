package customList;

public class CommandInterpreter {

    private customList<String> customList;

    public CommandInterpreter() {
        this.customList = new customList<String>();
    }

    public void execute(String[] params) {
        String command = params[0];

        switch (command) {
            case "Add":
                this.customList.add(params[1]);
                break;

            case "Remove":
                this.customList.remove(Integer.parseInt(params[1]));
                break;

            case "Contains" :
                System.out.println(this.customList.contains(params[1]));
                break;

            case "Swap":
                this.customList.swap(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
                break;

            case "Max":
                System.out.println(this.customList.getMax());
                break;

            case "Min":
                System.out.println(this.customList.getMin());
                break;

            case "Greater":
                System.out.println(customList.countGreaterThan(params[1]));
                break;

            case "Sort":
                customList.sort();
                break;

            case "Print":
                for (String o : this.customList) {
                    System.out.println(o);
                }
        }
    }
}
