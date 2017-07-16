package militaryElite.interfaces;

import java.util.Set;

/**
 * Created by kalin on 15.03.17.
 */
public interface ICommando {

    Set<IMission> getMissions();

    void addMission(IMission mission);

}
