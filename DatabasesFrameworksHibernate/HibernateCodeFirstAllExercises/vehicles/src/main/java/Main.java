import enitities.motor.Car;
import enitities.motor.Train;
import enitities.nonMotor.Bike;
import enitities.railRoad.Locomotive;
import enitities.railRoad.carriages.Passenger;
import enitities.railRoad.carriages.Sleeping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Car car = new Car("Skoda", "Octavia", 12421,21, 1, "fst"
        , (byte) 101, (byte)4, "some");
        em.persist(car);

        Bike bike = new Bike("BMW", "M3", 123.12, 30,10, "blue");

        Train train = new Train();
        train.setManufacturer("Mercedes");

        Locomotive locomotive = new Locomotive();

        train.setLocomotive(locomotive);
        locomotive.setTrain(train);

        Passenger passenger = new Passenger(train, 121);
        Sleeping sleeping = new Sleeping(train, 213);

        train.setCarriages(new ArrayList<>(Arrays.asList(passenger, sleeping)));

        em.persist(bike);
        em.persist(train);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
