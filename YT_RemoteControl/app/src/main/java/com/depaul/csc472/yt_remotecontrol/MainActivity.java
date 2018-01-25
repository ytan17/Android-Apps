package com.depaul.csc472.yt_remotecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bPlus,bMinus,bF1,bF2,bF3;
    Button[] btsList, allBtsList;
    TextView channelNum;
    Integer val1,val2,vol;
    Integer counter = 3;
    static private final String TAG = "MainActivity";


    public void onSwitchClicked(View view){

        // Power Switch
        Switch sw = (Switch) view;
        Log.d(TAG, "onSwitchClicked() " + sw.getTag() + " " + (sw.isChecked() ? "on" : "off"));
        final TextView powerSwitch = (TextView) findViewById(R.id.power_state);
        powerSwitch.setText((sw.isChecked() ? "ON" : "OFF"));

        // Disabled remote when power is off
        if ((sw.isChecked() ? "ON" : "OFF") == "OFF"){
            findViewById(R.id.volume_SeekBar).setEnabled(false);
            allBtsList = new Button[]{b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bPlus,bMinus,bF1,bF2,bF3};
            for(Button bts : allBtsList){
                bts.setEnabled(false);
            }
        }
        else if((sw.isChecked() ? "ON" : "OFF") == "ON"){
            findViewById(R.id.volume_SeekBar).setEnabled(true);
            for(Button bts : allBtsList){
                bts.setEnabled(true);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Volume SeekBar
        final TextView volume = (TextView) findViewById(R.id.volume_state);
        final SeekBar volumeSeekBar = (SeekBar) findViewById(R.id.volume_SeekBar);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    volumeSeekBar.setEnabled(true);
                    Log.d(TAG, "onProgressChanged");
                    vol = i;
                    volume.setText(vol.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch");
            }
        });

        // Current Channel and All Buttons
        this.channelNum = (TextView) findViewById(R.id.channel_state);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);
        b0 = (Button) findViewById(R.id.button0);
        bPlus = (Button) findViewById(R.id.buttonPlus);
        bMinus = (Button) findViewById(R.id.buttonMinus);
        bF1 = (Button) findViewById(R.id.buttonF1);
        bF2 = (Button) findViewById(R.id.buttonF2);
        bF3 = (Button) findViewById(R.id.buttonF3);

        btsList = new Button[]{b1, b2, b3, b4, b5,b6,b7,b8,b9,b0};
        for(Button bts : btsList){
            setButton(bts);
        }


        bPlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                val1 = Integer.parseInt(channelNum.getText() + "");
                if(val1<999){
                    val1+=1;
                }
                setPlusOrMinus(val1);
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                val2 = Integer.parseInt(channelNum.getText() + "");
                if(val2>0){
                    val2 -= 1;
                }
                setPlusOrMinus(val2);
            }
        });

        bF1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setFavChannel("111");
            }
        });
        bF2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setFavChannel("222");
            }
        });
        bF3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setFavChannel("333");
            }
        });
    }

    // used for Button 1 to 9
    public void setButton(final Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter += 1;
                if(counter > 3){
                    channelNum.setText("");
                    counter = 1;
                }
                channelNum.setText(channelNum.getText() + b.getText().toString());
            }
        });
    }

    // used for CH+ and CH-
    public void setPlusOrMinus(Integer val){
        if(val<10){
            channelNum.setText("00" + val.toString());
        }
        else if (val<100){
            channelNum.setText("0" + val.toString());
        }
        else {
            channelNum.setText(val.toString());
        }
    }

    // used for Favorite Channel
    public void setFavChannel(String s){
        if(counter == 0){
            channelNum.setText("");
        }
        channelNum.setText(s);
    }

}
