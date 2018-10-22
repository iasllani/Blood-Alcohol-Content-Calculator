package com.example.iasll.bac;

import android.app.Activity;
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
import org.w3c.dom.Text;
import java.text.NumberFormat; // for currency formatting

public class MainActivity extends Activity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.firstButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWelcome();
            }
        });
    }

    public void openWelcome(){
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);


    }

}



