package com.yaoxin.fmaster;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.ProgressBar;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import android.media.MediaPlayer;

import com.yaoxin.fmaster.MP3Player;



public class MyAsyncTask extends AsyncTask<Integer,Integer,String>
{
    private TextView txt;
    private ProgressBar pgbar;
    private Random rnd = new Random();
    private Map<Integer, String> map = new HashMap<Integer,String>();
    private Map<Integer, Integer> map2 = new HashMap<Integer,Integer>();

    private int last = -1;
    private MP3Player mPlayer;

    private int time;


    public MyAsyncTask(int time, TextView txt,ProgressBar pgbar, MP3Player p)
    {
        super();
        this.txt = txt;
        this.pgbar = pgbar;

        this.map.put(0, "C");
        this.map.put(1, "D");
        this.map.put(2, "E");
        this.map.put(3, "F");
        this.map.put(4, "G");
        this.map.put(5, "A");
        this.map.put(6, "B");

        this.map2.put(0, R.raw.c);
        this.map2.put(1, R.raw.d);
        this.map2.put(2, R.raw.e);
        this.map2.put(3, R.raw.f);
        this.map2.put(4, R.raw.g);
        this.map2.put(5, R.raw.a);
        this.map2.put(6, R.raw.b);



        this.mPlayer = p;

        this.time = time;
    }

    public class DelayOperator {
        private int time;

        public DelayOperator(int time) {
            this.time = time;
        }
        //延时操作,用来模拟下载
        public void delay()
        {
            try {
                Thread.sleep(this.time);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //该方法不运行在UI线程中,主要用于异步操作,通过调用publishProgress()方法
    //触发onProgressUpdate对UI进行操作
    @Override
    protected String doInBackground(Integer... params) {
        DelayOperator dop = new DelayOperator(this.time);
        int i = 0;
        for (i = 1;i <= Integer.MAX_VALUE;i+=1)
        {
            publishProgress(i);
            dop.delay();
        }
        return  i + params[0].intValue() + "";
    }

    //在doBackground方法中,每次调用publishProgress方法都会触发该方法
    //运行在UI线程中,可对UI控件进行操作
    @Override
    protected void onProgressUpdate(Integer... values) {
        int a = rnd.nextInt(7);
        while (a == this.last)
            a = rnd.nextInt(7);
        last = a;
        txt.setText(this.map.get(a));

        this.mPlayer.init(this.map2.get(a));
        this.mPlayer.play();


        int value = values[0];
        value %= 11;
        pgbar.setProgress(value*10);
    }
}