package com.studentRest.studentDetails;

import java.util.List;

import com.dto.StudentDetail;

public interface Student
{
	public List<StudentDetail> getAllStudent();
	public void addStudent(StudentDetail studentDetails);
}
