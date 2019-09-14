package com.bpom.app.models.login;

public class Data{
	private int b_id;
	private String b_user_name;
	private String b_full_name;
	private String b_email;

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public void setB_user_name(String b_user_name) {
		this.b_user_name = b_user_name;
	}

	public void setB_full_name(String b_full_name) {
		this.b_full_name = b_full_name;
	}

	public void setB_email(String b_email) {
		this.b_email = b_email;
	}


	public int getB_id() {
		return b_id;
	}

	public String getB_user_name() {
		return b_user_name;
	}

	public String getB_full_name() {
		return b_full_name;
	}

	public String getB_email() {
		return b_email;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"b_id = '" + b_id + '\'' +
			",b_user_name = '" + b_user_name + '\'' +
			",b_full_name = '" + b_full_name + '\'' +
			",b_email = '" + b_email + '\'' +
			"}";
	}

}
