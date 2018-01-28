package org.softuni.main.database.repositories;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.softuni.main.database.models.User;

public abstract class BaseRepository implements Repository {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Casebook");
	protected EntityManager em;
	
	private Map<String, Method> dbHandlers;
	
	protected BaseRepository() {
		this.em = ENTITY_MANAGER_FACTORY.createEntityManager();
		this.initializeMethods();
	}

	private void initializeMethods() {
		this.dbHandlers = new HashMap<>();
		
		for (Method method : this.getClass().getDeclaredMethods()) {
			method.setAccessible(true);
			this.dbHandlers.putIfAbsent(method.getName(), method);
		}
		
	}
	
	public Object doAction(String action, Object... args ) {
		EntityTransaction transaction = null;
		
		Object result = null;
		
		try {
			transaction = this.em.getTransaction();
			transaction.begin();
			
			result = this.dbHandlers.get(action).invoke(this, args);
			transaction.commit();
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public void dismiss() {
		this.em.close();
		ENTITY_MANAGER_FACTORY.close();
	}
}
