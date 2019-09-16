package com.bpom.app.models.q_a_data;
import com.bpom.app.models.answers.GAnswers;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import javax.annotation.Generated;
@Generated("com.robohorse.robopojogenerator")
public class QuestionAnswer{

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



    @SerializedName("b_multi_Option")
    private List<GAnswers> bmultiOption;

    @SerializedName("b_answer_txt")
    private String banswertxt;

    @SerializedName("b_multi_Option")
    private ArrayList<String> b_multi_text;

    @SerializedName("b_r_gp")
    private RadioGroup brgp;


    public void setB_multi_text(ArrayList<String> b_multi_text) {
        this.b_multi_text = b_multi_text;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public ArrayList<String> getB_multi_text() {
        return b_multi_text;
    }


    public void setBrgp(RadioGroup brgp) {
        this.brgp = brgp;
    }

    public RadioGroup getBrgp() {
        return brgp;
    }

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

    public List<GAnswers> getBmultiOption() {
        return bmultiOption;
    }

    public void setBmultiOption(List<GAnswers> bmultiOption) {
        this.bmultiOption = bmultiOption;
    }
    public void setBanswertxt(String banswertxt) {
        this.banswertxt = banswertxt;
    }

    public String getBanswertxt() {
        return banswertxt;
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