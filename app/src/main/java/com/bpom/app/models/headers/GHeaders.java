package com.bpom.app.models.headers;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GHeaders{

	@SerializedName("b_survey_name")
	private Object bSurveyName;

	@SerializedName("b_name")
	private String bName;

	@SerializedName("b_users_id")
	private int bUsersId;

	@SerializedName("b_id")
	private int bId;

	@SerializedName("b_description")
	private String bDescription;

	@SerializedName("b_survey_id")
	private int bSurveyId;

	@SerializedName("b_users")
	private String bUsers;

	@SerializedName("b_status")
	private int bStatus;

	@SerializedName("b_date")
	private Object bDate;

	@SerializedName("b_is_delete")
	private Object bIsDelete;

	public void setBSurveyName(Object bSurveyName){
		this.bSurveyName = bSurveyName;
	}

	public Object getBSurveyName(){
		return bSurveyName;
	}

	public void setBName(String bName){
		this.bName = bName;
	}

	public String getBName(){
		return bName;
	}

	public void setBUsersId(int bUsersId){
		this.bUsersId = bUsersId;
	}

	public int getBUsersId(){
		return bUsersId;
	}

	public void setBId(int bId){
		this.bId = bId;
	}

	public int getBId(){
		return bId;
	}

	public void setBDescription(String bDescription){
		this.bDescription = bDescription;
	}

	public String getBDescription(){
		return bDescription;
	}

	public void setBSurveyId(int bSurveyId){
		this.bSurveyId = bSurveyId;
	}

	public int getBSurveyId(){
		return bSurveyId;
	}

	public void setBUsers(String bUsers){
		this.bUsers = bUsers;
	}

	public String getBUsers(){
		return bUsers;
	}

	public void setBStatus(int bStatus){
		this.bStatus = bStatus;
	}

	public int getBStatus(){
		return bStatus;
	}

	public void setBDate(Object bDate){
		this.bDate = bDate;
	}

	public Object getBDate(){
		return bDate;
	}

	public void setBIsDelete(Object bIsDelete){
		this.bIsDelete = bIsDelete;
	}

	public Object getBIsDelete(){
		return bIsDelete;
	}

	@Override
 	public String toString(){
		return 
			"GHeaders{" + 
			"b_survey_name = '" + bSurveyName + '\'' + 
			",b_name = '" + bName + '\'' + 
			",b_users_id = '" + bUsersId + '\'' + 
			",b_id = '" + bId + '\'' + 
			",b_description = '" + bDescription + '\'' + 
			",b_survey_id = '" + bSurveyId + '\'' + 
			",b_users = '" + bUsers + '\'' + 
			",b_status = '" + bStatus + '\'' + 
			",b_date = '" + bDate + '\'' + 
			",b_is_delete = '" + bIsDelete + '\'' + 
			"}";
		}
}