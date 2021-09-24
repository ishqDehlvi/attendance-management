package com.sujin.clickableimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class WebActivity extends AppCompatActivity {

    WebView mWebview;
    WebSettings webSettings;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        mWebview = findViewById(R.id.web_view);

        firebaseAuth = FirebaseAuth.getInstance();
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview.loadUrl("http://192.168.1.28:5500/student.html?id="+firebaseAuth.getCurrentUser().getUid());

        /*mWebview = (WebView) findViewById(R.id.web_view);
        webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebview.loadUrl("http://www.google.com");
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.setWebViewClient(new MyAppWebViewClient());*/

    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.log_out:

                firebaseAuth.signOut();
                startActivity(new Intent(WebActivity.this, MainActivity.class));
                finish();
                break;

        }
        return false;
    }
}
