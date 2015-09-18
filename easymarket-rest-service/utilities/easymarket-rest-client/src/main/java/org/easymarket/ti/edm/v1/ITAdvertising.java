package org.easymarket.ti.edm.v1;

public class ITAdvertising {

	private String id;
	private String name;

	public ITAdvertising() {

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
		return "Advertising ["+getId()+" Name :"+getName()+"]";
	}
}
