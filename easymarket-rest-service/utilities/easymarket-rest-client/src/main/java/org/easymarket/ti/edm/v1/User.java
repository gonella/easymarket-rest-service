package org.easymarket.ti.edm.v1;

public class User {

	private String id;
	private String name;

	public User() {

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
	
	public String toStrig(){
		return "User ["+getId()+" Name :"+getName()+"]";
	}
}