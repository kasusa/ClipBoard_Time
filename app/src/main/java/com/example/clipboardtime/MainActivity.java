package com.example.clipboardtime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate; // import the LocalDate class
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    String theDate;
    String theTime;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    Button bt1;
    Button bt2;
    Button bt3;
    SeekBar seekBar;
    ImageView image;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.tmp);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        // seek bar and image for rotating and fun!
        image = (ImageView)findViewById(R.id.imageView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                float degree = (float) (progress *1.8);
                rotate((int)degree);
                tv3.setText("progress: "+ progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        gettime();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        gettime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void gettime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter datefromat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         theDate = myDateObj.format(datefromat);
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
         theTime = myDateObj.format(timeformat);

         tv1.setText(theDate);
         tv2.setText(theTime);
    }

    public void copydate(View view) {
        Snackbar.make(view, "日期"+theDate+"已成功复制", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", theDate);
        clipboard.setPrimaryClip(clip);
    }

    public void copytime(View view) {
        Snackbar.make(view, "时间"+theTime+"已成功复制", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", theTime);
        clipboard.setPrimaryClip(clip);
    }

    public void copyBoth(View view) {
        Snackbar.make(view, "时间和日期"+theDate+" "+theTime+"已成功复制", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", theDate+" "+theTime);
        clipboard.setPrimaryClip(clip);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Refreash(View view) {
        Snackbar.make(view, "时间已刷新✔", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        gettime();
    }

    public void exit(View view) {
        finish();
    }

    public void rotate(int degree){
        image.setPivotX(image.getWidth()/2);
        image.setPivotY(image.getHeight()/2);//支点在图片中心
        image.setRotation(degree);
    }
}