package com.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "StudentDetails")
public class StudentDetails
{
	private int rollNo;
	private String studentName;
	private String studentAddress;
	public int getRollNo()
	{
		return rollNo;
	}
	public void setRollNo(int rollNo)
	{
		this.rollNo = rollNo;
	}
	public String getStudentName()
	{
		return studentName;
	}
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	public String getStudentAddress()
	{
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress)
	{
		this.studentAddress = studentAddress;
	}
	
	@Override
	public String toString()
	{
		return "StudentDetails [rollNo=" + rollNo + ", studentName="
				+ studentName + ", studentAddress=" + studentAddress + "]";
	}
	
	
}
