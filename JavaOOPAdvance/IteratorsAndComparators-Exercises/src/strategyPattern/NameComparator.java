package strategyPattern;

import java.util.Comparator;

/**
 * Created by Kalin on 3/22/2017.
 */
public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        int index = Integer.compare(o1.getName().length(), o2.getName().length());
        if (index == 0) {
            char firstPersonLetter = Character.toLowerCase(o1.getName().charAt(0));
            char secondPersonLetter = Character.toLowerCase(o2.getName().charAt(0));
            index = Character.compare(firstPersonLetter, secondPersonLetter);
        }
        return index;
    }
}
