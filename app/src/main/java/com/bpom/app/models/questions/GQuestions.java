package com.bpom.app.models.questions;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GQuestions{

	@SerializedName("b_name_questioner")
	private String bNameQuestioner;

	@SerializedName("b_users_id")
	private int bUsersId;

	@SerializedName("b_position")
	private int bPosition;

	@SerializedName("b_id_questioner")
	private int bIdQuestioner;

	@SerializedName("b_description")
	private String bDescription;

	@SerializedName("question_type")
	private String questionType;

	@SerializedName("b_parent_id")
	private int bParentId;

	@SerializedName("b_question_option")
	private String bQuestionOption;

	@SerializedName("b_title")
	private String bTitle;

	@SerializedName("b_question_id")
	private int bQuestionId;

	@SerializedName("b_survey_id")
	private int bSurveyId;

	@SerializedName("b_is_delete")
	private Object bIsDelete;

	public void setBNameQuestioner(String bNameQuestioner){
		this.bNameQuestioner = bNameQuestioner;
	}

	public String getBNameQuestioner(){
		return bNameQuestioner;
	}

	public void setBUsersId(int bUsersId){
		this.bUsersId = bUsersId;
	}

	public int getBUsersId(){
		return bUsersId;
	}

	public void setBPosition(int bPosition){
		this.bPosition = bPosition;
	}

	public int getBPosition(){
		return bPosition;
	}

	public void setBIdQuestioner(int bIdQuestioner){
		this.bIdQuestioner = bIdQuestioner;
	}

	public int getBIdQuestioner(){
		return bIdQuestioner;
	}

	public void setBDescription(String bDescription){
		this.bDescription = bDescription;
	}

	public String getBDescription(){
		return bDescription;
	}

	public void setQuestionType(String questionType){
		this.questionType = questionType;
	}

	public String getQuestionType(){
		return questionType;
	}

	public void setBParentId(int bParentId){
		this.bParentId = bParentId;
	}

	public int getBParentId(){
		return bParentId;
	}

	public void setBQuestionOption(String bQuestionOption){
		this.bQuestionOption = bQuestionOption;
	}

	public String getBQuestionOption(){
		return bQuestionOption;
	}

	public void setBTitle(String bTitle){
		this.bTitle = bTitle;
	}

	public String getBTitle(){
		return bTitle;
	}

	public void setBQuestionId(int bQuestionId){
		this.bQuestionId = bQuestionId;
	}

	public int getBQuestionId(){
		return bQuestionId;
	}

	public void setBSurveyId(int bSurveyId){
		this.bSurveyId = bSurveyId;
	}

	public int getBSurveyId(){
		return bSurveyId;
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
			"GQuestions{" + 
			"b_name_questioner = '" + bNameQuestioner + '\'' + 
			",b_users_id = '" + bUsersId + '\'' + 
			",b_position = '" + bPosition + '\'' + 
			",b_id_questioner = '" + bIdQuestioner + '\'' + 
			",b_description = '" + bDescription + '\'' + 
			",question_type = '" + questionType + '\'' + 
			",b_parent_id = '" + bParentId + '\'' + 
			",b_question_option = '" + bQuestionOption + '\'' + 
			",b_title = '" + bTitle + '\'' + 
			",b_question_id = '" + bQuestionId + '\'' + 
			",b_survey_id = '" + bSurveyId + '\'' + 
			",b_is_delete = '" + bIsDelete + '\'' + 
			"}";
		}
}