package com.bpom.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bpom.app.R;
import com.bpom.app.models.questions.GQuestions;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_survey, parent, false);
        ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
        return holder;
    } //fungsi yang dijalankan saat ViewHolder dibuat

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GQuestions data = lists.get(position); //inisialisasi object DataMahasiwa
        holder.mNama.setText(data.getBTitle());
//        holder.mId.setText(data.getBId());
    }

    @Override
    public int getItemCount() {
        return lists.size(); //mengambil item sesuai urutan
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mNama, mId; //inisialisasi variabel

        public ViewHolder(View itemView) {
            super(itemView);
            mNama = itemView.findViewById(R.id.tvName);
            mId = itemView.findViewById(R.id.tvId);

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