package com.example.iasll.bac;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText; // for weight amount input
import android.widget.SeekBar; // for changing the amount of drinks
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.Switch;
import android.widget.TextView; // for displaying text
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.content.Intent;
import android.widget.Toast;

import org.w3c.dom.Text;
import java.text.NumberFormat; // for currency formatting

public class Welcome extends Activity {

    private Button btn1;
    private Button btn2;
    private Button btn3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn1 = (Button) findViewById(R.id.beerButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Entering Beer Activity";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                openBeer();
            }
        });

        btn2 = (Button) findViewById(R.id.wineButton);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Entering Wine Activity";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                openWine();
            }
        });

        btn3 = (Button) findViewById(R.id.liquorButton);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Entering Liquor Activity";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                openLiqour();
            }
        });
    }

    public void openBeer(){
        Intent intent = new Intent(this, BeerActivity.class);
        startActivity(intent);
    }

    public void openWine(){
        Intent intent = new Intent(this, WineActivity.class);
        startActivity(intent);
    }

    public void openLiqour(){
        Intent intent = new Intent(this, LiquorActivity.class);
        startActivity(intent);
    }


}
