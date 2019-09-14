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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.adapters.DatabaseAdapter;
import com.bpom.app.adapters.QuestionAdapter;
import com.bpom.app.models.areas.GArea;
import com.bpom.app.models.questions.GQuestions;
import com.bpom.app.utils.Cons;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    BE.LoadingPrimary pd;
    Context c;

    private static String TAG;
    private List<GQuestions> lists;
    private RecyclerView rv;

    private DatabaseAdapter dbCon;
    QuestionAdapter adapters;
    String ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        TAG = getApplicationContext().getClass().getSimpleName();
        getSupportActionBar().setTitle("List Pertanyaan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        c = this;
        pd = new BE.LoadingPrimary(c);

        if(getIntent().getExtras()!=null) {
            Bundle bundle = getIntent().getExtras();
            ids = bundle.getString("id");
        }

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        rv.setLayoutManager(new LinearLayoutManager(this)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        lists = new ArrayList<>();
        adapters = new QuestionAdapter(c, lists);
        rv.setAdapter(adapters);

        pd.show();
        dbCon=new DatabaseAdapter(this);

        loadListGQuestionsFT();
       /* AndroidNetworking.get(Cons.API_QUESTION+ids)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(GQuestions.class, new ParsedRequestListener<List<GQuestions>>() {
                    @Override
                    public void onResponse(List<GQuestions> r) {
                        pd.dismiss();
                        if(r.size()>0) {
                            lists = r;
                            adapters = new QuestionAdapter(c,lists);
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
    public void loadListGQuestionsFT(){
        SQLiteDatabase rdb = dbCon.getReadableDatabase();
        /*SELECT * FROM v_question a
			LEFT JOIN v_questioner b ON a.b_questioner_id = b.b_id
			WHERE a.b_survey_id = 37 and a.b_questioner_id = ?
			ORDER BY b_questioner_name,b_parent_id,b_position*/
        String query="SELECT * FROM "+ DatabaseAdapter.TB_V_QUESTION
                +" WHERE "+DatabaseAdapter.b_survey_id+"='"+Cons.gID+"'"
                +" AND "+DatabaseAdapter.b_id_questioner+"='"+ids+"'"
                +" ORDER BY "+DatabaseAdapter.b_name_questioner
                +","+DatabaseAdapter.b_parent_id
                +","+DatabaseAdapter.b_position;
        Cursor cur=rdb.rawQuery(query,null);
        try {
            if (cur.moveToFirst()) {
                do {
                    GQuestions rows=new GQuestions();
                    rows.setBNameQuestioner(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_name_questioner)));
                    rows.setBUsersId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_users_id)));
                    rows.setBPosition(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_position)));
                    rows.setBIdQuestioner(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_id_questioner)));
                    rows.setBDescription(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_description)));
                    rows.setQuestionType(cur.getString(cur.getColumnIndex(DatabaseAdapter.question_type)));
                    rows.setBParentId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_parent_id)));
                    rows.setBTitle(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_title)));
                    rows.setBOptionList(cur.getString(cur.getColumnIndex(DatabaseAdapter.b_option_list)));
                    rows.setBQuestionId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_question_id)));
                    rows.setBSurveyId(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_survey_id)));
                    rows.setBIsDelete(cur.getInt(cur.getColumnIndex(DatabaseAdapter.b_is_delete)));
                    lists.add(rows);
                }while (cur.moveToNext());
                adapters = new QuestionAdapter(c,lists);
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
