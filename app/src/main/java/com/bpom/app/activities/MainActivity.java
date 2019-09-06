package com.bpom.app.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.bpom.app.R;
import com.bpom.app.fragments.Webs;
import com.bpom.app.interfaces.IOnBackPressed;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }

    BottomNavigationView bottomNavigationView;
    Context c;
    public static String FRAGMENT_DATA = "transaction_data";

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static int FCR = 1;
    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        }
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigationView) ;
        c = this;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        fragment = new Webs();
        if(null != bundle.getString("urls")) {
            bundle.getString("urls");
            fragment.setArguments(bundle);
            loadFragment(fragment);
        }else {
            bundle.putString("urls", getString(R.string.urls));
            fragment.setArguments(bundle);
            loadFragment(fragment);
        }
//        loadFragment(new WebFragment());
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            Uri[] results = null;
//
//            //Check if response is positive
//            if (resultCode == RESULT_OK) {
//                if (requestCode == FCR) {
//
//                    if (null == mUMA) {
//                        return;
//                    }
//                    if (intent == null) {
//                        //Capture Photo if no image available
//                        if (mCM != null) {
//                            results = new Uri[]{Uri.parse(mCM)};
//                        }
//                    } else {
//                        String dataString = intent.getDataString();
//                        if (dataString != null) {
//                            results = new Uri[]{Uri.parse(dataString)};
//                        }
//                    }
//                }
//            }
//            mUMA.onReceiveValue(results);
//            mUMA = null;
//        } else {
//
//            if (requestCode == FCR) {
//                if (null == mUM) return;
//                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
//                mUM.onReceiveValue(result);
//                mUM = null;
//            }
//        }
//    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                fragment = new Webs();
                bundle.putString("urls", getString(R.string.urls));
                fragment.setArguments(bundle);
                break;
            case R.id.nav_vid:
                fragment = new Webs();
                bundle.putString("urls", getString(R.string.urls_focus));
                fragment.setArguments(bundle);
                break;
            case R.id.nav_menu:
                fragment = new Webs();
                bundle.putString("urls", getString(R.string.urls_regional));
                fragment.setArguments(bundle);
                break;
            case R.id.nav_video:
                fragment = new Webs();
                bundle.putString("urls", getString(R.string.urls_video));
                fragment.setArguments(bundle);
                break;
            case R.id.nav_infografis:
                fragment = new Webs();
                bundle.putString("urls", getString(R.string.urls_infografis));
                fragment.setArguments(bundle);
                break;
        }
        return loadFragment(fragment);
    }

    public class Callback extends WebViewClient {
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(getApplicationContext(), "Failed loading app!", Toast.LENGTH_SHORT).show();
        }
    }
}