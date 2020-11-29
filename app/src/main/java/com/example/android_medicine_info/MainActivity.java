package com.example.android_medicine_info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView mainImage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImage1 = (ImageView)findViewById(R.id.mainImage1);
        mainImage1.setImageResource(R.drawable.online_pharmacy);

        Handler handler = new Handler() {
            public void handleMessage (Message msg){
                super.handleMessage(msg);

                startActivity(new Intent(MainActivity.this,homeMain.class));
            }
        };
        handler.sendEmptyMessageDelayed(0,2000);
    }
}
