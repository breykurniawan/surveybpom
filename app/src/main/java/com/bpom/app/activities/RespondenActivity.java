package com.bpom.app.activities;

import android.content.Context;
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

import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.adapters.DatabaseAdapter;
import com.bpom.app.adapters.RespondenAdapter;
import com.bpom.app.models.responden.GResponden;
import com.bpom.app.utils.Cons;

import java.util.ArrayList;
import java.util.List;

public class RespondenActivity extends AppCompatActivity {

    BE.LoadingPrimary pd;
    Context c;

    private static final String TAG = "ReadAllActivity";
    private List<GResponden> lists;
    private RecyclerView rv;

    private DatabaseAdapter dbCon;
    RespondenAdapter adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        getSupportActionBar().setTitle("Pilih Responden");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        c = this;
        pd = new BE.LoadingPrimary(c);

        final TextView tvArea = findViewById(R.id.tvArea);
        tvArea.setText("Silahkan pilih target responden anda");

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        rv.setLayoutManager(new LinearLayoutManager(this)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        lists = new ArrayList<>();
        adapters = new RespondenAdapter(c,lists);
        rv.setAdapter(adapters);

        pd.show();
        dbCon=new DatabaseAdapter(this);
        loadListGRespondenFT();
        /*AndroidNetworking.get(Cons.API_RESPONDEN)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(GResponden.class, new ParsedRequestListener<List<GResponden>>() {
                    @Override
                    public void onResponse(List<GResponden> r) {
                        pd.dismiss();
                        if(r.size()>0) {
                            lists = r;
                            adapters = new RespondenAdapter(c,lists);
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

    public void loadListGRespondenFT(){
        SQLiteDatabase rdb = dbCon.getReadableDatabase();

        /*SELECT * from v_responden a
        LEFT JOIN b_users_baru b on a.b_staff_id = b.b_id*/
        String query="SELECT * FROM "+ DatabaseAdapter.TB_V_RESPONDEN;
                /*+" WHERE "+DatabaseAdapter.b_id+"='"+Cons.gID+"'";*/
        Cursor cur=rdb.rawQuery(query,null);
        try {
            if (cur.moveToFirst()) {
                do {
                    GResponden rows=new GResponden();
                    rows.setBAreaLevelOneId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_area_level_one_id)));
                    rows.setBName(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_name)));
                    rows.setBPendidikan(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_pendidikan)));
                    rows.setBSurveyLevel(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_survey_level)));
                    rows.setBAreaRootId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_area_root_id)));
                    rows.setBTelepon(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_telepon)));
                    rows.setBLatitude(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_latitude)));
                    rows.setBDeleteDate(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_delete_date)));
                    rows.setBDob(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_dob)));
                    rows.setBAddress(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_address)));;
                    rows.setBAreaLevelTwoId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_area_level_two_id)));;
                    rows.setBDate(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_date)));
                    rows.setBLongitude(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_longitude)));
                    rows.setBAreaId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_area_id)));
                    rows.setBId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_id)));
                    rows.setBSex(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_sex)));
                    rows.setBStaffId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_staff_id)));
                    rows.setBAreaLevelThreeId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_area_level_three_id)));
                    rows.setBSurveyId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_survey_id)));
                    rows.setBEmail(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_email)));
                    rows.setBInstansi(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_instansi)));
                    rows.setBStatus(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_status)));
                    rows.setBIsDelete(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_is_delete)));
                    lists.add(rows);
                }while (cur.moveToNext());

                adapters = new RespondenAdapter(c,lists);
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
