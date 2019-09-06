package com.bpom.app.models.areas;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GArea{

	@SerializedName("b_area")
	private String bArea;

	@SerializedName("b_delete_date")
	private Object bDeleteDate;

	@SerializedName("b_result")
	private Object bResult;

	@SerializedName("b_start")
	private String bStart;

	@SerializedName("b_target")
	private int bTarget;

	@SerializedName("b_date")
	private String bDate;

	@SerializedName("b_user_id")
	private int bUserId;

	@SerializedName("b_area_level")
	private int bAreaLevel;

	@SerializedName("b_id")
	private int bId;

	@SerializedName("b_description")
	private Object bDescription;

	@SerializedName("b_finish")
	private String bFinish;

	@SerializedName("b_client")
	private int bClient;

	@SerializedName("b_status")
	private int bStatus;

	@SerializedName("b_is_delete")
	private Object bIsDelete;

	public void setBArea(String bArea){
		this.bArea = bArea;
	}

	public String getBArea(){
		return bArea;
	}

	public void setBDeleteDate(Object bDeleteDate){
		this.bDeleteDate = bDeleteDate;
	}

	public Object getBDeleteDate(){
		return bDeleteDate;
	}

	public void setBResult(Object bResult){
		this.bResult = bResult;
	}

	public Object getBResult(){
		return bResult;
	}

	public void setBStart(String bStart){
		this.bStart = bStart;
	}

	public String getBStart(){
		return bStart;
	}

	public void setBTarget(int bTarget){
		this.bTarget = bTarget;
	}

	public int getBTarget(){
		return bTarget;
	}

	public void setBDate(String bDate){
		this.bDate = bDate;
	}

	public String getBDate(){
		return bDate;
	}

	public void setBUserId(int bUserId){
		this.bUserId = bUserId;
	}

	public int getBUserId(){
		return bUserId;
	}

	public void setBAreaLevel(int bAreaLevel){
		this.bAreaLevel = bAreaLevel;
	}

	public int getBAreaLevel(){
		return bAreaLevel;
	}

	public void setBId(int bId){
		this.bId = bId;
	}

	public int getBId(){
		return bId;
	}

	public void setBDescription(Object bDescription){
		this.bDescription = bDescription;
	}

	public Object getBDescription(){
		return bDescription;
	}

	public void setBFinish(String bFinish){
		this.bFinish = bFinish;
	}

	public String getBFinish(){
		return bFinish;
	}

	public void setBClient(int bClient){
		this.bClient = bClient;
	}

	public int getBClient(){
		return bClient;
	}

	public void setBStatus(int bStatus){
		this.bStatus = bStatus;
	}

	public int getBStatus(){
		return bStatus;
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
			"GArea{" + 
			"b_area = '" + bArea + '\'' + 
			",b_delete_date = '" + bDeleteDate + '\'' + 
			",b_result = '" + bResult + '\'' + 
			",b_start = '" + bStart + '\'' + 
			",b_target = '" + bTarget + '\'' + 
			",b_date = '" + bDate + '\'' + 
			",b_user_id = '" + bUserId + '\'' + 
			",b_area_level = '" + bAreaLevel + '\'' + 
			",b_id = '" + bId + '\'' + 
			",b_description = '" + bDescription + '\'' + 
			",b_finish = '" + bFinish + '\'' + 
			",b_client = '" + bClient + '\'' + 
			",b_status = '" + bStatus + '\'' + 
			",b_is_delete = '" + bIsDelete + '\'' + 
			"}";
		}
}