package com.example.button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    Connection_Detector check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //removing top bar or action bar from top
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_main);

        check=new Connection_Detector(this);
        if(check.isConnected()){
            Toast.makeText(this, "Connected !", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Check Internet Connectivity !", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Restart Application", Toast.LENGTH_SHORT).show();
        }


        WebView web=(WebView) findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://www.roushan.xyz/chat");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    button=(Button) findViewById(R.id.play);
    button.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            openActivity2();
        }
    });
    }
    public void openActivity2(){
        Intent intent=new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}