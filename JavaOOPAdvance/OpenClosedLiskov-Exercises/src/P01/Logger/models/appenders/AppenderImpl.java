package P01.Logger.models.appenders;

import P01.Logger.enums.ReportLevel;
import P01.Logger.interfaces.Appender;
import P01.Logger.interfaces.Layout;

public abstract class AppenderImpl implements Appender {

    private Layout layout;
    private ReportLevel reportLevel;
    private int messages;

    protected AppenderImpl(Layout layout) {
        this.setLayout(layout);
        reportLevel = ReportLevel.INFO;
    }

    public void setLayout(Layout layout) {
        if(layout == null) {
            throw new IllegalArgumentException("The layout can not be null");
        }
        this.layout = layout;
    }

    protected Layout getLayout() {
        return this.layout;
    }

    protected ReportLevel getReportLevel() {
        return this.reportLevel;
    }

    protected void saveMessage() {
        this.messages++;
    }

    protected int getMessages() {
        return messages;
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d"
                , this.getClass().getSimpleName(), this.layout.getClass().getSimpleName(),
                this.reportLevel.name(), this.messages);
    }
}
