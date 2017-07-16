package pr0304Barracks.core.factories;

import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

import java.lang.reflect.Constructor;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"pr0304Barracks.models.units.";

	@Override
	@SuppressWarnings("unchecked")
	public Unit createUnit(String unitType) {
		Unit unit = null;

		try {
            Class<Unit> unitReflection = (Class<Unit>) Class.forName(UNITS_PACKAGE_NAME + unitType);

            Constructor<Unit> constructor = unitReflection.getConstructor();

            unit = constructor.newInstance();


        } catch (ReflectiveOperationException e) {
		    e.printStackTrace();
        }

	    return unit;

	}
}
