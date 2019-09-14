package com.bpom.app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.adapters.DatabaseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixplicity.easyprefs.library.Prefs;
import com.bpom.app.utils.Cons;
public class DashboardActivity extends AppCompatActivity {

    Button btnSurvey, btnMaster, btnSync;
    Context c;
    FloatingActionButton fabs;

    private DatabaseAdapter dbCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!Prefs.getString("is_login","").equals("1")){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_dashboard);
        btnSurvey = findViewById(R.id.btnSurvey);
        btnMaster = findViewById(R.id.btnMaster);
        btnSync = findViewById(R.id.btnSync);
        fabs = findViewById(R.id.fab);
        c = this;

        dbCon=new DatabaseAdapter(this);

        if(Cons.DEBUG_MODE){
            btnSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(c,RespondenActivity.class));
                }
            });
            btnMaster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intents = new Intent();
                    intents.setClass(getBaseContext(), DownloadActivity.class);
                    startActivityForResult(intents,101);
                }
            });
            btnSync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(c, getString(R.string.err_d), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            if(checkDownloadStateFT(dbCon.getReadableDatabase())) {
                btnSurvey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BE.TShort("No data!");
                    }
                });
                btnSurvey.setTextColor(Color.parseColor("#005b9f"));
                btnMaster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(c, DownloadActivity.class));
                    }
                });
                btnSync.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BE.TShort("No data!");
                    }
                });
                btnSync.setTextColor(Color.parseColor("#005b9f"));
            }else{
                btnSurvey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(c,RespondenActivity.class));
                    }
                });
                btnMaster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BE.TShort("Can't download!");
                    }
                });
                btnMaster.setTextColor(Color.parseColor("#005b9f"));
                btnSync.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(c, getString(R.string.err_d), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }






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
                                        Prefs.clear();
                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                        finish();
                                    }

                                }).setNegativeButton("Tidak", null).show();
            }
        });
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if(resultCode==1){
                if(Cons.DEBUG_MODE){

                }else {
                    btnSurvey.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(c,RespondenActivity.class));
                        }
                    });
                    btnMaster.setTextColor(Color.parseColor("#0288d1"));
                    btnMaster.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            BE.TShort("Can't download!");
                        }
                    });
                    btnMaster.setTextColor(Color.parseColor("#005b9f"));
                    btnSync.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(c, getString(R.string.err_d), Toast.LENGTH_SHORT).show();
                        }
                    });
                    btnMaster.setTextColor(Color.parseColor("#0288d1"));
                }

            }else if(resultCode==2){

            }else if(resultCode==3){

            }

        }else if (requestCode == 102) {

        }
    }
}
