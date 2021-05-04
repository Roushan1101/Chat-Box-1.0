package com.example.button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {
    private Button button;
    ImageView Imagev;
    Button download;
    Button SaveImg;
    Animation scaleup,scaledown;
    View k=null;

    public void save(View view){
        SaveImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    SaveImg.startAnimation(scaleup);
                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    SaveImg.startAnimation(scaledown);
                }

                return false;
            }
        });
    }

    public void tapped(View view) {

        download.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    download.startAnimation(scaleup);
                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    download.startAnimation(scaledown);
                }

                return false;
            }
        });

        new CountDownTimer(500, 1000) {
            @Override
            public void onTick(long l) {
                download.setText("Loading...");
                SaveImg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                download.setText("NEXT");
                SaveImg.setVisibility(View.VISIBLE);
            }
        }.start();

        DownloadTask task = new DownloadTask();
        Bitmap myImage;
        try {
            myImage = task.execute("https://source.unsplash.com/random").get();

            Imagev.setImageBitmap(myImage);


        }catch(Exception e){
            e.printStackTrace();
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        download=findViewById(R.id.download);
        Imagev=findViewById(R.id.imageView);
        scaleup= AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaledown=AnimationUtils.loadAnimation(this, R.anim.scale_down);
        SaveImg=findViewById(R.id.saveimg);

        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                tapped(k);
            }
        }.start();

        button=(Button) findViewById(R.id.saveimg);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openActivity2();
            }
        });

    }
    public void openActivity2(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public class DownloadTask extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url=new URL(urls[0]);
                HttpURLConnection Connection=(HttpURLConnection) url.openConnection();
                Connection.connect();
                InputStream in=Connection.getInputStream();
                Bitmap myBitmap;
                myBitmap = BitmapFactory.decodeStream(in);

                return myBitmap;
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
    }
}