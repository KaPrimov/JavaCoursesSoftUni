package com.softuni.carDealer.services.impls;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.carDealer.dtos.view.LogDTO;
import com.softuni.carDealer.entities.Log;
import com.softuni.carDealer.entities.OperationEnum;
import com.softuni.carDealer.entities.User;
import com.softuni.carDealer.repositories.LoggerRepository;
import com.softuni.carDealer.services.apis.LogService;
import com.softuni.carDealer.utils.ModelParser;

@Service
public class LogServiceImpl implements LogService {

	private final LoggerRepository loggerRepository;
	
	@Autowired	
	public LogServiceImpl(LoggerRepository loggerRepository) {
		super();
		this.loggerRepository = loggerRepository;
	}

	@Override
	public void log(OperationEnum operation, User user, String tableName) {
		Log log = new Log(user, operation, tableName, new Date());
		this.loggerRepository.save(log);
	}

	@Override
	public Set<LogDTO> allLogs() {
		List<Log> allLogs = this.loggerRepository.findAll();
		Set<LogDTO> allLogsDTO = convertEnititesToDTOs(allLogs);
		return allLogsDTO;
	}

	@Override
	public Set<LogDTO> queryLogsByUsername(String text) {
		List<Log> allLogs = this.loggerRepository.findAllForUsername(text);
		Set<LogDTO> allLogsDTO = convertEnititesToDTOs(allLogs);
		return allLogsDTO;
	}

	private Set<LogDTO> convertEnititesToDTOs(List<Log> allLogs) {
		Set<LogDTO> allLogsDTO = new LinkedHashSet<>();
		
		for (Log log : allLogs) {
			allLogsDTO.add(ModelParser.getInstance().map(log, LogDTO.class));
		}
		return allLogsDTO;
	}

	@Override
	public void deleteAll() {
		this.loggerRepository.deleteAll();
	}

}
