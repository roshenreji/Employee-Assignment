package com.mindtree.EmployeeAssignment.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.mindtree.EmployeeAssignment.Service.EmployeeService;
import com.mindtree.EmployeeAssignment.Service.Impl.EmployeeServiceImpl;
import com.mindtree.EmployeeAssignment.Utility.JDBCConnection;

public class EmployeeApplication {

	Scanner sc = new Scanner(System.in);

	public void displayMessages() {
		System.out.println();
		System.out.println("These are the choices: ");
		System.out.println("1. Insert Data");
		System.out.println("2. Delete Data");
		System.out.println("3. Update Phone Number");
		System.out.println("4. Get List of all Employees");
		System.out.println("5. Get List of all Employees in Specific Track");
		System.out.println("6. Count of Employees in each track");
		System.out.println("7. Exit");
		System.out.println("Enter your choice: ");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean isValid = true;
		int firstTime = 1;
		JDBCConnection connect = new JDBCConnection();
		EmployeeService employees = new EmployeeServiceImpl();
		EmployeeApplication obj=new EmployeeApplication();
		Connection con = connect.getConnection();
		do {
			obj.displayMessages();
			int choice=obj.sc.nextInt();
			switch(choice) {
			case 1:
				employees.addEmployee(con);
				break;
				
			case 2:
				System.out.println("Enter id of the Employee you want to delete: ");
				int id=obj.sc.nextInt();
				employees.deleteEmployee(id, con);
				break;
			case 3:
				System.out.println("Enter id of the Employee you want to update: ");
				int idOfEmployee=obj.sc.nextInt();
				System.out.println("Enter the new phone Number of the Employee");
				String phoneNumber=obj.sc.next();
				employees.updatePhoneNumber(idOfEmployee, phoneNumber, con);
				break;
			case 4:
				employees.getListOfAllEmployees(con);
				break;
			case 5:
				System.out.println("Enter the track whose details you need to check: ");
				String track=obj.sc.next();
				employees.getListOfEmployeeInSpecificTrack(track, con);
				break;
				
			case 6:
				employees.countOfMindsInEachTrack(con);
				break;
			case 7:
				isValid=false;
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
				default:
					System.out.println("Invalid Option, Please try Again");
			}
		}
		while(isValid);
	}

}
