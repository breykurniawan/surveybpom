package com.bpom.app.models.login;

public class Data{
	private int bId;
	private String bUserName;
	private String bFullName;
	private String bEmail;

	public void setBId(int bId){
		this.bId = bId;
	}

	public int getBId(){
		return bId;
	}

	public void setBUserName(String bUserName){
		this.bUserName = bUserName;
	}

	public String getBUserName(){
		return bUserName;
	}

	public void setBFullName(String bFullName){
		this.bFullName = bFullName;
	}

	public String getBFullName(){
		return bFullName;
	}

	public void setBEmail(String bEmail){
		this.bEmail = bEmail;
	}

	public String getBEmail(){
		return bEmail;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"b_id = '" + bId + '\'' + 
			",b_user_name = '" + bUserName + '\'' + 
			",b_full_name = '" + bFullName + '\'' + 
			",b_email = '" + bEmail + '\'' + 
			"}";
		}
}
