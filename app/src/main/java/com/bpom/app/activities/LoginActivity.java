package com.bpom.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.models.login.PLogin;
import com.bpom.app.utils.Cons;
import com.pixplicity.easyprefs.library.Prefs;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Context c;
    EditText etUser, etPass;
    BE.LoadingPrimary pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        c = this;
        pd = new BE.LoadingPrimary(c);
        btnLogin = (Button)findViewById(R.id.btnSignIn);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        etUser.setText("jabar01");
        etPass.setText("jabar01");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUser = etUser.getText().toString();
                String sPass = etPass.getText().toString();
                if(!sUser.isEmpty() && !sPass.isEmpty()){
                    pd.show();
                    AndroidNetworking.post(Cons.API_LOGIN)
                            .addHeaders(Cons.API_HEADER_K, Cons.API_HEADER_V)
                            .addBodyParameter("b_user_name", etUser.getText().toString())
                            .addBodyParameter("b_user_pass", etPass.getText().toString())
                            .setPriority(Priority.HIGH)
                            .build()
                            .getAsObject(PLogin.class, new ParsedRequestListener<PLogin>() {
                                @Override
                                public void onResponse(PLogin user) {
                                    pd.dismiss();
                                    if (user.isStatus()) {
                                        Prefs.putString("is_login","1");
                                        Prefs.putString(Cons.USER_ID, String.valueOf(user.getData().getBId()));
                                        Prefs.putString(Cons.USER_NAME, user.getData().getBUserName());
                                        Prefs.putString(Cons.USER_PERSON, user.getData().getBFullName());
                                        Prefs.putString(Cons.USER_EMAIL, user.getData().getBEmail());
                                        startActivity(new Intent(c, DashboardActivity.class));
                                        finish();
                                    } else {
                                        BE.TShort(user.getMessage());
                                    }
                                }

                                @Override
                                public void onError(ANError error) {
                                    pd.dismiss();
                                    if (error.getErrorCode() != 0) {
                                        // get parsed error object (If ApiError is your class)
                                        PLogin apiError = error.getErrorAsObject(PLogin.class);
                                        BE.TShort(apiError.getMessage());
                                    } else {
                                        BE.TShort(error.getErrorDetail());
                                    }
                                }
                            });
                }else{
                    BE.TShort(getString(R.string.err_blank));
                }
            }
        });
    }
}
