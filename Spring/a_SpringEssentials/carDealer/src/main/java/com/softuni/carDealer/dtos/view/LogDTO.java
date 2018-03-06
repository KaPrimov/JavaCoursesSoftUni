package com.softuni.carDealer.dtos.view;

import java.util.Date;

import com.softuni.carDealer.entities.OperationEnum;
import com.softuni.carDealer.entities.User;

public class LogDTO {
	private UserDTO user;
	private OperationEnum operation;
	private String tableName;
	private Date date;
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public OperationEnum getOperation() {
		return operation;
	}
	public void setOperation(OperationEnum operation) {
		this.operation = operation;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
