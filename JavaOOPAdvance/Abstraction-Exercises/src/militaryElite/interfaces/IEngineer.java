package militaryElite.interfaces;

import java.util.Set;

public interface IEngineer {

    Set<IRepair> getRepairs();

    void addRepair(IRepair repair);
}
