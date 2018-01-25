package com.depaul.csc472.yt_pointofscale;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Double tot = 0.00;
    ArrayList<String> OrderList = new ArrayList<>();
    String record;
    Map<String,Double> hm = null;
    double matchedPrice = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText priceEntered = (EditText) findViewById(R.id.priceEntered);
        priceEntered.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(message.getWindowToken(), 0);

                    calculateTotal();
                    handled = true;
                }
                return handled;
            }
        });

        Button total = (Button) findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculateTotal();
            }
        });

        Button newOrder = (Button) findViewById(R.id.newOrder);
        newOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createNewOrder();
            }
        });

        Button newItem = (Button) findViewById(R.id.newItem);
        newItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createNewItem();
            }
        });

        // create the HashMap
        hm = createMap();
        Set<String> keys = hm.keySet();
        String[] allKeys = keys.toArray(new String[keys.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, allKeys);
        final AutoCompleteTextView itemEntered = (AutoCompleteTextView) findViewById(R.id.itemEntered);
        itemEntered.setAdapter(adapter);

        itemEntered.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Double priceMatched = hm.get(itemEntered.getText().toString());
                matchedPrice = priceMatched;
                String priceMatchedStr = String.format("%.2f",priceMatched);

                EditText priceEntered = (EditText)findViewById(R.id.priceEntered);
                priceEntered.setText("$"+ priceMatchedStr);
            }
        });

    }

    private static Map<String, Double> createMap()
    {
        Map<String,Double> myMap = new HashMap<String,Double>();
        myMap.put("Pastrami", 6.70);
        myMap.put("Turkey Club",7.20);
        myMap.put("Grilled Chicken Cheddar",5.95);
        myMap.put("A Wreck",5.60);
        myMap.put("Turkey Breast",5.40);
        myMap.put("Italian",5.60);
        myMap.put("Mediterranean",5.70);
        myMap.put("Mediterranean chicken",6.70);
        myMap.put("Smoked Ham",5.40);
        myMap.put("Roast Beef",5.55);
        myMap.put("Chicken Salad",5.50);
        myMap.put("Tuna Salad",5.50);
        myMap.put("Meatball",5.50);
        myMap.put("Pizza Sandwich",5.65);
        myMap.put("Clubby",6.65);

        return myMap;
    }



    public void calculateTotal(View v) {
        calculateTotal();
    }

    protected void calculateTotal() {
        // local variable
        Double price;
        Double quantity = 1.00;

        // dismiss the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        // findViewById
        EditText itemEntered = (EditText) findViewById(R.id.itemEntered);
        EditText quantityEntered = (EditText) findViewById(R.id.quantityEntered);
        EditText priceEntered = (EditText)findViewById(R.id.priceEntered);

        if(!quantityEntered.getText().toString().isEmpty()) {
            quantity = Double.parseDouble(quantityEntered.getText().toString());
        }

        if(matchedPrice != 0.00){
            price = matchedPrice;
        }
        else{
            price = Double.parseDouble(priceEntered.getText().toString());
        }
        Double totalPrice = quantity * price;
        tot += totalPrice;
        String totalPriceStr = String.format("%.2f",tot);

        String itemEnteredStr = itemEntered.getText().toString();
        String quantityStr = quantity.toString();
        record = itemEnteredStr + " x" +quantityStr;

        TextView totalCalculated = (TextView) findViewById(R.id.totalCalculated);
        totalCalculated.setText("$" + totalPriceStr);

        // add the item in record
        OrderList.add(record);

        // display the record
        TextView itemRecord = (TextView) findViewById(R.id.itemRecord);
        StringBuilder builder = new StringBuilder();
        for (String item : OrderList) {
            builder.append(item + "\n");
        }
        itemRecord.setText(builder.toString());

    }

    public void clear(){
        EditText itemEntered = (EditText) findViewById(R.id.itemEntered);
        EditText quantityEntered = (EditText) findViewById(R.id.quantityEntered);
        EditText priceEntered = (EditText)findViewById(R.id.priceEntered);

        itemEntered.setText("");
        quantityEntered.setText("");
        priceEntered.setText("");
    }

    public void createNewOrder(View v) {
        createNewOrder();
    }

    protected void createNewOrder() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        OrderList = new ArrayList<>();

        clear();
        TextView totalCalculated = (TextView) findViewById(R.id.totalCalculated);
        totalCalculated.setText("$0.00");
        TextView itemRecord = (TextView) findViewById(R.id.itemRecord);
        itemRecord.setText("");
        tot = 0.00;
        matchedPrice = 0.00;
    }

    public void createNewItem(View v) {
        createNewItem();
    }

    protected void createNewItem() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        // set as default
        clear();
        matchedPrice =0.00;
        // show the total amount with previous values
        TextView totalCalculated = (TextView) findViewById(R.id.totalCalculated);
        String totalPriceStr = String.format("%.2f",tot);
        totalCalculated.setText("$"+ totalPriceStr);
    }

}
