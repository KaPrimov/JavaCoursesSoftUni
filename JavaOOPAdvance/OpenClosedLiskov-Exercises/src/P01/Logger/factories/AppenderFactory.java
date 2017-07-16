package P01.Logger.factories;

import P01.Logger.interfaces.Appender;
import P01.Logger.interfaces.Layout;
import P01.Logger.models.appenders.AppenderImpl;
import P01.Logger.models.layouts.SimpleLayout;

import java.lang.reflect.Constructor;

public class AppenderFactory {

    public Appender returnObject(Layout layout, String name) {
        String fullName = AppenderImpl.class.getName();
        int index = fullName.lastIndexOf('.') + 1;
        String packageName = fullName.substring(0, index);
        String layoutName = packageName + name;
        Appender appenderInstance = null;
        try {
            Class<Appender> commandClass = (Class<Appender>) Class.forName(layoutName);
            Constructor ctor = commandClass.getConstructor(Layout.class);
            appenderInstance = (Appender) ctor.newInstance(layout);

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return appenderInstance;
    }

}
