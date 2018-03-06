package com.softuni.carDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softuni.carDealer.entities.Log;

@Repository
public interface LoggerRepository extends JpaRepository<Log, Long>{
	
	@Query("SELECT l FROM Log AS l "
			+ "WHERE l.user.username = :username")
	List<Log> findAllForUsername(@Param("username") String username);
	
}
