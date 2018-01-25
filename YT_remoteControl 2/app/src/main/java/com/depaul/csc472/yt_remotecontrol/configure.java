package com.depaul.csc472.yt_remotecontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class configure extends AppCompatActivity {
    int radioPos;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bPlus,bMinus,bF1,bF2,bF3;
    RadioButton rL,rM,rR;
    Integer val1,val2;
    String labelConfi;
    Button[] btsList;
    TextView channelNum;
    Integer counter = 3;
    EditText labelEdit;
//    private static final int FAVORITE_SET = 100; // request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        // saveBtn
        Button save = (Button) findViewById(R.id.saveBtnID);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(configure.this, MainActivity.class);
                intent.putExtra("Position", radioPos);
                intent.putExtra("Label",labelEdit.getText());
                intent.putExtra("Channel", channelNum.getText());
//                startActivityForResult(intent, FAVORITE_SET);
                startActivity(intent);
            }
        });

        // cancelBtn
        Button cancel = (Button) findViewById(R.id.cancelBtnID);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(configure.this, MainActivity.class);
                intent.putExtra("Position", 0);
                intent.putExtra("Label","");
                intent.putExtra("Channel", "");
//                startActivityForResult(intent, FAVORITE_SET);
                startActivity(intent);
            }
        });

        // Radio Button Group
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioBtnPos);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                rL = (RadioButton) findViewById(R.id.leftID);
                rM = (RadioButton) findViewById(R.id.middleID);
                rR = (RadioButton) findViewById(R.id.rightID);
                if(rL.isChecked()) radioPos = 1;
                else if (rM.isChecked()) radioPos = 2;
                else if (rR.isChecked()) radioPos = 3;
            }
        });


        // Label Entered
        labelEdit = (EditText) findViewById(R.id.labelEditID);
        labelEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(labelEdit.getWindowToken(), 0);

                    labelConfi = labelEdit.getText().toString();
                    if(labelConfi.length() == 1){
                        Toast.makeText(getApplicationContext(), "The label is too short.", Toast.LENGTH_LONG).show();
                    }
                    else if(labelConfi.length()>4){
                        Toast.makeText(getApplicationContext(), "The label is too long.", Toast.LENGTH_LONG).show();
                    }
                    handled = true;
                }
                return handled;
            }
        });



        // Channel Entered
        this.channelNum = (TextView) findViewById(R.id.channelNumID);
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
                if(val2>1){
                    val2 -= 1;
                }
                setPlusOrMinus(val2);
            }
        });
    }

    public void setButton(final Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter += 1;
                if(counter > 3){
                    channelNum.setText("");
                    counter = 1;
                }
                else if (counter == 3 ){
                    if(b == b0){
                        Toast.makeText(getApplicationContext(), "The channel number should be in range of 1-999", Toast.LENGTH_LONG).show();
                    }
                }
                String channel = channelNum.getText() + b.getText().toString();
                channelNum.setText(channel);

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


}
