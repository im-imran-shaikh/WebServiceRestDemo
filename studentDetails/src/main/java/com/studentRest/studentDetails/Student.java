package com.studentRest.studentDetails;

import java.util.List;

import com.dto.StudentDetails;

public interface Student
{
	public List<StudentDetails> getAllStudent();
	public void addStudent(StudentDetails studentDetails);
}
