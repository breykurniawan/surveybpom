package com.bpom.app.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bpom.app.R;
import com.bpom.app.interfaces.IOnBackPressed;

public class WebFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IOnBackPressed {

    WebView mWebView;
    ProgressBar mProgressBar;
    SwipeRefreshLayout swipeLayout;
    Context c;
    boolean doubleBackToExitPressedOnce = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);

        mWebView = (WebView)view.findViewById(R.id.web_view);
        mProgressBar = (ProgressBar)view.findViewById(R.id.pb);
        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        c = getActivity();

        return view;
    }

//    public class FirebaseNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
//        Context ctx;
//        FirebaseNotificationOpenedHandler(Context context) {
//            ctx = context;
//        }
//        // This fires when a notification is opened by tapping on it.
//        @Override
//        public void notificationOpened(OSNotificationOpenResult result) {
//            OSNotificationAction.ActionType actionType = result.action.type;
//            JSONObject data = result.notification.payload.additionalData;
//            Toast.makeText(ctx, "Halo, saya klik notifikasi ya", Toast.LENGTH_SHORT).show();
//            if (data != null) {
//                String customKey = data.optString("customkey", null);
//                String lagikey = data.optString("lagikey", null);
//                if (customKey != null)
//                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
//                if (lagikey != null)
//                    Log.i("OneSignalExample", "lagikey set with value: " + lagikey);
//            }
//            if (actionType == OSNotificationAction.ActionType.ActionTaken)
//                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
////            Intent intent = new Intent(getActivity(), PenerimaActivity.class);
////            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
////            ctx.startActivity(intent);
//        }
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeLayout.setOnRefreshListener(this);
        renderWebPage(getString(R.string.urls));
    }

    @Override
    public void onRefresh() {
        swipeLayout.setRefreshing(true);
        renderWebPage(getString(R.string.urls));
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        ButterKnife.bind(this,view);
//        c = getActivity();
//        renderWebPage("akugamer.com");
//        return view;
//    }

    protected void renderWebPage(String urlToRender){
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mWebView.loadUrl("file:///android_asset/index.html");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started
                // Visible the progressbar
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                // Do something when page loading finished
//                Toast.makeText(mContext,"Page Loaded.",Toast.LENGTH_SHORT).show();
                swipeLayout.setRefreshing(false);
            }

        });

        /*
            WebView
                A View that displays web pages. This class is the basis upon which you can roll your
                own web browser or simply display some online content within your Activity. It uses
                the WebKit rendering engine to display web pages and includes methods to navigate
                forward and backward through a history, zoom in and out, perform text searches and more.

            WebChromeClient
                 WebChromeClient is called when something that might impact a browser UI happens,
                 for instance, progress updates and JavaScript alerts are sent here.
        */
        mWebView.setWebChromeClient(new WebChromeClient(){
            /*
                public void onProgressChanged (WebView view, int newProgress)
                    Tell the host application the current progress of loading a page.

                Parameters
                    view : The WebView that initiated the callback.
                    newProgress : Current page loading progress, represented by an integer
                        between 0 and 100.
            */
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                mProgressBar.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        // Enable the javascript
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Render the web page
        mWebView.loadUrl(urlToRender);
    }

    @Override
    public boolean onBackPressed() {
        if (true) {
            //action not popBackStack
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                if (doubleBackToExitPressedOnce) {
                    getActivity().finish();
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(getActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 2000);
            }
            return true;
        } else {
            return false;
        }
    }
}