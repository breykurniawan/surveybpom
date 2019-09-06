package com.bpom.app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bpom.app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixplicity.easyprefs.library.Prefs;

public class DashboardActivity extends AppCompatActivity {

    Button btnSurvey, btnMaster, btnSync;
    Context c;
    FloatingActionButton fabs;

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
        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(c,SurveyActivity.class));
            }
        });
        btnMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, getString(R.string.err_d), Toast.LENGTH_SHORT).show();
            }
        });
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, getString(R.string.err_d), Toast.LENGTH_SHORT).show();
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
                                        Prefs.clear();
                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                        finish();
                                    }

                                }).setNegativeButton("Tidak", null).show();
            }
        });
    }
}
