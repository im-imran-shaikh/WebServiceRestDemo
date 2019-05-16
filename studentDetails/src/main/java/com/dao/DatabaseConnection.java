package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentDetails;

public class DatabaseConnection
{
	private String databaseUserName = "root";
	private String databasePassword = "3diadmin";
	private String databaseURL = "jdbc:mysql://localhost:3306/student";
	private String databaseDriverName = "com.mysql.cj.jdbc.Driver";
	private Connection connection;
	private PreparedStatement statement;
	
	private String studentSQL = "select * from studentdetails";
	private String addStudent = "insert into studentdetails(Student_Name,Student_Address) values(?,?)";
	
	StudentDetails studentDetails = new StudentDetails();
	
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<StudentDetails> getAllStudent()
	{
		int rollNo;
		String studentName;
		String studentAddress;
		
		List<StudentDetails> studentDetailsList = new ArrayList<>();
		
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
	
	public static void main(String args[])
	{
		DatabaseConnection connection = new DatabaseConnection();
		connection.getAllStudent();
	}
}
