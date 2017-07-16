package P01.Logger.models.files;


import P01.Logger.enums.ReportLevel;
import P01.Logger.interfaces.File;
import P01.Logger.interfaces.Layout;

public class LogFile implements File {

    private static final String FILE_PATH = "logger.txt";

    private long size;

    @Override
    public void write(String message, ReportLevel severity, String date, Layout layout) {

//        try(PrintWriter out = new PrintWriter(FILE_PATH)) {
            String string = (layout.prepareLayout(date, severity, message, this)).replaceAll("[^A-Za-z]*", "").replaceAll("Filesize", "");
            for (int i = 0; i < string.length(); i++) {
                this.size += (int) string.charAt(i);
            }

//            out.print(string);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return ", File size: " + this.size;
    }
}
