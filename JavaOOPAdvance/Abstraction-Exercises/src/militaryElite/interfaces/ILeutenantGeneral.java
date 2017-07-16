package militaryElite.interfaces;

import java.util.Set;

public interface ILeutenantGeneral {

    Set<ISoldier> getPrivates();

    void addPrivate(ISoldier iSoldier);
}
