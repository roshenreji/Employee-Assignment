package com.mindtree.EmployeeAssignment.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mindtree.EmployeeAssignment.DAO.EmployeeDao;
import com.mindtree.EmployeeAssignment.Exceptions.IdAbsentException;
import com.mindtree.EmployeeAssignment.Exceptions.IdPresentException;

public class EmpolyeeDaoImpl implements EmployeeDao {

	Scanner sc = new Scanner(System.in);

	public void checkIdPresenceOfMinds(int id, Connection con) throws IdPresentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from CampusMind Where Id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;
				}
			}

			if (valid == true) {
				throw new IdPresentException("Already Present");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void checkIdPresenceOfLeads(int id, Connection con) throws IdPresentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from Leads Where Id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;
				}
			}

			if (valid == true) {
				throw new IdPresentException("Already Present");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void checkIdAbsenceLeads(int id, Connection con) throws IdAbsentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from Leads Where Id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;

				}
			}

			if (valid == false) {
				throw new IdAbsentException("Entry Not Found");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void checkIdAbsenceMinds(int id, Connection con) throws IdAbsentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from CampusMind Where Id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;

				}
			}

			if (valid == false) {
				throw new IdAbsentException("Entry Not Found");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void addCampusMind(Connection con) {

		String query = "Insert into CampusMind values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("Enter the id of the mind: ");
			int id = sc.nextInt();
			try {
				checkIdPresenceOfMinds(id, con);
				System.out.println("Enter Name of the mind: ");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.println("Enter Phone Number of the Mind");
				String phoneNumber = sc.next();
				System.out.println("Enter the Track of the Mind: ");
				String track = sc.next();

				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, phoneNumber);
				ps.setString(4, track);

				ps.executeUpdate();
			} catch (IdPresentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addLead(Connection con) {

		String query = "Insert into Leads values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("Enter the id of the Lead: ");
			int id = sc.nextInt();
			try {
				checkIdPresenceOfLeads(id, con);
				System.out.println("Enter Name of the Lead: ");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.println("Enter Phone Number of the Lead");
				String phoneNumber = sc.next();
				System.out.println("Enter the Track of the Lead: ");
				String track = sc.next();

				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, phoneNumber);
				ps.setString(4, track);

				ps.executeUpdate();
			} catch (IdPresentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String validateRole(String role) {
		boolean isValid = true;

		while (isValid) {
			if (role.equalsIgnoreCase("Campus Mind") || role.equalsIgnoreCase("Lead")) {
				isValid = false;

			} else {
				System.out.println("Enter the role properly: ");
				role = sc.nextLine();
			}
		}

		return role;

	}

	public void addEmployee(Connection con) {
		System.out.println("Enter the role of the Employee: ");
		String role = sc.nextLine();
		sc.nextLine();
		role = validateRole(role);
		if (role.equals("Campus Mind")) {
			addCampusMind(con);
		} else {
			addLead(con);
		}

	}

	public void deleteCampusMind(int id, Connection con) {
		try {
			checkIdAbsenceMinds(id, con);
			try {

				String query = "DELETE from CampusMind Where Id=" + id;
				PreparedStatement ps = con.prepareStatement(query);

				ps.executeUpdate();

				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		} catch (IdAbsentException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
	}

	public void deleteLead(int id, Connection con) {
		try {
			checkIdAbsenceLeads(id, con);
			try {

				String query = "DELETE from Leads Where Id=" + id;
				PreparedStatement ps = con.prepareStatement(query);

				ps.executeUpdate();

				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		} catch (IdAbsentException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
	}

	public void deleteEmployee(int id, Connection con) {
		System.out.println("Enter the role of the Employee whose item you want to delete: ");
		String role = sc.nextLine();
		role = validateRole(role);
		if (role.equals("Campus Mind")) {
			deleteCampusMind(id, con);
		} else {
			deleteLead(id, con);
		}

	}

	public void updatePhoneNumberOfMind(String phoneNumber, int id, Connection con) {
		try {
			checkIdAbsenceMinds(id, con);
			try {

				String query = "Update CampusMind Set phoneNumber=" + phoneNumber + " Where Id=" + id;
				PreparedStatement ps = con.prepareStatement(query);

				ps.executeUpdate();

				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		} catch (IdAbsentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void updatePhoneNumberOfLead(String phoneNumber, int id, Connection con) {
		try {
			checkIdAbsenceLeads(id, con);
			try {

				String query = "Update Leads Set phoneNumber=" + phoneNumber + " Where Id=" + id;
				PreparedStatement ps = con.prepareStatement(query);

				ps.executeUpdate();

				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		} catch (IdAbsentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void updatePhoneNumber(int id, String phoneNumber, Connection con) {
		System.out.println("Enter the role of the Employee whose item you want to update: ");
		String role = sc.nextLine();
		role = validateRole(role);
		if (role.equals("Campus Mind")) {
			updatePhoneNumberOfMind(phoneNumber, id, con);
		} else {
			updatePhoneNumberOfLead(phoneNumber, id, con);
		}

	}

	public void getListOfCampusMinds(Connection con) {
		try {

			int count = 1;

			String query = "Select id,name,phoneNumber,track from CampusMind";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				System.out.println();
				System.out.println(count + "\t Mind Id: " + rs.getInt(1));
				System.out.println("\t  Name of the Campus Mind: " + rs.getString(2));
				System.out.println("\t Phone Number: " + rs.getString(3));
				System.out.println("\t Track: " + rs.getString(4));

				count++;
			}

			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void getListOfLeads(Connection con) {
		try {

			int count = 1;

			String query = "Select id,name,phoneNumber,track from Leads";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				System.out.println();
				System.out.println(count + "\t Lead Id: " + rs.getInt(1));
				System.out.println("\t  Name of the Lead: " + rs.getString(2));
				System.out.println("\t Phone Number: " + rs.getString(3));
				System.out.println("\t Track: " + rs.getString(4));

				count++;
			}

			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void getListOfAllEmployees(Connection con) {
		System.out.println("Enter the role of the Employee whose item you want to update: ");
		String role = sc.nextLine();
		role = validateRole(role);
		if (role.equals("Campus Mind")) {
			getListOfCampusMinds(con);
		} else {
			getListOfLeads(con);
		}
	}

	public void getListOfEmployeeInSpecificTrack(String track, Connection con) {
		try {

			int count = 1;

			String query = "Select Leads.id ,Leads.name , CampusMind.id ,CampusMind.name from CampusMind inner join Leads on CampusMind.track=Leads.track";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs != null) {
				System.out.println("Sl.No \t Lead ID \t Lead Name \t CampusMind ID \t CampusMind Name");
				while (rs.next()) {
					System.out.println();
					System.out.println(count + "\t " + rs.getInt(1) + "\t\t  " + rs.getString(2) + "\t\t "
							+ rs.getInt(3) + "\t\t " + rs.getString(4));
					/*
					 * System.out.println("\t  " + rs.getString(2)); System.out.println("\t " +
					 * rs.getInt(3)); System.out.println("\t " + rs.getString(4));
					 */

					count++;
				}
			}

			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void countOfMindsInEachTrack(Connection con) {
		try {

			int count = 1;

			int counter = 0;
			String tracks = "";
			String query = "Select track,count(id) from CampusMind group by track";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				tracks = rs.getString(1);
				counter = rs.getInt(2);

				System.out.println();
				System.out.println(count + "\t {" + tracks + ":" + counter + "}");

				count++;
			}

			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
