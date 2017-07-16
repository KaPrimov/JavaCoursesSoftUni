package contracts;

import exeptions.DuplicateModelException;

import java.util.List;

public interface Race
{
    int getDistance();

    int getWindSpeed ();

    int getOceanCurrentSpeed();

    boolean getAllowsMotorboats ();

    void addParticipant(Raceable boat) throws DuplicateModelException;

    List<Raceable> getParticipants();
}
