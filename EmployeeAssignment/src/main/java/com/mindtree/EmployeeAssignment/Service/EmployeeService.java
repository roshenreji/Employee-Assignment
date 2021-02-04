package com.mindtree.EmployeeAssignment.Service;

import java.sql.Connection;

public interface EmployeeService {

	public void addEmployee(Connection con);
	public void deleteEmployee(int id,Connection con);
	public void updatePhoneNumber(int id,String phoneNumber,Connection con);
	public void getListOfAllEmployees(Connection con);
	public void getListOfEmployeeInSpecificTrack(String track,Connection con);
	public void countOfMindsInEachTrack(Connection con);
}
