package com.yaoxin.fmaster;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.widget.Toast;

//import com.example.examplemp3.MainActivity;

import java.io.IOException;
import android.net.Uri;


public class MP3Player {

    private MediaPlayer mp;
    private String path;
    private int length;
    private Context ctx;
    private Boolean first = true;



    public MP3Player(Context ctx) {

        // 获取内部存储器绝对路径
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(ctx, path, Toast.LENGTH_LONG).show();

        this.ctx = ctx;
    }

    public int getPosition() {
        // 获取当前位置
        return mp.getCurrentPosition();
    }

    public int getLength() {
        return length;
    }

    public void init(int id) {
        try {

            if (first) {
                first = false;
            } else {
                mp.reset();
            }
            mp = new MediaPlayer();

            // 存储在SD卡或其他文件路径下的媒体文件
            try {
                Uri setDataSourceuri = Uri.parse("android.resource://com.yaoxin.fmaster/"+id);
                mp.setDataSource(this.ctx, setDataSourceuri);

            } catch (Exception e) {
                String ss = e.getMessage();
                int a = 10;
            }

            // 音乐文件准备
            mp.prepare();
            // 音乐文件长度
            length = mp.getDuration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        mp.start();
    }

    public void pause() {
        mp.pause();
    }

    public void stop() {
        mp.stop();
    }

    public void destroy() {
        mp.release();
    }

}
