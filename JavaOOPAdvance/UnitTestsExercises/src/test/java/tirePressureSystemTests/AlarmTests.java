package tirePressureSystemTests;

import TirePressureMonitoringSystem.Alarm;
import TirePressureMonitoringSystem.Sensor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AlarmTests {

    private static final double LOW_PRESSURE_BOUND = 16;
    private static final double HIGH_PRESSURE_BOUND = 22;
    private static final double NORMAL_PRESSURE_VALUE = 20;

    private Alarm alarm;
    private Sensor sensor;

    @Before
    public void initializeObjects() {
        this.sensor = Mockito.mock(Sensor.class);
        this.alarm = new Alarm(this.sensor);
    }

    @Test
    public void testAlarmIsOnWhenPressureIsLow() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_BOUND);
        this.alarm.check();
        Assert.assertEquals(true, this.alarm.getAlarmOn());
    }

    @Test
    public void testAlarmIsOnWhenPressureIsHigh() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_BOUND);
        this.alarm.check();
        Assert.assertEquals(true, this.alarm.getAlarmOn());
    }

    @Test
    public void testAlarmIsOffWhenPressureIsWithinLimits() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(NORMAL_PRESSURE_VALUE);
        this.alarm.check();
        Assert.assertEquals(false, this.alarm.getAlarmOn());
    }
}
