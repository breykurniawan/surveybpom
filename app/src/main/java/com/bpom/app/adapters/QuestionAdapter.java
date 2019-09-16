package com.bpom.app.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.activities.QuestionsActivity;
import com.bpom.app.models.answers.GAnswers;
import com.bpom.app.models.q_a_data.QuestionAnswer;
import com.bpom.app.models.questions.GQuestions;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int inCCCC=0;
    private List<GQuestions> lists;
    public List<QuestionAnswer> answersLists;
    Context c;
    private String TAG;
    private DatabaseAdapter dbCon;
    QuestionsActivity activitys;
    public QuestionAdapter(QuestionsActivity activitys,Context c, List<GQuestions> lists) {
        this.activitys=activitys;
        this.c = c;
        this.answersLists=new ArrayList<>();
        ArrayList<String> _strs= new ArrayList<>();
        for(int i=0;i<lists.size();i++){
            QuestionAnswer qa=new QuestionAnswer();
            qa.setBId(i);
            qa.setBrgp(new RadioGroup(c));
            qa.setB_multi_text(_strs);
            answersLists.add(qa);
        }
        this.lists = lists;
        dbCon=new DatabaseAdapter(c);
        inCCCC=0;
    }
    public void addInsertItems(List<GQuestions> datas) {
        lists.clear();
        lists.addAll(datas);
        notifyDataSetChanged();
    }
    public void addItem(GQuestions insertData) {
        int lens=lists.size();
        lists.add(lists.size(), insertData);
        notifyItemInserted(lens);
    }
    public void removeItem(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
        //notifyDataSetChanged();

    }
    @Override
    public int getItemViewType(int position) {
        GQuestions data = lists.get(position);
        String qt = data.getQuestionType();
        int viewtype=0;
        if("Essay".equals(qt)) {
            viewtype=0;
        }else if ("Yes/No".equals(qt)) {
            viewtype=1;
        }else if("Multiple Chooice ".equals(qt)) {
            viewtype=2;
        }else{
            viewtype=3;
        }
        return viewtype;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(viewType==0){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_edit, parent, false);
            ViewHolder0 holder = new ViewHolder0(v);
            return holder;
        }else if(viewType==1){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_radio, parent, false);
            ViewHolder1 holder = new ViewHolder1(v);
            return holder;
        }else if(viewType==2){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_multi, parent, false);
            ViewHolder2 holder = new ViewHolder2(v);
            return holder;
        }else{
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_other, parent, false);
            ViewHolder3 holder = new ViewHolder3(v);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType=holder.getItemViewType();
        if(viewType==0){
            ViewHolder0 holders = (ViewHolder0)holder;
            GQuestions data = lists.get(position);
            holders.mNo.setText((position + 1) + ". ");
            holders.mId.setText(String.valueOf(data.getBQuestionId()));
            holders.mNama.setText(data.getBTitle());
            holders.etEssay.setText(answersLists.get(position).getBanswertxt());
            //holders.mAnswer.setText(data.getQuestionType());
            //String qt = data.getQuestionType();
            //holders.mEssay.setVisibility(View.VISIBLE);
        }else if(viewType==1){
            ViewHolder1 holders = (ViewHolder1)holder;
            GQuestions data = lists.get(position);
            holders.mNo.setText((position + 1) + ". ");
            holders.mId.setText(String.valueOf(data.getBQuestionId()));
            holders.mNama.setText(data.getBTitle());
            //holders.mAnswer.setText(data.getQuestionType());
            //String qt = data.getQuestionType();

            String ids = String.valueOf(data.getBQuestionId());
            List<GAnswers> listsANSWER=loadListGANSWERFT(ids);
            holders.rgA.removeAllViews();
            if(listsANSWER.size()>0) {
                for (int i = 0; i < listsANSWER.size(); i++) {
                    RadioButton radioButton = new RadioButton(c);
                    GAnswers rows = listsANSWER.get(i);
                    radioButton.setText(rows.getBOption());
                    radioButton.setId(i);
                    String _select="";
                    try {
                        _select =answersLists.get(position).getBOption();
                    }catch (Exception e){
                        _select="";
                    }
                    if(!(_select==null) && _select.equals(rows.getBOption())){
                        radioButton.setChecked(true);
                    }

                    RadioGroup.LayoutParams rprms =
                            new RadioGroup.LayoutParams(
                                    RadioGroup.LayoutParams.WRAP_CONTENT,
                                    RadioGroup.LayoutParams.WRAP_CONTENT
                            );
                    holders.rgA.addView(radioButton, rprms);
                    holders.tvStatus.setText("1");
                }
            } else {
                BE.TShort(c.getString(R.string.err_no_data));
            }

        }else if(viewType==2){
            final ViewHolder2 holders = (ViewHolder2)holder;
            GQuestions data = lists.get(position);
            holders.mNo.setText((position + 1) + ". ");
            holders.mId.setText(String.valueOf(data.getBQuestionId()));
            holders.mNama.setText(data.getBTitle());
            //holders.mAnswer.setText(data.getQuestionType());
            //String qt = data.getQuestionType();
            String ids = String.valueOf(data.getBQuestionId());
            List<GAnswers> listsANSWER=loadListGANSWERFT(ids);
            holders.llCK.removeAllViews();
            if(listsANSWER.size()>0){
                List<String> _CheckTxt=answersLists.get(position).getB_multi_text();
                if(_CheckTxt==null || _CheckTxt.size()==0){
                    for(int i=0;i<listsANSWER.size();i++){
                        _CheckTxt.add("");
                    }
                }
                for(int i=0;i<listsANSWER.size();i++){
                    final CheckBox CheckButton = new CheckBox(c);
                    GAnswers rows=listsANSWER.get(i);
                    CheckButton.setText(rows.getBOption());
                    CheckButton.setId(i);
                    CheckButton.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            )
                    );
                    if(_CheckTxt.get(i).equals(rows.getBOption()))CheckButton.setChecked(true);
                    holders.llCK.addView(CheckButton);
                    CheckButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean checked=CheckButton.isChecked();
                            int j=0;
                            while(true){
                                CheckBox _chks=(CheckBox)holders.llCK.getChildAt(j);
                                if(CheckButton.getText().toString().equals(_chks.getText().toString())){
                                    break;
                                }
                                j++;
                            }
                            if(checked){
                                answersLists.get(position).getB_multi_text().set(j,CheckButton.getText().toString());
                            }else {
                                answersLists.get(position).getB_multi_text().set(j,"");
                            }
                        }
                    });
                    holders.tvStatus.setText("1");
                }
            }else{
                BE.TShort(c.getString(R.string.err_no_data));
            }

        }else if(viewType==3){
            ViewHolder3 holders = (ViewHolder3)holder;
            GQuestions data = lists.get(position);
            holders.mNo.setText((position + 1) + ". ");
            holders.mId.setText(String.valueOf(data.getBQuestionId()));
            holders.mNama.setText(data.getBTitle());
            //holders.mAnswer.setText(data.getQuestionType());
            //String qt = data.getQuestionType();
            holders.mAnswer.setText(data.getBOptionList());
        }
        inCCCC=inCCCC+1;
    }

    /*@Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            GQuestions data = lists.get(position);
            holder.mNo.setText((position + 1) + ". ");
            holder.mId.setText(String.valueOf(data.getBQuestionId()));
            holder.mNama.setText(data.getBTitle());
            holder.mAnswer.setText(data.getQuestionType());
            String qt = data.getQuestionType();
            int sizess=getItemCount();
            System.out.println("*************"+position+"***********************");
            if(inCCCC<sizess){
                holder.rgA.removeAllViews();
                holder.llCK.removeAllViews();
                if (null != qt) {
                    System.out.println("--------"+qt+"--------");
                    if("Essay".equals(qt)) {
                        System.out.println("******111***************");
                        holder.mEssay.setVisibility(View.VISIBLE);
                    }else if ("Yes/No".equals(qt)) {
                        System.out.println("******222***************");
                        holder.llRB.setVisibility(View.VISIBLE);
                        String ids = String.valueOf(data.getBQuestionId());
                        List<GAnswers> listsANSWER=loadListGANSWERFT(ids);
                        //holder.rgA.removeAllViews();
                        if(listsANSWER.size()>0) {
                            for (int i = 0; i < listsANSWER.size(); i++) {
                                RadioButton radioButton = new RadioButton(c);
                                GAnswers rows = listsANSWER.get(i);
                                radioButton.setText(rows.getBOption());
                                radioButton.setId(i);
                                RadioGroup.LayoutParams rprms =
                                        new RadioGroup.LayoutParams(
                                                RadioGroup.LayoutParams.WRAP_CONTENT,
                                                RadioGroup.LayoutParams.WRAP_CONTENT
                                        );
                                holder.rgA.addView(radioButton, rprms);
                                holder.tvStatus.setText("1");
                            }
                        } else {
                            BE.TShort(c.getString(R.string.err_no_data));
                        }
                    }else if("Multiple Chooice ".equals(qt)) {
                        System.out.println("******333***************");
                        holder.llCK.setVisibility(View.VISIBLE);
                        String ids = String.valueOf(data.getBQuestionId());
                        List<GAnswers> listsANSWER=loadListGANSWERFT(ids);
                        if(listsANSWER.size()>0){
                            for(int i=0;i<listsANSWER.size();i++){
                                CheckBox CheckButton = new CheckBox(c);
                                GAnswers rows=listsANSWER.get(i);
                                CheckButton.setText(rows.getBOption());
                                CheckButton.setId(i);
                                CheckButton.setLayoutParams(
                                        new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT
                                        )
                                );
                                holder.llCK.addView(CheckButton);
                                holder.tvStatus.setText("1");
                            }
                        }else{
                            BE.TShort(c.getString(R.string.err_no_data));
                        }
                    } else {
                        System.out.println("******444***************");
                        holder.mAnswer.setText(data.getBOptionList());
                    }
                }
            }
            inCCCC=inCCCC+1;
        }
    }*/

    @Override
    public int getItemCount() {
        return lists.size(); //mengambil item sesuai urutan
    }



    /*private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }*/


    class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView mNama, mNo, mAnswer,mId,tvStatus,etEssay; //inisialisasi variabel
        LinearLayout mEssay;
        public ViewHolder0(View itemView){
            super(itemView);
            TAG = c.getClass().getSimpleName();
            mNo = itemView.findViewById(R.id.tvNo);
            mId = itemView.findViewById(R.id.tvId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            mNama = itemView.findViewById(R.id.tvName);
            mAnswer = itemView.findViewById(R.id.tvAnswer);
            mEssay = itemView.findViewById(R.id.llAnswerEssay);
            etEssay = itemView.findViewById(R.id.etEssay);
            etEssay.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    answersLists.get(getAdapterPosition()).setBQuestionId(Integer.valueOf(mId.getText().toString()));
                    answersLists.get(getAdapterPosition()).setBanswertxt(etEssay.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView mNama, mNo, mAnswer,mId,tvStatus;
        LinearLayout  llRB;
        RadioGroup rgA;
        public ViewHolder1(final View itemView){
            super(itemView);
            TAG = c.getClass().getSimpleName();
            mNo = itemView.findViewById(R.id.tvNo);
            mId = itemView.findViewById(R.id.tvId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            mNama = itemView.findViewById(R.id.tvName);
            mAnswer = itemView.findViewById(R.id.tvAnswer);
            llRB = itemView.findViewById(R.id.llRB);
            rgA = itemView.findViewById(R.id.rgAnswer);
            rgA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton)itemView.findViewById(selectedId);
                    try {
                        if(radioButton.isChecked()){
                            answersLists.get(getAdapterPosition()).setBQuestionId(Integer.valueOf(mId.getText().toString()));
                            answersLists.get(getAdapterPosition()).setBOption(radioButton.getText().toString());
                        }
                    }catch (Exception e){

                    }
                }
            });
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView mNama, mNo, mAnswer,mId,tvStatus;
        LinearLayout  llCK;
        public ViewHolder2(View itemView){
            super(itemView);
            TAG = c.getClass().getSimpleName();
            mNo = itemView.findViewById(R.id.tvNo);
            mId = itemView.findViewById(R.id.tvId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            mNama = itemView.findViewById(R.id.tvName);
            mAnswer = itemView.findViewById(R.id.tvAnswer);
            llCK = itemView.findViewById(R.id.llCK);
        }
    }
    class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView mNama, mNo, mAnswer,mId,tvStatus;
        public ViewHolder3(View itemView){
            super(itemView);
            TAG = c.getClass().getSimpleName();
            mNo = itemView.findViewById(R.id.tvNo);
            mId = itemView.findViewById(R.id.tvId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            mNama = itemView.findViewById(R.id.tvName);
            mAnswer = itemView.findViewById(R.id.tvAnswer);
        }
    }
        /*public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mNama, mNo, mAnswer,mId,tvStatus; //inisialisasi variabel
        LinearLayout mEssay, llRB,llCK;
        RadioGroup rgA;
        Button btnJwb, btnSave;
        public ViewHolder(View itemView) {
            super(itemView);
            TAG = c.getClass().getSimpleName();
            mNo = itemView.findViewById(R.id.tvNo);
            mId = itemView.findViewById(R.id.tvId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            mNama = itemView.findViewById(R.id.tvName);
            mAnswer = itemView.findViewById(R.id.tvAnswer);
            mEssay = itemView.findViewById(R.id.llAnswerEssay);
            llRB = itemView.findViewById(R.id.llRB);
            llCK = itemView.findViewById(R.id.llCK);
            rgA = itemView.findViewById(R.id.rgAnswer);
            btnJwb = itemView.findViewById(R.id.btnJawab);
            //final int position = Integer.parseInt(mNo.getText().toString().replaceAll(". ","")) - 1;
            btnSave = itemView.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BE.TShort("Saved");
                }
            });
        }
    }*/
    public List<GAnswers> loadListGANSWERFT(String ids){
        List<GAnswers> listsANSWER=new ArrayList<>();
        SQLiteDatabase rdb = dbCon.getReadableDatabase();
        String query="SELECT * FROM "+ DatabaseAdapter.TB_B_ANSWER
                +" WHERE "+DatabaseAdapter.b_question_id+"='"+ids+"'";

        Cursor cur=rdb.rawQuery(query,null);
        try {
            if (cur.moveToFirst()) {
                do {
                    GAnswers rows=new GAnswers();
                    rows.setBId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_id)));
                    rows.setBQuestionId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_question_id)));
                    rows.setBDeleteDate(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_delete_date)));
                    rows.setBOption(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_option)));
                    rows.setBIsDelete(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_is_delete)));
                    listsANSWER.add(rows);
                }while (cur.moveToNext());
            }
        }catch (Exception e){
            BE.TShort(e.toString());
            Log.d(TAG, "onError errorCode : " + e.toString());
        }
        cur.close();
        rdb.close();
        return listsANSWER;
    }

    public void _saveDataFT(){
        boolean saveFlags=true;
        for(int i=0;i<lists.size();i++){
            QuestionAnswer qa=this.answersLists.get(i);
            GQuestions data = lists.get(i);
            String qt = data.getQuestionType();
            int viewtype=0;
            if("Essay".equals(qt)) {
                viewtype=0;
            }else if ("Yes/No".equals(qt)) {
                viewtype=1;
            }else if("Multiple Chooice ".equals(qt)) {
                viewtype=2;
            }else{
                viewtype=3;
            }
            if(viewtype==0){
                if(qa.getBanswertxt().equals("")){
                    saveFlags=false;
                    break;
                }
            }else if(viewtype==1){
                if(qa.getBOption().equals("")){
                    saveFlags=false;
                    break;
                }
            }else if(viewtype==2){
                List<String> _multiStr=qa.getB_multi_text();
                for(int j=0;j<_multiStr.size();j++){
                    if(_multiStr.get(j).equals("")){
                        saveFlags=false;
                        break;
                    }
                }
                if(!saveFlags)break;
            }
        }
        if(!saveFlags){
            BE.TShort("Please answer complete!");
        }else {

        }

    }
}