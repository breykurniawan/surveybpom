package com.bpom.app.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bpom.app.R;
import com.bpom.app.models.questions.GQuestions;
import com.bpom.app.utils.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<GQuestions> lists; //inisialisasi List dengan object GQuestions
    Context c;


    //construktor SurveyAdapter
    public QuestionAdapter(Context c, List<GQuestions> lists) {
        this.c = c;
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question, parent, false);
        ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
        return holder;
    } //fungsi yang dijalankan saat ViewHolder dibuat

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GQuestions data = lists.get(position); //inisialisasi object DataMahasiwa
        holder.mNo.setText(String.valueOf(position+1)+". ");
        holder.mNama.setText(data.getBTitle());

        String qt = data.getQuestionType();
        if(qt.equals("Essay")){
            holder.mEssay.setVisibility(View.VISIBLE);
            holder.mAnswer.setVisibility(View.GONE);
        }else {
            holder.mEssay.setVisibility(View.GONE);
            holder.mAnswer.setVisibility(View.VISIBLE);
            holder.mAnswer.setText(data.getBOptionList());
        }
//        holder.mAnswer.setText(data.getBOptionList().get);
//        Log.d("jsonnya", lists.get(0).getBQuestionOption());
//        String src = data.getBQuestionOption();
//            String[] dest = new String[src.length()];
//            System.arraycopy(src, 0, dest, 0, src.length());
//            System.out.println(Arrays.toString(dest));
//            holder.mAnswer.setText(String.valueOf(Arrays.toString(dest)));

//        String src = "{\"Perkotaan\",\"Pedesaan\"}";
//        String[] dest = new String[src.length()];
//        System.arraycopy(src, 0, dest, 0, src.length());
//        Log.d("cek",dest.toString());


//        JsonHelper json = JsonHelper.fromStringToJSON(data.getBQuestionOption());
//        if(null != data.getBOptionList()) {
//            String src = data.getBOptionList().replace("\"","");
//            JsonHelper json = JsonHelper.fromStringToJSON(src);
//            if (json.obj != null) {
//
//                // If the String is a JSON array
//                if (json.isJsonArray) {
//                    JSONArray jsonArray = (JSONArray) json.obj;
//                    Log.d("jea", jsonArray.toString());
//                }
//                // If it's a JSON object
//                else {
//                    JSONObject jsonObject = (JSONObject) json.obj;
//                    Log.d("jea", jsonObject.toString());
//                }
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return lists.size(); //mengambil item sesuai urutan
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mNama, mNo, mAnswer; //inisialisasi variabel
        LinearLayout mEssay;

        public ViewHolder(View itemView) {
            super(itemView);
            mNo = itemView.findViewById(R.id.tvNo);
            mNama = itemView.findViewById(R.id.tvName);
            mAnswer = itemView.findViewById(R.id.tvAnswer);
            mEssay = itemView.findViewById(R.id.llAnswerEssay);



//            mNama.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String ids = mId.getText().toString();
//                    Toast.makeText(c, ids, Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(c, QuestionsActivity.class);
//                    Bundle b = new Bundle();
//                    b.putString("id",ids);
//                    i.putExtras(b);
//                    c.startActivity(i);
//                }
//            });
        }
    }
}