package com.dto;

public class StudentMarksheet
{
	private int rollNo;
	private int history;
	private int geography;
	private int math;
	public int getRollNo()
	{
		return rollNo;
	}
	public void setRollNo(int rollNo)
	{
		this.rollNo = rollNo;
	}
	public int getHistory()
	{
		return history;
	}
	public void setHistory(int history)
	{
		this.history = history;
	}
	public int getGeography()
	{
		return geography;
	}
	public void setGeography(int geography)
	{
		this.geography = geography;
	}
	public int getMath()
	{
		return math;
	}
	public void setMath(int math)
	{
		this.math = math;
	}
	
	
	@Override
	public String toString()
	{
		return "StudentMarksheet [rollNo=" + rollNo + ", history=" + history
				+ ", geography=" + geography + ", math=" + math + "]";
	}
	
	
}
