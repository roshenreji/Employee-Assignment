package com.mindtree.EmployeeAssignment.Service.Impl;

import java.sql.Connection;

import com.mindtree.EmployeeAssignment.DAO.EmployeeDao;
import com.mindtree.EmployeeAssignment.DAO.Impl.EmpolyeeDaoImpl;
import com.mindtree.EmployeeAssignment.Service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao dao=new EmpolyeeDaoImpl();
	public void addEmployee(Connection con) {
		dao.addEmployee(con);

	}

	public void deleteEmployee(int id, Connection con) {
		dao.deleteEmployee(id, con);

	}

	public void updatePhoneNumber(int id, String phoneNumber, Connection con) {
		dao.updatePhoneNumber(id, phoneNumber, con);

	}

	public void getListOfAllEmployees(Connection con) {
		dao.getListOfAllEmployees(con);

	}

	public void getListOfEmployeeInSpecificTrack(String track, Connection con) {
		dao.getListOfEmployeeInSpecificTrack(track, con);

	}

	public void countOfMindsInEachTrack(Connection con) {
		dao.countOfMindsInEachTrack(con);
		
	}
	

}
