package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentDetail;
import com.dto.StudentMarksheet;

public class DatabaseConnection
{
	private String databaseUserName = "root";
	private String databasePassword = "3diadmin";
	private String databaseURL = "jdbc:mysql://localhost:3306/student";
	private String databaseDriverName = "com.mysql.cj.jdbc.Driver";
	private Connection connection;
	private PreparedStatement statement;
	
	private String studentSQL = "select * from studentdetails";
	private String studentMarksheet = "select * from studentmarksheet";
	private String addStudent = "insert into studentdetails(Student_Name,Student_Address) values(?,?)";
	private String addStudentMarksheet = "insert into studentmarksheet(History,Geography,Math) values(?,?,?)";
	private String deleteStudent = "Delete  from studentdetails where Roll_No = ?";
	private String deleteStudentMarksheet = "Delete from studentmarksheet where Roll_No = ?";
	private String updateStudent = "update studentDetails set Student_Name = ? , Student_Address = ? where Roll_No = ?";
	
	private Connection databaseConnection()
	{
		try
		{
			Class.forName( databaseDriverName );
			connection = DriverManager.getConnection( databaseURL, databaseUserName, databasePassword );
			System.out.println("Database is connected");
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
	
	private void closeConnection()
	{
		if  ( connection != null )
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
	}
	
	private void closeStatement()
	{
		if ( statement != null )
			try
			{
				statement.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
	}
	
	public List<StudentDetail> getAllStudent()
	{
		int rollNo;
		String studentName;
		String studentAddress;
		
		List<StudentDetail> studentDetailsList = new ArrayList<>();
		
		try
		{	
			connection = databaseConnection();
			statement = connection.prepareStatement(studentSQL);
			ResultSet resultSet = statement.executeQuery();
			while ( resultSet.next() )
			{
				rollNo = resultSet.getInt("Roll_No");
				studentName = resultSet.getString("student_Name");
				studentAddress =  resultSet.getString("student_Address");
				StudentDetail studentDetails = new StudentDetail();
				
				studentDetails.setRollNo(rollNo);
				studentDetails.setStudentName(studentName);
				studentDetails.setStudentAddress(studentAddress);
				System.out.println(rollNo + " " + studentName + " " + studentAddress);
				
				studentDetailsList.add(studentDetails);
			}
			
			
		} catch (SQLException e)
		{
			System.out.println("Unable to create a statement " );
		}
		finally
		{
			closeStatement();
			closeConnection();
		}
		
		return studentDetailsList;
	}
	
	public List<StudentMarksheet> getStudentMarksheet()
	{
		int rollNo;
		int history;
		int geography;
		int math;
		
		List<StudentMarksheet> studentMarksheetList = new ArrayList<>();
		
		try
		{	
			connection = databaseConnection();
			statement = connection.prepareStatement(studentMarksheet);
			ResultSet resultSet = statement.executeQuery();
			while ( resultSet.next() )
			{
				rollNo = resultSet.getInt("Roll_No");
				history = resultSet.getInt("history");
				geography =  resultSet.getInt("geography");
				math = resultSet.getInt("math");
				StudentMarksheet studentMarksheet = new StudentMarksheet();
				
				studentMarksheet.setRollNo(rollNo);
				studentMarksheet.setHistory(history);
				studentMarksheet.setGeography(geography);
				studentMarksheet.setMath(math);
				
				System.out.println(rollNo + " " + history + " " + geography + " " + math);
				
				studentMarksheetList.add(studentMarksheet);
			}
			
			
		} catch (SQLException e)
		{
			System.out.println("Unable to create a statement " );
		}
		finally
		{
			closeStatement();
			closeConnection();
		}
		
		return studentMarksheetList;
	}
	
	public void addStudent(String studentName ,String studentAddress)
	{
		connection = databaseConnection();
		try
		{
			statement = connection.prepareStatement(addStudent);
			statement.setString(1,studentName);
			statement.setString(2,studentAddress);
			statement.executeUpdate();
			System.out.print("student is added");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatement();
			closeConnection();
		}	
	}
	
	public void addStudentMarksheet(int history ,int geography, int math)
	{
		connection = databaseConnection();
		try
		{
			statement = connection.prepareStatement(addStudentMarksheet);
			statement.setInt(1,history);
			statement.setInt(2,geography);
			statement.setInt(3, math);
			statement.executeUpdate();
			System.out.print("Marksheet is updated ");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatement();
			closeConnection();
		}	
	}
	
	public void deleteStudent(int rollNo)
	{
		connection = databaseConnection();
		try
		{
			statement = connection.prepareStatement(deleteStudent);
			statement.setInt(1, rollNo);
			statement.executeUpdate();
			System.out.println("Roll No " + rollNo + " is deleted");
			
			statement = connection.prepareStatement(deleteStudentMarksheet);
			statement.setInt(1, rollNo);
			statement.executeUpdate();
			System.out.println("student marksheet of roll no " + rollNo + " is deleted");
			
		} catch (SQLException e)
		{	
			e.printStackTrace();
		}
		
	}
	
	public void updateStudent(String studentName, String studentAddress,int rollNo)
	{
		connection = databaseConnection();
		try
		{
			statement = connection.prepareStatement(updateStudent);
			statement.setString(1, studentName);
			statement.setString(2, studentAddress);
			statement.setInt(3, rollNo);
			statement.executeUpdate();
			System.out.println(rollNo + " is updated");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatement();
			closeConnection();
		}
	}
}
