package database;

import contracts.Repository;
import models.boats.Boat;
import models.engines.BoatEngineImpl;

public class BoatSimulatorDatabase {

    private static BoatSimulatorDatabase INSTANCE = null;

    Repository<Boat> boats;
    Repository<BoatEngineImpl> engines;

    public static BoatSimulatorDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoatSimulatorDatabase();
        }
        return INSTANCE;
    }


    private BoatSimulatorDatabase()
    {
        this.boats = new RepositoryImpl<>();
        this.engines = new RepositoryImpl<>();
    }

    public Repository<Boat> getBoats() {
        return this.boats;
    }

    public Repository<BoatEngineImpl> getEngines() {
        return this.engines;
    }

    }
