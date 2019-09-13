package com.bpom.app.models.answers;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GAnswers{

	@SerializedName("b_id")
	private int bId;

	@SerializedName("b_question_id")
	private int bQuestionId;

	@SerializedName("b_delete_date")
	private Object bDeleteDate;

	@SerializedName("b_option")
	private String bOption;

	@SerializedName("b_is_delete")
	private Object bIsDelete;

	public void setBId(int bId){
		this.bId = bId;
	}

	public int getBId(){
		return bId;
	}

	public void setBQuestionId(int bQuestionId){
		this.bQuestionId = bQuestionId;
	}

	public int getBQuestionId(){
		return bQuestionId;
	}

	public void setBDeleteDate(Object bDeleteDate){
		this.bDeleteDate = bDeleteDate;
	}

	public Object getBDeleteDate(){
		return bDeleteDate;
	}

	public void setBOption(String bOption){
		this.bOption = bOption;
	}

	public String getBOption(){
		return bOption;
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
			"GAnswers{" + 
			"b_id = '" + bId + '\'' + 
			",b_question_id = '" + bQuestionId + '\'' + 
			",b_delete_date = '" + bDeleteDate + '\'' + 
			",b_option = '" + bOption + '\'' + 
			",b_is_delete = '" + bIsDelete + '\'' + 
			"}";
		}
}