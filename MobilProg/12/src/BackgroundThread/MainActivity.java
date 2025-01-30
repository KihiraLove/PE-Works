package com.example.backgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button buttonStart;

    private Handler mainHandler = new Handler();

    private volatile boolean stopThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.button_start_thread);
    }

    public void startThread(View view){
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(10);
        new Thread(runnable).start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
    }

    public void stopThread(View view){
        stopThread = true;
    }

    class ExampleThread extends Thread{

        private int seconds;

        ExampleThread(int seconds){
            this.seconds = seconds;
        }

        @Override
        public void run() {
            super.run();

            for(int i = 0; i < seconds; i++){
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ExampleRunnable implements Runnable{

        private int seconds;

        ExampleRunnable(int seconds){
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for(int i = 0; i < seconds; i++){
                if(stopThread)
                    return;
                if(i == 5) {

                    Handler threadHandler = new Handler(Looper.getMainLooper());
                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStart.setText("50%");
                        }
                    });

                    /*buttonStart.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStart.setText("50%");
                        }
                    });*/

                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonStart.setText("50%");
                        }
                    });*/
                }
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}