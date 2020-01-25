package com.yaoxin.fmaster;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.ProgressBar;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;


public class MyAsyncTask extends AsyncTask<Integer,Integer,String>
{
    private TextView txt;
    private ProgressBar pgbar;
    private Random rnd = new Random();
    private Map<Integer, String> map = new HashMap<Integer,String>();
    private int last = -1;

    public MyAsyncTask(TextView txt,ProgressBar pgbar)
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

    }

    public class DelayOperator {
        //延时操作,用来模拟下载
        public void delay()
        {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //该方法不运行在UI线程中,主要用于异步操作,通过调用publishProgress()方法
    //触发onProgressUpdate对UI进行操作
    @Override
    protected String doInBackground(Integer... params) {
        DelayOperator dop = new DelayOperator();
        int i = 0;
        for (i = 1;i <= Integer.MAX_VALUE;i+=1)
        {
            dop.delay();
            publishProgress(i);
        }
        return  i + params[0].intValue() + "";
    }

    //在doBackground方法中,每次调用publishProgress方法都会触发该方法
    //运行在UI线程中,可对UI控件进行操作
    @Override
    protected void onProgressUpdate(Integer... values) {
        int a = rnd.nextInt(6);
        while (a == this.last)
            a = rnd.nextInt(6);
        last = a;
        txt.setText(this.map.get(a));

        int value = values[0];
        value %= 11;
        pgbar.setProgress(value*10);
    }
}