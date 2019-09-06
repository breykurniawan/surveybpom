package com.bpom.app;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.androidnetworking.AndroidNetworking;
import com.pixplicity.easyprefs.library.Prefs;

public class BE extends Application {

    public static String appVersion = BuildConfig.VERSION_NAME;

    private static Context appContext;

    private static BE instance;

    public BE() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName("sp")
                .setUseDefaultSharedPreference(true)
                .build();


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        AndroidNetworking.enableLogging();

        // OneSignal Initialization
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();

//        OneSignal.startInit(this).setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
//            @Override
//            public void notificationOpened(OSNotificationOpenResult result) {
//
//                String launchURL = result.notification.payload.launchURL;
//
//                if (launchURL != null) {
//                    Log.d("urls", "Launch URL: " + launchURL);
//
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("urls", launchURL);
//                    startActivity(intent);
//
//                } else {
//                    Log.d("urls", "Launch URL not found");
//
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
//            }
//        }).init();
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static void initAnimFly(View view){
        ValueAnimator vAnimator = ObjectAnimator.ofFloat(view, "alpha", 1,0f);
        vAnimator.setDuration(1000);
        vAnimator.setRepeatMode(ValueAnimator.REVERSE);
        vAnimator.setRepeatCount(1);

        ValueAnimator vAnimator2 = ObjectAnimator.ofFloat(view, "translationX", 100f);
        vAnimator2.setDuration(1000);
        vAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        vAnimator2.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(vAnimator,vAnimator2);
        set.setStartDelay(0);

        set.start();

    }

    public static class LoadingPrimary extends Dialog {

        private ImageView iv;
        private TextView tv;

        public LoadingPrimary(Context context) {
            super(context, R.style.MyDialogLoading);
            WindowManager.LayoutParams wlmp = getWindow().getAttributes();
            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            getWindow().setAttributes(wlmp);
            setTitle(null);
            setCancelable(false);
            setOnCancelListener(null);
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            iv = new ImageView(context);
            iv.setImageResource(R.mipmap.ic_launcher);
            initAnimFly(iv);
            layout.addView(iv, params);

//            TextView tv = new TextView(context);
//            tv.setText("Please Wait");
//            tv.setTypeface(Typeface.SANS_SERIF);
//            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//            tv.setTextColor(context.getResources().getColor(android.R.color.white));
//            layout.addView(tv, params);

            addContentView(layout, params);
        }
    }

    public static class Loading extends Dialog {

        private ImageView iv;
        private TextView tv;

        public Loading() {
            super(getAppContext(),R.style.MyDialogLoading);
            WindowManager.LayoutParams wlmp = getWindow().getAttributes();
            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            getWindow().setAttributes(wlmp);
            getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
            setTitle(null);
            setCancelable(false);
            setOnCancelListener(null);
            LinearLayout layout = new LinearLayout(getAppContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            iv = new ImageView(getAppContext());
            iv.setImageResource(R.mipmap.ic_launcher);
            initAnimFly(iv);
            layout.addView(iv, params);

//            TextView tv = new TextView(context);
//            tv.setText("Please Wait");
//            tv.setTypeface(Typeface.SANS_SERIF);
//            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//            tv.setTextColor(context.getResources().getColor(android.R.color.white));
//            layout.addView(tv, params);

            addContentView(layout, params);
        }
    }

    //toast
    public static void TShort(String title) {
//        if (title.toLowerCase().contains("error")) {
//            Toast.makeText(getAppContext(), getAppContext().getString(R.string.err_server), Toast.LENGTH_SHORT).show();
//        } else {
            Toast.makeText(getAppContext(), title, Toast.LENGTH_SHORT).show();
//        }
    }

//    public static void TLong(String title) {
//        if (title.toLowerCase().contains("failed")) {
//            Toast.makeText(getAppContext(), getAppContext().getString(R.string.err_server), Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getAppContext(), title, Toast.LENGTH_LONG).show();
//        }
//    }

    public static void showToastContext(Context context, String title) {
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
    }
}