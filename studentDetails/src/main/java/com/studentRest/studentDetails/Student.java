package com.studentRest.studentDetails;

import java.util.List;

import com.dto.StudentDetail;
import com.dto.StudentMarksheet;

public interface Student
{
	public List<StudentDetail> getAllStudent();
	public List<StudentMarksheet> getAllStudentMarsheet();
	public void addStudent(StudentDetail studentDetails);
	public void addStudentMarksheet(StudentMarksheet studentMarksheet);
	public void deleteStudent(StudentDetail studentDetail);
	public void updateStudent(StudentDetail studentDetail);
	
}
