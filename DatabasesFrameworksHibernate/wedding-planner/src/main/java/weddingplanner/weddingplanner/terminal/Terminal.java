package weddingplanner.weddingplanner.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import weddingplanner.weddingplanner.controllers.AgencyController;
import weddingplanner.weddingplanner.controllers.PeopleController;
import weddingplanner.weddingplanner.controllers.VenuesController;
import weddingplanner.weddingplanner.controllers.WeddingController;

@Component
public class Terminal implements CommandLineRunner {

    private final AgencyController agencyController;
    private final PeopleController peopleController;
    private final WeddingController weddingController;
    private final VenuesController venuesController;

    @Autowired
    public Terminal(AgencyController agencyController, PeopleController peopleController, WeddingController weddingController, VenuesController venuesController) {
        this.agencyController = agencyController;
        this.peopleController = peopleController;
        this.weddingController = weddingController;
        this.venuesController = venuesController;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.agencyController.importAgencies();
//        this.peopleController.addPeople();
//        this.weddingController.saveWeddings();
//        this.venuesController.saveVenues();
        this.weddingController.addVenuesToWeddings();
    }
}
