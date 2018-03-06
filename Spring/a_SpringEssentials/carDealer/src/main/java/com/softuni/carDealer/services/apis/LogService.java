package com.softuni.carDealer.services.apis;

import java.util.Set;

import com.softuni.carDealer.dtos.view.LogDTO;
import com.softuni.carDealer.entities.OperationEnum;
import com.softuni.carDealer.entities.User;

public interface LogService {
	void log(OperationEnum operation, User user, String tableName);
	
	Set<LogDTO> allLogs();

	Set<LogDTO> queryLogsByUsername(String text);

	void deleteAll();
}
