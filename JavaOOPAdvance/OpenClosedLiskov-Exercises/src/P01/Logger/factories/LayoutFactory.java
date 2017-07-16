package P01.Logger.factories;

import P01.Logger.interfaces.Facotry;
import P01.Logger.interfaces.Layout;
import P01.Logger.models.layouts.SimpleLayout;

import java.lang.reflect.Constructor;

public class LayoutFactory implements Facotry {

    public Layout returnObject(String name) {
        String fullName = SimpleLayout.class.getName();
        int index = fullName.lastIndexOf('.') + 1;
        String packageName = fullName.substring(0, index);
        String layoutName = packageName + name;
        Layout layoutInstance = null;
        try {
            Class<Layout> commandClass = (Class<Layout>) Class.forName(layoutName);
            Constructor ctor = commandClass.getConstructor();
            layoutInstance = (Layout) ctor.newInstance();

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return layoutInstance;
    }

}
