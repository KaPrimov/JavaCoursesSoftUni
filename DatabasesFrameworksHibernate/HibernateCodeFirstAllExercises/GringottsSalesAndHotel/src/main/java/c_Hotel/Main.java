package c_Hotel;

import c_Hotel.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager em = emf.createEntityManager();

        //em.getTransaction().begin();

        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");
        employee.setTitle("manager");

        //em.persist(employee);

        Customer customer = new Customer();
        customer.setAccountNumber(123456789L);
        customer.setFirstName("Misho");
        customer.setLastName("Mishev");
        customer.setPhoneNumber("+359777888999");

        //em.persist(customer);

        RoomStatus roomStatus = new RoomStatus();
        roomStatus.setRoomStatus(10L);
        roomStatus.setNotes("Perfect");

        //em.persist(roomStatus);

        RoomType roomType = new RoomType();
        roomType.setType("2 beds");
        roomType.setNotes("With two single beds");

        //em.persist(roomType);

        BedType bedType = new BedType();
        bedType.setType("Single");

        //em.persist(bedType);

        Room room = new Room();
        room.setRoomNumber(303L);
        room.setBedType(bedType);
        room.setRoomStatus(roomStatus);
        room.setRoomType(roomType);
        room.setRate(5D);

        Room room2 = new Room();
        room2.setRoomNumber(304L);
        room2.setBedType(bedType);
        room2.setRoomStatus(roomStatus);
        room2.setRoomType(roomType);
        room2.setRate(5D);

        //em.persist(room);
        //em.persist(room2);

        Payment payment = new Payment();
        payment.setPaymentDate(new Date());
        payment.setAccountNumber(customer);
        payment.setTotalDays(5);
        payment.setFirstDateOccupied(new Date());

        //em.persist(payment);

        Occupancy occupancy = new Occupancy();
        occupancy.setDateOccupied(new Date());
        occupancy.setAccountNumber(customer);
        occupancy.setRoomNumber(room);
        occupancy.setPhoneCharge(25.1);

        //em.persist(occupancy);

        //em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
