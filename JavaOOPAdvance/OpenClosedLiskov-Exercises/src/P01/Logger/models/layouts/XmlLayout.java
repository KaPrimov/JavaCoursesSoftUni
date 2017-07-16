package P01.Logger.models.layouts;

import P01.Logger.enums.ReportLevel;
import P01.Logger.interfaces.File;
import P01.Logger.interfaces.Layout;

public class XmlLayout implements Layout {
    @Override
    public String prepareLayout(String date, ReportLevel severity, String message) {

        StringBuilder sb = new StringBuilder();
        sb.append("<log>").append(System.lineSeparator());
        sb.append(String.format("   <date>%s</date>", date)).append(System.lineSeparator());
        sb.append(String.format("   <level>%s</level>", severity.name())).append(System.lineSeparator());
        sb.append(String.format("   <message>%s</message>", message)).append(System.lineSeparator());
        sb.append("</log>");

        return sb.toString();
    }

    @Override
    public String prepareLayout(String date, ReportLevel severity, String message, File file) {
        StringBuilder sb = new StringBuilder(this.prepareLayout(date, severity, message));
        sb.append(System.lineSeparator());
        sb.append(file.toString());
        return sb.toString();
    }
}
