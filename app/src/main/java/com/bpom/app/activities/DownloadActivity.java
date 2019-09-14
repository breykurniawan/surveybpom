package com.bpom.app.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.adapters.DatabaseAdapter;
import com.bpom.app.models.responden.GResponden;
import com.bpom.app.utils.Cons;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DownloadActivity extends AppCompatActivity {
    FloatingActionButton fabs;
    BE.LoadingPrimary pd;
    Context c;
    TextView tvLoad;
    ContentLoadingProgressBar progressBar;

    private static final String TAG = "ReadAllActivity";
    private List<GResponden> lists;
    private boolean saveFlags=false;

    private DatabaseAdapter dbCon;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        saveFlags=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dowload Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        c = this;
        pd = new BE.LoadingPrimary(c);
        lists = new ArrayList<>();
        pd.show();
        fabs = findViewById(R.id.fab);
        tvLoad=findViewById(R.id.tvLoad);
        progressBar=findViewById(R.id.progressBar);

        dbCon=new DatabaseAdapter(this);

        AndroidNetworking.get(Cons.API_RESPONDEN)
            .setPriority(Priority.HIGH)
            .build()
            .getAsObjectList(GResponden.class, new ParsedRequestListener<List<GResponden>>() {
                @Override
                public void onResponse(List<GResponden> r) {
                    pd.dismiss();
                    if(r.size()>0) {
                        tvLoad.setText("Loading is finish.");
                        lists = r;
                        saveFlags=true;
                    }else {
                        saveFlags=false;
                        BE.TShort(getString(R.string.err_no_data));
                    }
                } @Override
                public void onError(ANError error) {
                    saveFlags=false;
                    pd.dismiss();
                    BE.TShort(error.getErrorDetail());
                    /*Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                    Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());*/
                }
            });
        fabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(c)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("Keluar")
                        .setMessage("Yakin untuk keluar dari akun?")
                        .setPositiveButton("Ya",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    if(saveFlags){
                                        tvLoad.setText("Just moment...");
                                        progressBar.setVisibility(View.VISIBLE);
                                        saveDataFT();
                                    }else {
                                        BE.TShort(getString(R.string.err_no_data));
                                    }
                                }

                            }).setNegativeButton("Tidak", null).show();
            }
        });
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
    private boolean saveDataFT(){
        SQLiteDatabase wdb = dbCon.getWritableDatabase();
        //SQLiteDatabase rdb = dbCon.getReadableDatabase();
        Cursor cur = null;

        int sizes=lists.size();
        progressBar.setProgress(sizes);
        for(int i=0;i<sizes;i++){
            GResponden rows=lists.get(i);
            String query = "INSERT INTO "+dbCon.TB_V_RESPONDEN+" ("
                    +dbCon.b_id +","
                    +dbCon.b_survey_level +","
                    +dbCon.b_area_id  +","
                    +dbCon.b_name  +","
                    +dbCon.b_sex  +","
                    +dbCon.b_dob  +","
                    +dbCon.b_address  +","
                    +dbCon.b_email  +","
                    +dbCon.b_telepon +","
                    +dbCon.b_instansi+","
                    +dbCon.b_pendidikan +","
                    +dbCon.b_longitude+","
                    +dbCon.b_latitude+","
                    +dbCon.b_status +","
                    +dbCon.b_is_delete+","
                    +dbCon.b_delete_date +","
                    +dbCon.b_date +","
                    +dbCon.b_survey_id +","
                    +dbCon.b_area_root_id+","
                    +dbCon.b_area_level_one_id +","
                    +dbCon.b_area_level_two_id+","
                    +dbCon.b_area_level_three_id +","
                    +dbCon.b_staff_id +","
                    +dbCon.b_full_name
                    +") VALUES ('"
                    +rows.getBId()+"','"
                    +rows.getBSurveyLevel()+"','"
                    +rows.getBAreaId()+"','"
                    +rows.getBName()+"','"
                    +rows.getBSex()+"','"
                    +rows.getBDob()+"','"
                    +rows.getBAddress()+"','"
                    +rows.getBEmail()+"','"
                    +rows.getBTelepon()+"','"
                    +rows.getBInstansi()+"','"
                    +rows.getBPendidikan()+"','"
                    +rows.getBLongitude()+"','"
                    +rows.getBLatitude()+"','"
                    +rows.getBStatus()+"','"
                    +rows.getBIsDelete()+"','"
                    +rows.getBDeleteDate()
                    +rows.getBDate()+"','"
                    +rows.getBSurveyId()+"','"
                    +rows.getBAreaRootId()+"','"
                    +rows.getBAreaLevelOneId()+"','"
                    +rows.getBAreaLevelTwoId()+"','"
                    +rows.getBAreaLevelThreeId()+"','"
                    +rows.getBStaffId()+"','"
                    +"abc"
                    + "')";




            try {
                wdb.execSQL(query);
            }catch (Exception e){

            }finally {

            }
            progressBar.setProgress(i+1);
        }




        /*try {
            String query = "";
            query = "INSERT INTO tb_groups (name) VALUES ('" + group_name + "')";
            wdb.execSQL(query);
            cur = rdb.rawQuery("SELECT max(id) as max FROM tb_groups", null);
            int ids = 0;

            if (cur != null) {
                cur.moveToFirst();
                ids = cur.getInt(cur.getColumnIndex("max"));
            } else {
                ids = 1;
            }
            for (int i = 0; i < saveNumbers.size(); i++) {
                query = "INSERT INTO tb_contact_lists (gid,name,number) VALUES ('"
                        + Integer.toString(ids) + "','"
                        + saveNumbers.get(i).getName() + "','"
                        + saveNumbers.get(i).getNumber()
                        + "')";
                wdb.execSQL(query);
            }
            assert cur != null;
            cur.close();
        } catch (Exception e) {
            assert cur != null;
            cur.close();
        }*/
        wdb.close();
        //rdb.close();
        tvLoad.setText("Save is finish.");
        return false;
    }
}
