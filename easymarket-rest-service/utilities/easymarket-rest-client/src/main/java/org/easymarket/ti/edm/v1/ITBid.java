package org.easymarket.ti.edm.v1;

public class ITBid {

	private String id;
	private String name;

	public ITBid() {

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
		return "Bid ["+getId()+" Name :"+getName()+"]";
	}
}
