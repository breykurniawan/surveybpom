package com.bpom.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bpom.app.R;
import com.bpom.app.models.responden.GResponden;

import java.util.List;

public class RespondenAdapter extends RecyclerView.Adapter<RespondenAdapter.ViewHolder> {

    private List<GResponden> lists; //inisialisasi List dengan object GResponden


    //construktor SurveyAdapter
    public RespondenAdapter(List<GResponden> lists) {
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
        GResponden data = lists.get(position); //inisialisasi object DataMahasiwa
        holder.mNama.setText(data.getBName());
    }

    @Override
    public int getItemCount() {
        return lists.size(); //mengambil item sesuai urutan
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mNama, mNim; //inisialisasi variabel

        public ViewHolder(View itemView) {
            super(itemView);
            mNama = itemView.findViewById(R.id.tvName);
        }
    }
}