import commands.*;
import groups.AttackGroup;
import groups.Group;
import loggers.CombatLogger;
import loggers.ErrorLogger;
import loggers.EventLogger;
import loggers.Logger;
import models.Attacker;
import models.Dragon;
import models.SubjectTarget;
import models.Warrior;

public class Main {
    public static void main(String[] args) {
        Logger combatLog = new CombatLogger();
        Logger eventLog = new EventLogger();
        Logger errorLogger = new ErrorLogger();

        combatLog.setSuccessor(eventLog);
        eventLog.setSuccessor(errorLogger);

        Attacker warrior = new Warrior("Pesho", 2, combatLog);
        Attacker warrior1 = new Warrior("Strahil", 2, combatLog);
        Attacker warrior2 = new Warrior("Toto", 2, combatLog);
        SubjectTarget dragon = new Dragon("Gosho", 3, 10);

        Executor commandExecutor = new CommandExecutor();
        AttackGroup attackGroup = new Group();
        attackGroup.addMember(warrior);
        attackGroup.addMember(warrior1);
        attackGroup.addMember(warrior2);
        Command groupTargetCommand = new GroupTargetCommand(attackGroup, dragon);
        commandExecutor.executeCommand(groupTargetCommand);
        Command groupAttackCommand = new GroupAttackCommand(attackGroup);
        commandExecutor.executeCommand(groupAttackCommand);

    }
}
