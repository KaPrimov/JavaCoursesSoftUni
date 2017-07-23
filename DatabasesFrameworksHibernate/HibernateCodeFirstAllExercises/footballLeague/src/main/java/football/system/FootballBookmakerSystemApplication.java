package football.system;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FootballBookmakerSystemApplication {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
		EntityManager em = emf.createEntityManager();
	}
}
