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
import com.bpom.app.models.areas.GArea;
import com.bpom.app.models.headers.GHeaders;
import com.bpom.app.models.questions.GQuestions;
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
    private List<GResponden> listsGResponden;
    private List<GHeaders> listsGHeaders;
    private List<GArea> listsGArea;
    private List<GQuestions> listsGQuestions;
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
        /////////////////////////////////////
        listsGResponden = new ArrayList<>();
        listsGHeaders = new ArrayList<>();
        listsGArea = new ArrayList<>();
        listsGQuestions = new ArrayList<>();
        ////////////////////////////////////
        pd.show();
        fabs = findViewById(R.id.fab);
        tvLoad=findViewById(R.id.tvLoad);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setMax(4);
        dbCon=new DatabaseAdapter(this);
        LOAD_RESPONDEN();

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
                                    if(listsGResponden.size()>0 &&
                                            listsGHeaders.size()>0 &&
                                            listsGQuestions.size()>0 &&
                                            listsGArea.size()>0)
                                        saveFlags=true;

                                    tvLoad.setText("User waiting...");

                                    if(saveFlags){
                                        tvLoad.setText("Start save...");
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


    public void LOAD_RESPONDEN(){
        AndroidNetworking.get(Cons.API_RESPONDEN)
            .setPriority(Priority.HIGH)
            .build()
            .getAsObjectList(GResponden.class, new ParsedRequestListener<List<GResponden>>() {
                @Override
                public void onResponse(List<GResponden> r) {
                    //pd.dismiss();
                    if(r.size()>0) {
                        tvLoad.setText("Loading is finish.");
                        listsGResponden = r;
                        progressBar.setProgress(1);
                        LOAD_HEADER();
                    }else {
                        saveFlags=false;
                        BE.TShort(getString(R.string.err_no_data));
                    }
                } @Override
                public void onError(ANError error) {
                    saveFlags=false;
                    pd.dismiss();
                    BE.TShort(error.getErrorDetail());
                    Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                    Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                }
            });
    }
    public void LOAD_HEADER(){
        AndroidNetworking.get(Cons.API_HEADER+Cons.gID)
            .setPriority(Priority.HIGH)
            .build()
            .getAsObjectList(GHeaders.class, new ParsedRequestListener<List<GHeaders>>() {
                @Override
                public void onResponse(List<GHeaders> r) {
                    //pd.dismiss();
                    if(r.size()>0) {
                        listsGHeaders = r;
                        progressBar.setProgress(2);
                        LOAD_AREA();
                    }else {
                        saveFlags=false;
                        BE.TShort(getString(R.string.err_no_data));
                    }
                } @Override
                public void onError(ANError error) {
                    saveFlags=false;
                    pd.dismiss();
                    BE.TShort(error.getErrorDetail());
                    Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                    Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                }
            });
    }
    public void LOAD_AREA(){
        AndroidNetworking.get(Cons.API_AREA+Cons.gID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(GArea.class, new ParsedRequestListener<List<GArea>>() {
                    @Override
                    public void onResponse(List<GArea> r) {
                        if(r.size()>0) {
                            listsGArea=r;
                            progressBar.setProgress(3);
                            LOAD_QUESTION();
                        }else {
                            saveFlags=false;
                            BE.TShort(getString(R.string.err_no_data));
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        saveFlags=false;
                        BE.TShort(error.getErrorDetail());
                        Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                        Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
    public void LOAD_QUESTION(){
        AndroidNetworking.get(Cons.API_QUESTION)
        .setPriority(Priority.HIGH)
        .build()
        .getAsObjectList(GQuestions.class, new ParsedRequestListener<List<GQuestions>>() {
                @Override
                public void onResponse(List<GQuestions> r) {
                    if(r.size()>0) {
                        listsGQuestions = r;
                        progressBar.setProgress(4);
                    }else {
                        saveFlags=false;
                        BE.TShort(getString(R.string.err_no_data));
                    }
                    pd.dismiss();
                } @Override
                public void onError(ANError error) {
                    saveFlags=false;
                    pd.dismiss();
                    BE.TShort(error.getErrorDetail());
                    Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                    Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                    Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
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
        SQLiteDatabase rdb = dbCon.getReadableDatabase();
        if(!Cons.DEBUG_MODE){
            if(!checkDownloadStateFT(rdb))return false;
        }else {
            try {
                wdb.execSQL("DROP TABLE IF EXISTS '" + dbCon.TB_SETTING + "'");
                wdb.execSQL(dbCon.CREATE_TB_APP_SETTING);
            }catch (Exception e){

            }
        }


        try {
            wdb.execSQL("DROP TABLE IF EXISTS '" + dbCon.TB_V_RESPONDEN + "'");
            wdb.execSQL("DROP TABLE IF EXISTS '" + dbCon.TB_V_QUESTIONER + "'");
            wdb.execSQL("DROP TABLE IF EXISTS '" + dbCon.TB_V_QUESTION + "'");
            wdb.execSQL("DROP TABLE IF EXISTS '" + dbCon.TB_B_AREA + "'");
            wdb.execSQL(dbCon.CREATE_TB_V_RESPONDEN);
            wdb.execSQL(dbCon.CREATE_TB_V_QUESTIONER);
            wdb.execSQL(dbCon.CREATE_TB_V_QUESTION);
            wdb.execSQL(dbCon.CREATE_TB_B_AREA);
        }catch (Exception e){
            BE.TShort(e.toString());
            Log.d(TAG, "onError errorCode : " + e.toString());
        }

        /*Cursor cur = null;
        String maxQuery="SELECT max(id) as max FROM "+dbCon.TB_V_RESPONDEN;
        cur = rdb.rawQuery(maxQuery, null);
        int ids = 0;

        if (cur != null) {
            cur.moveToFirst();
            ids = cur.getInt(cur.getColumnIndex("max"));
        } else {
            ids = 1;
        }*/
        int sizes1=listsGResponden.size();
        progressBar.setProgress(sizes1);
        for(int i=0;i<sizes1;i++){
            GResponden rows=listsGResponden.get(i);
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
                    +rows.getBDeleteDate()+"','"
                    +rows.getBDate()+"','"
                    +rows.getBSurveyId()+"','"
                    +rows.getBAreaRootId()+"','"
                    +rows.getBAreaLevelOneId()+"','"
                    +rows.getBAreaLevelTwoId()+"','"
                    +rows.getBAreaLevelThreeId()+"','"
                    +rows.getBStaffId()+"','"
                    +"b_full_name"
                    + "')";




            try {
                wdb.execSQL(query);
            }catch (Exception e){
                BE.TShort(e.toString());
                Log.d(TAG, "onError errorCode : " + e.toString());
            }finally {

            }
            progressBar.setProgress(i+1);
        }

        int sizes2=listsGHeaders.size();
        for(int i=0;i<sizes2;i++){
            GHeaders rows=listsGHeaders.get(i);
            String query = "INSERT INTO "+dbCon.TB_V_QUESTIONER+" ("
                    + dbCon.b_id+","
                    + dbCon.b_name +","
                    + dbCon.b_status+","
                    + dbCon.b_date +","
                    + dbCon.b_survey_id+","
                    + dbCon.b_users_id +","
                    + dbCon.b_survey_name +","
                    + dbCon.b_users+","
                    + dbCon.b_is_delete +","
                    + dbCon.b_description
                    +") VALUES ('"
                    +rows.getBId()+"','"
                    + rows.getBName()+"','"
                    + rows.getBStatus()+"','"
                    + rows.getBDate()+"','"
                    + rows.getBSurveyId()+"','"
                    + rows.getBUsersId()+"','"
                    + rows.getBSurveyName()+"','"
                    + rows.getBUsers()+"','"
                    + rows.getBIsDelete()+"','"
                    + rows.getBDescription()
                    + "')";
            try {
                wdb.execSQL(query);
            }catch (Exception e){
                BE.TShort(e.toString());
                Log.d(TAG, "onError errorCode : " + e.toString());
            }finally {

            }
        }

        int sizes3=listsGArea.size();
        for(int i=0;i<sizes3;i++){
            GArea rows=listsGArea.get(i);
            String query = "INSERT INTO "+dbCon.TB_B_AREA+" ("
                    +dbCon.b_id+","
                    +dbCon.b_area+","
                    +dbCon.b_description+","
                    +dbCon.b_client+","
                    +dbCon.b_start+","
                    +dbCon.b_finish+","
                    +dbCon.b_target+","
                    +dbCon.b_status+","
                    +dbCon.b_is_delete+","
                    +dbCon.b_delete_date+","
                    +dbCon.b_date+","
                    +dbCon.b_client_name+","
                    +dbCon.count+","
                    +dbCon.b_percent+","
                    +dbCon.b_area_level+","
                    +dbCon.b_result
                    +") VALUES ('"
                    +rows.getBId()+"','"
                    +rows.getBArea()+"','"
                    +rows.getBDescription()+"','"
                    +rows.getBClient()+"','"
                    +rows.getBStart()+"','"
                    +rows.getBFinish()+"','"
                    +rows.getBTarget()+"','"
                    +rows.getBStatus()+"','"
                    +rows.getBIsDelete()+"','"
                    +rows.getBDeleteDate()+"','"
                    +rows.getBDate()+"','"
                    +"b_client_name','"
                    +"count"+"','"
                    +"b_percent"+"','"
                    +rows.getBAreaLevel()+"','"
                    +rows.getBResult()
                    + "')";
            try {
                wdb.execSQL(query);
            }catch (Exception e){
                BE.TShort(e.toString());
                Log.d(TAG, "onError errorCode : " + e.toString());
            }finally {

            }
        }

        int sizes4=listsGQuestions.size();
        for(int i=0;i<sizes4;i++){
            GQuestions rows=listsGQuestions.get(i);
            String query = "INSERT INTO "+dbCon.TB_V_QUESTION+" ("
                    +dbCon.b_name_questioner+","
                    +dbCon.b_users_id+","
                    +dbCon.b_position+","
                    +dbCon.b_id_questioner+","
                    +dbCon.b_description+","
                    +dbCon.question_type+","
                    +dbCon.b_parent_id+","
                    +dbCon.b_title+","
                    +dbCon.b_option_list+","
                    +dbCon.b_question_id+","
                    +dbCon.b_survey_id+","
                    +dbCon.b_is_delete+","
                    +dbCon.b_label
                    +") VALUES ('"
                    +rows.getBNameQuestioner()+"','"
                    +rows.getBUsersId()+"','"
                    +rows.getBPosition()+"','"
                    +rows.getBIdQuestioner()+"','"
                    +rows.getBDescription()+"','"
                    +rows.getQuestionType()+"','"
                    +rows.getBParentId()+"','"
                    +rows.getBTitle()+"','"
                    +rows.getBOptionList()+"','"
                    +rows.getBQuestionId()+"','"
                    +rows.getBSurveyId()+"','"
                    +rows.getBIsDelete()+"','"
                    +"b_label"
                    + "')";
            try {
                wdb.execSQL(query);
            }catch (Exception e){
                BE.TShort(e.toString());
                Log.d(TAG, "onError errorCode : " + e.toString());
            }finally {

            }
        }
        String downquery = "INSERT INTO "+dbCon.TB_SETTING+" ("+dbCon.skey+","+dbCon.svalue+") VALUES ('downloads','downloads')";
        wdb.execSQL(downquery);
        wdb.close();
        rdb.close();
        tvLoad.setText("Save is success!");

        Intent returnIntent = new Intent();
        //returnIntent.putExtra("result","");
        setResult(1,returnIntent);
        this.finish();


        return true;
    }

    public boolean checkDownloadStateFT(SQLiteDatabase rdb){
        Cursor cur = null;
        String maxQuery="SELECT "+dbCon.svalue+" FROM "+dbCon.TB_SETTING+" WHERE "+dbCon.skey+"='downloads'";
        String  downloads= "";
        try {
            cur = rdb.rawQuery(maxQuery, null);
            if (cur != null) {
                cur.moveToFirst();
                downloads = cur.getString(cur.getColumnIndex("svalue"));
                return false;
            } else {
                downloads = "";
                return true;
            }
        }catch (Exception e){
            return true;
        }
    }

}
