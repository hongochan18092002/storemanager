package fpt.edu.duannnhom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
ProgressBar progressBar;
TextView textView;
int value;
Handler handler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar=findViewById(R.id.progressbarid);
        textView=findViewById(R.id.textview);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startProgress();
            }
        });
        thread.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },5010);

    }
    public void startProgress(){
        for (value=0;value<100;value=value+1){
            try {
                Thread.sleep(50);
                progressBar.setProgress(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
textView.setText(String.valueOf("LoaDing: "+value+"%..."));
                }
            });
        }


    }
}