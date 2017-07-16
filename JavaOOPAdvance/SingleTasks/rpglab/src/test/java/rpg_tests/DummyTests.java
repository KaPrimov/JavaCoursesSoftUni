package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Dummy;
import rpg_lab.Weapon;

import java.util.ArrayList;

public class DummyTests {

    private static final int DUMMY_HEALTH = 10;
    private static final int DUMMY_XP = 10;
    private static final int ATTACK_POINTS = 5;
    private static final int KILL_DUMMY_ATTACK_POINTS = 20;

    private Dummy dummy;

    @Before
    public void initializeTestObjects() {
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP, new ArrayList<Weapon>());
    }


    @Test
    public void dummyLoosesHealthWhenIsAttacked() {
        this.dummy.takeAttack(ATTACK_POINTS);
        Assert.assertEquals("Wrong health points",DUMMY_HEALTH - ATTACK_POINTS, this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void dummyThrowsExceptionWhenIsAttackedAndIsDead()  {
        this.dummy.takeAttack(ATTACK_POINTS);
        this.dummy.takeAttack(ATTACK_POINTS);
        this.dummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void deadDummyGivesXP() {
        this.dummy.takeAttack(KILL_DUMMY_ATTACK_POINTS);
        Assert.assertEquals("Wrong XP points", DUMMY_XP, dummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyDoesNotGiveXP() {
        this.dummy.takeAttack(ATTACK_POINTS);
        this.dummy.giveExperience();
    }

}
