package com.depaul.csc472.yt_calculator;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // store the number buttons and operator buttons into separate array list
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bPlus,bEqaul;
    TextView num;
    Integer val1,val2;
    Integer result = 0;
    boolean add = false;
    boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.num = (TextView) findViewById(R.id.displayNum);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);
        Button b9 = (Button) findViewById(R.id.button9);
        Button b0 = (Button) findViewById(R.id.button0);
        Button bPlus = (Button) findViewById(R.id.buttonPlus);
        Button bEqual = (Button) findViewById(R.id.buttonEqual);


        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "1");
                clear = false;
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "2");
                clear = false;
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "3");
                clear = false;
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "4");
                clear = false;
            }
        });
        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "5");
                clear = false;
            }
        });
        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "6");
                clear = false;
            }
        });
        b7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "7");
                clear = false;
            }
        });
        b8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "8");
                clear = false;
            }
        });
        b9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "9");
                clear = false;
            }
        });
        b0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(clear == true && add == false){
                    result = 0;
                    num.setText("");
                }
                num.setText(num.getText() + "0");
                clear = false;
            }
        });

        bPlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(num == null){
                    num.setText("");
                }
                else{
                    val1 = Integer.parseInt(num.getText() + "");
                    if(clear == false) {
                        result += val1;
                    }
                    add = true;
                    num.setText(null);
                }
            }
        });
        bEqual.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                val2 = Integer.parseInt(num.getText() + "");
                if(add == true){
                    num.setText(result + val2 + "");
                    result += val2;
                    add = false;
                    clear = true;
                }
            }
        });

    }
}
