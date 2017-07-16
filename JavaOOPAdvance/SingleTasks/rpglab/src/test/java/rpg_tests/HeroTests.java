package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Hero;
import rpg_lab.RandomProvider;
import rpg_lab.Target;
import rpg_lab.Weapon;

public class HeroTests {

    private static final int TARGET_XP = 5;
    private static final String HERO_NAME = "Fake Hero";

    private Hero hero;
    private Weapon weapon;
    private Target target;
    private RandomProvider  randomProvider;

    @Before
    public void initializeObjects() {
        this.weapon = Mockito.mock(Weapon.class);
        this.hero = new Hero(HERO_NAME, weapon);
        this.target = Mockito.mock(Target.class);
        this.randomProvider = Mockito.mock(RandomProvider.class);
    }

    @Test
    public void heroGainsXPAfterAttackIfTargetDies() {

        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveExperience()).thenReturn(TARGET_XP);

        hero.attack(target, new RandomProvider.Implementation());
        Assert.assertEquals("Wrong XP given", TARGET_XP, hero.getExperience());
    }

    @Test
    public void heroGetsWeaponWhenTargetIsDead() {
        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.dropWeapon(this.randomProvider)).thenReturn(this.weapon);

        this.hero.attack(this.target, this.randomProvider);

        Assert.assertSame(this.weapon, this.hero.getInventory().iterator().next());
    }

}
