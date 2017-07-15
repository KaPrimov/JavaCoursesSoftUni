import connections.Connector;
import entities.User;
import managers.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        try {
            Connector.initConnection("mysql", "root", "1234", "localhost", "3306", "mini_orm", "createDatabaseIfNotExist=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connection connection = Connector.getConnection();
        EntityManager entityManager = new EntityManager(connection);
        User user = new User("Kalin", 25, LocalDate.now());
        User user1 = new User("Pesho", 25, LocalDate.now());

        try {
            //entityManager.persist(user);
            //entityManager.persist(user1);
            System.out.println(entityManager.findFirst(User.class, "name = 'Kalin'").getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
