package com.auth.server.model;

public class Employee {
	String id;
	String name;
	String dept;

	public Employee() {
	}

	public Employee(String id, String name, String dept) {
		this.id = id;
		this.name = name;
		this.dept = dept;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	@Override
	public String toString() {
		return "Emp ID : "+this.id+" Emp Name : "+this.name+" Emp Dept : "+this.dept;
	}
}
