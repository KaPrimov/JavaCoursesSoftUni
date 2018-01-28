package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javache.io.Reader;
import javache.models.User;
import javache.models.dtos.UserDTO;

public final class UserRepositoryImpl {
	
	private static final String DB_PATH = System.getProperty("user.dir") + "\\src\\resources\\db\\users.txt";

	private UserRepositoryImpl() {}
	
	public static boolean save(User user) throws IOException {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(DB_PATH, true))) {
			User existingUser = findUserDataByEmail(user.getName());
			if (existingUser != null) {
				return false;
			}
			
			writer.write(UUID.randomUUID().toString() + "|" + user.getName() + "|" + user.getPassword() + System.lineSeparator());
			return true;
		}			
	}
	

	public static User getUserById(String id) throws IOException {
		return findUserDataById(id);
	}
	
	public static User getUserByEmail(String email) throws IOException {
		return findUserDataByEmail(email);
	}

	public static Set<UserDTO> getAllUsers() throws IOException {
		String content = Reader.readAllLines(new FileInputStream(DB_PATH));
		Set<UserDTO> users = new HashSet<>();
		String[] usersData = content.split("\\r\\n");
		for (String userData : usersData) {			
			users.add(new UserDTO(userData.split("\\|")[1]));
		}
		return users;
	}
	
	public static User findUserDataByEmail(String email) throws IOException {
		String dbPath = DB_PATH;
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(dbPath))) {
    		String line = "";
    		while((line = reader.readLine()) != null) {
    			String[] userData = line.split("\\|");
				if (userData[1].equals(email)) {
					return new User(userData[0], userData[1], userData[2]); 
				}
    		}
    	} 
    	return null;
    }
    
    public static User findUserDataById(String id) throws IOException {
		String dbPath = DB_PATH;
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(dbPath))) {
    		String line = "";
    		while((line = reader.readLine()) != null) {
    			String[] userData = line.split("\\|");
				if (userData[0].equals(id)) {
					return new User(userData[0], userData[1], userData[2]); 
				}
    		}
    	} 
    	return null;
    }
}
