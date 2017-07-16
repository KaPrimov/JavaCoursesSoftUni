package P01.Logger.models.appenders;

import P01.Logger.enums.ReportLevel;
import P01.Logger.interfaces.File;
import P01.Logger.interfaces.Layout;
import P01.Logger.models.files.LogFile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileAppender extends AppenderImpl {

    private File file;

    public FileAppender(Layout layout) {
        super(layout);
        file = new LogFile();
    }

    public void setFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("You have to give an existing file");
        }
        this.file = file;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.file.toString());
        return sb.toString();
    }

    @Override
    public void saveFile(String date, ReportLevel severity, String message) {


        if (severity.ordinal() >= super.getReportLevel().ordinal()) {
            super.saveMessage();
            file.write(message, severity, date, super.getLayout());
        }


    }
}
