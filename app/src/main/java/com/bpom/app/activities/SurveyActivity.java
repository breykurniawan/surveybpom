package com.bpom.app.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.adapters.DatabaseAdapter;
import com.bpom.app.adapters.SurveyAdapter;
import com.bpom.app.models.areas.GArea;
import com.bpom.app.models.headers.GHeaders;
import com.bpom.app.utils.Cons;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SurveyActivity extends AppCompatActivity {

    BE.LoadingPrimary pd;
    Context c;

    private static final String TAG = "ReadAllActivity";
    private List<GHeaders> lists;
    private RecyclerView rv;

    private DatabaseAdapter dbCon;
    SurveyAdapter adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        getSupportActionBar().setTitle("Survey");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        c = this;
        pd = new BE.LoadingPrimary(c);



        pd.show();
        dbCon=new DatabaseAdapter(this);
        loadListGAreaFT();
        /*AndroidNetworking.get(Cons.API_AREA+Cons.gID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(GArea.class, new ParsedRequestListener<List<GArea>>() {
                    @Override
                    public void onResponse(List<GArea> r) {
                        if(r.size()>0) {
                            tvArea.setText(r.get(0).getBArea());
                        }else {
                            BE.TShort(getString(R.string.err_no_data));
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        BE.TShort(error.getErrorDetail());
                        Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                        Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                    }
                });*/

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        rv.setLayoutManager(new LinearLayoutManager(this)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        lists = new ArrayList<>();
        adapters = new SurveyAdapter(c, lists);
        rv.setAdapter(adapters);

        loadListGHeadersFT();
        /*AndroidNetworking.get(Cons.API_HEADER+Cons.gID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(GHeaders.class, new ParsedRequestListener<List<GHeaders>>() {
                    @Override
                    public void onResponse(List<GHeaders> r) {
                        pd.dismiss();
                        if(r.size()>0) {
                            lists = r;
                            adapters = new SurveyAdapter(c,lists);
                            rv.setAdapter(adapters);
                            adapters.notifyDataSetChanged();
                        }else {
                            BE.TShort(getString(R.string.err_no_data));
                        }
                    } @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        BE.TShort(error.getErrorDetail());
                        Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                        Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                    }
                });*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                return false;
        }
        return true;
    }

    public void insert(View view) {
        BE.TShort(getString(R.string.err_d));
    }
    public void loadListGAreaFT(){
        SQLiteDatabase rdb = dbCon.getReadableDatabase();
        String query="SELECT * FROM "+ DatabaseAdapter.TB_B_AREA
                +" WHERE "+DatabaseAdapter.b_id+"='"+Cons.gID+"'";
        Cursor cur=rdb.rawQuery(query,null);
        try {
            if (cur.moveToFirst()) {
                final TextView tvArea = findViewById(R.id.tvArea);
                tvArea.setText(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_area)));
            }
        }catch (Exception e){
            BE.TShort(e.toString());
            Log.d(TAG, "onError errorCode : " + e.toString());;
        }
    }
    public void loadListGHeadersFT(){
        SQLiteDatabase rdb = dbCon.getReadableDatabase();
        /*SELECT b_name FROM v_questioner WHERE b_survey_id = 37 ORDER BY b_name*/
        String query="SELECT * FROM "+ DatabaseAdapter.TB_V_QUESTIONER
                +" WHERE "+DatabaseAdapter.b_survey_id+"='"+Cons.gID+"' ORDER BY "+DatabaseAdapter.b_name;
        Cursor cur=rdb.rawQuery(query,null);
        try {
            if (cur.moveToFirst()) {
                do {
                    GHeaders rows=new GHeaders();
                    rows.setBId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_id)));
                    rows.setBSurveyName(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_survey_name)));
                    rows.setBName(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_name)));
                    rows.setBUsersId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_users_id)));
                    rows.setBDescription(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_description)));
                    rows.setBSurveyId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_survey_id)));
                    rows.setBUsers(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_users)));
                    rows.setBStatus(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_status)));
                    rows.setBDate(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_date)));
                    rows.setBIsDelete(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_is_delete)));
                    lists.add(rows);
                }while (cur.moveToNext());
                adapters = new SurveyAdapter(c,lists);
                rv.setAdapter(adapters);
                adapters.notifyDataSetChanged();
            }
        }catch (Exception e){
            pd.dismiss();
            BE.TShort(e.toString());
            Log.d(TAG, "onError errorCode : " + e.toString());
            return;
        }
        pd.dismiss();
        cur.close();
        rdb.close();
    }
}
