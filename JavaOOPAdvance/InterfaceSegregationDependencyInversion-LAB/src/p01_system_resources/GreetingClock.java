package p01_system_resources;

public class GreetingClock {

    private HourProvidable hourProvidable;
    private OutputWriter outputWriter;

    public GreetingClock(HourProvidable hourProvidable, OutputWriter outputWriter) {
        this.hourProvidable = hourProvidable;
        this.outputWriter = outputWriter;
    }

    public void greeting() {
        if (this.hourProvidable.getHour() < 12) {
           this.outputWriter.printMessage("Good morning...");
        } else if (this.hourProvidable.getHour() < 18) {
            this.outputWriter.printMessage("Good afternoon...");
        } else {
            this.outputWriter.printMessage("Good evening...");
        }
    }

    public static void main(String[] args) {
        HourProvidable hourProvidable = new HourProvider();
        OutputWriter outputWriter = new OutputWriterImpl();
        GreetingClock greetingClock = new GreetingClock(hourProvidable, outputWriter);

        greetingClock.greeting();
    }
}
