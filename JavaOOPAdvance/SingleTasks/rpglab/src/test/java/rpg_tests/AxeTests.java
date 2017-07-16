package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;
import rpg_lab.Weapon;

import java.util.ArrayList;

public class AxeTests {

    public static final int AXE_ATTACK = 10;
    public static final int AXE_DURABILITY = 10;
    public static final int EXPECTED_DURABILITY = 9;
    public static final int DUMMY_HEALTH = 10;
    public static final int DUMMY_XP = 10;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP, new ArrayList<Weapon>());
    }

    @Test
    public void weaponLosesDurabilityAfterAttack() {
        this.axe.attack(dummy);
        Assert.assertEquals("Wrong durability",EXPECTED_DURABILITY, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void attackWithBrokenAxe() {
        this.axe.attack(dummy);
        this.axe.attack(dummy);
    }
}
