package com.depaul.csc472.yt_remotecontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class my_dvr extends AppCompatActivity {
    Button play, stop, pause, forward, rewind, record;
    Button[] allBtsList;
    TextView dvrState;
    static private final String TAG = "my_dvr";

    public void onSwitchClicked(View view){

        // Power Switch
        Switch sw = (Switch) view;
        Log.d(TAG, "onSwitchClicked() " + sw.getTag() + " " + (sw.isChecked() ? "on" : "off"));
        final TextView powerSwitch = (TextView) findViewById(R.id.powerStatus);
        powerSwitch.setText((sw.isChecked() ? "ON" : "OFF"));

        // Disabled remote when power is off
        if ((sw.isChecked() ? "ON" : "OFF") == "OFF"){
            allBtsList = new Button[]{play, stop, pause, forward, rewind, record};
            for(Button bts : allBtsList){
                bts.setEnabled(false);
            }
        }
        else if((sw.isChecked() ? "ON" : "OFF") == "ON"){
            dvrState.setText("Stopped");
            for(Button bts : allBtsList){
                bts.setEnabled(true);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dvr);

        // Switch to DVR
        Button button1 = (Button) findViewById(R.id.switch_to_tv);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(my_dvr.this, MainActivity.class);
                startActivity(intent);
            }
        });


        dvrState = (TextView) findViewById(R.id.stateTextview);
        play = (Button) findViewById(R.id.playBtn);
        stop = (Button) findViewById(R.id.stopBtn);
        pause = (Button) findViewById(R.id.pauseBtn);
        forward = (Button) findViewById(R.id.forwardBtn);
        rewind = (Button) findViewById(R.id.rewindBtn);
        record = (Button) findViewById(R.id.recordBtn);

        play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!(dvrState.getText().toString() == "Recording")) {
                    dvrState.setText("Playing");
                }
                else{
                    Toast.makeText(getApplicationContext(),"The requested operation is not possible.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        pause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(dvrState.getText().toString() == "Playing") {
                    dvrState.setText("Pausing");
                }
                else{
                    Toast.makeText(getApplicationContext(),"The requested operation is not possible.",Toast.LENGTH_SHORT).show();
                }


            }
        });

        forward.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(dvrState.getText().toString() == "Playing") {
                    dvrState.setText("Forwarding");
                }
                else{
                    Toast.makeText(getApplicationContext(),"The requested operation is not possible.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        rewind.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(dvrState.getText().toString() == "Playing") {
                    dvrState.setText("Rewinding");
                }
                else{
                    Toast.makeText(getApplicationContext(),"The requested operation is not possible.",Toast.LENGTH_SHORT).show();
                }

            }
        });
        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dvrState.setText("Stopped");

            }
        });

        record.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(dvrState.getText().toString() == "Stopped") {
                    dvrState.setText("Recording");
                }
                else{
                    Toast.makeText(getApplicationContext(),"The requested operation is not possible.",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
