package groups;

import models.Attacker;
import models.SubjectTarget;

public interface AttackGroup {

    void addMember(Attacker attacker);
    void groupTarget(SubjectTarget target);
    void groupAttack();

}
