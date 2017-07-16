package p01_system_resources;

public class OutputWriterImpl implements OutputWriter {



    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
