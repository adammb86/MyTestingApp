package com.example.adammb.mytestingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSetValue;
    private TextView tvText;
    private ArrayList<String> names;
    private DelayAsync delayAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText=(TextView)findViewById(R.id.tv_text);
        btnSetValue=(Button)findViewById(R.id.btn_set_nilai);

        btnSetValue.setOnClickListener(this);

        names=new ArrayList<>();
        names.add("Adam Mukharil Bachtiar");
        names.add("Dian Dharmayanti");
        names.add("Ramdhan Rizki");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_set_nilai){
            String name="";
            for(int i=0;i<=names.size();i++){
                name +=names.get(i)+"\n";
            }

            tvText.setText(name);

           delayAsync=new DelayAsync();
           delayAsync.execute();
        }

    }

    private class DelayAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(3000000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("DelayAsync","Done");
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            Log.d("DelayAsync","Cancelled");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(delayAsync!=null){
            if(delayAsync.getStatus().equals(AsyncTask.Status.RUNNING)){
                delayAsync.cancel(true);
            }
        }
    }
}
