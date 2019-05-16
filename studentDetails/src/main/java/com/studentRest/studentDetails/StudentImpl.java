package com.studentRest.studentDetails;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.DatabaseConnection;
import com.dto.StudentDetail;

@Path("/student")
public class StudentImpl implements Student
{
	DatabaseConnection databaseConnection = new DatabaseConnection();

	@GET
	@Path("/getAllStudent")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public List<StudentDetail> getAllStudent()
	{
		return databaseConnection.getAllStudent();
	}

	@POST
	@Path("/addStudent")
	@Consumes(MediaType.APPLICATION_XML)
	@Override
	public void addStudent(StudentDetail studentDetails)
	{
		String studentName = studentDetails.getStudentName();
		String studentAddress = studentDetails.getStudentAddress();
		databaseConnection.addStudent(studentName, studentAddress);	
	}
	
	

	

	
	
}
