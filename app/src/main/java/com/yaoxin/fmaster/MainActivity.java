package com.yaoxin.fmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.view.View;

import com.yaoxin.fmaster.MP3Player;

import android.media.MediaPlayer;

// https://www.runoob.com/w3cnote/android-tutorial-ansynctask.html
public class MainActivity extends AppCompatActivity {

    private TextView txttitle;
    private ProgressBar pgbar;
    private Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       //MediaPlayer p = MediaPlayer.create(getApplicationContext(), R.raw.a);
        // p.start();

        String[] permission = {"android.permission.READ_EXTERNAL_STORAGE"};
        // 获取动态权限
        this.requestPermissions(permission, 0);
        txttitle = (TextView)findViewById(R.id.txttitle);
        pgbar = (ProgressBar)findViewById(R.id.pgbar);
        btnupdate = (Button)findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myTask = new MyAsyncTask(txttitle,pgbar,  new MP3Player(MainActivity.this));
                myTask.execute(1000);
            }
        });
    }
}
