package weddingplanner.weddingplanner.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import weddingplanner.weddingplanner.controllers.*;

@Component
public class Terminal implements CommandLineRunner {

    private final AgencyController agencyController;
    private final PeopleController peopleController;
    private final WeddingController weddingController;
    private final VenuesController venuesController;
    private final PresentsController presentsController;

    @Autowired
    public Terminal(AgencyController agencyController, PeopleController peopleController, WeddingController weddingController, VenuesController venuesController, PresentsController presentsController) {
        this.agencyController = agencyController;
        this.peopleController = peopleController;
        this.weddingController = weddingController;
        this.venuesController = venuesController;
        this.presentsController = presentsController;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.agencyController.importAgencies();
//        this.peopleController.addPeople();
//        this.weddingController.saveWeddings();
//        this.venuesController.saveVenues();
//        this.weddingController.addVenuesToWeddings();
//        this.presentsController.savePresents();
//        this.agencyController.saveOrderedAgencies();
//        this.weddingController.findGuestsToWeddings();
//        this.venuesController.findWeddingsInSofia();
        this.agencyController.saveAllAgenciesByTown();
    }
}
