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
import android.widget.RadioButton;
import android.widget.SeekBar; // for changing the amount of drinks
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.Switch;
import android.widget.TextView; // for displaying text
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.content.Intent;
import org.w3c.dom.Text;
import java.text.NumberFormat; // for currency formatting


public class WineActivity extends Activity {
    private Button btn;

    //weightTextView.setText(weightAmount + "lbs"); when we want to follow it with a string
    //Called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        // SQL
        btn = (Button) findViewById(R.id.firstButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSQL();
            }
        });

        // get references to progammatically manipulated TextViews
        weightTextView = (TextView) findViewById(R.id.weightTextView);
        drinksTextView = (TextView) findViewById(R.id.drinksTextView);
        bacTotalTextView = (TextView) findViewById(R.id.bacTotalTextView);

        // set EditTexts' TextWatcher
        EditText weightEditText = (EditText)findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        //set percentSeekBar's onSeekBarChangeListener
        SeekBar drinksSeekBar = (SeekBar) findViewById(R.id.drinksSeekBar);
        drinksSeekBar.setOnSeekBarChangeListener(seekBarListener);

    }

    public void openSQL(){
        Intent intent = new Intent(this, HelperActivity.class);
        startActivity(intent);
    }

    private static final NumberFormat decimalFormat =
            NumberFormat.getInstance();

    private int weightAmount = 0; // weight amount entered by the user
    private int drinkAmount = 12; // initial amount of drinks
    private TextView weightTextView; // shows the users weight
    private TextView drinksTextView; // shows the users amount of drinks
    private TextView bacTotalTextView; // displays total bac

    private double genderVal;

    // calculate and display bac
    private void calculate() {
        weightTextView.setText(weightAmount + "lbs");
        drinksTextView.setText(drinkAmount + "glasses");

        double gC; //gender Constant * weight in Grams
        int weightG;
        double drinksG;
        double bac;

        // convert lbs and drinks to grams
        weightG = weightAmount * 454;
        drinksG = drinkAmount * 340 * .05;

        gC = genderVal * weightG;
        bac = (drinksG / gC) * 100;
        // format integer and display in bacTotalTextView
        bacTotalTextView.setText(decimalFormat.format(bac));
    }

    // listener object for the SeekBar's progress changed events
    private final OnSeekBarChangeListener seekBarListener =
            new OnSeekBarChangeListener() {
                // update drinks, then call calculate
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    Log.d("Ilir", "Progress" + progress);
                    drinkAmount = progress; //set drinks to progress on seekbar
                    calculate(); //calculate and display bacTotal
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    // listender object for weightEditText
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the weight amount
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try { // get weight amount and display integer formatted value
                weightAmount = Integer.parseInt(s.toString());
                weightTextView.setText(Integer.toString(weightAmount));
            } catch (NumberFormatException e) { // if s is empty or non-numeric
                weightTextView.setText("");
                weightAmount = 0;
            }
            calculate(); // update the bac
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final OnCheckedChangeListener switchListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            calculate();
        }
    };

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maleRadioButton:
                if (checked) {
                    genderVal = .68;
                    calculate(); // update the bac
                }
                break;
            case R.id.femaleRadioButton:
                if (checked){
                    genderVal = .55;
                    calculate(); // update the bac
                }
                break;
        }
    }
}
