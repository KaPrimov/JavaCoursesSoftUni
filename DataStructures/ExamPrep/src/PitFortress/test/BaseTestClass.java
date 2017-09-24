package PitFortress.test;

import PitFortress.main.PitFortressCollection;
import PitFortress.main.interfaces.IPitFortress;
import org.junit.Before;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BaseTestClass {

    protected IPitFortress PitFortressCollection;

    @Before
    public void init() {
        this.PitFortressCollection = new PitFortressCollection();
    }

    public <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
