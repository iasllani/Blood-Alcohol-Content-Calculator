package com.example.iasll.bac;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HelperActivity extends Activity {
// Declare references

    EditText userInput1,userInput2,userInput3;
    TextView recordsTextView;
    SQLActivity dbHandler;
    private MediaPlayer sound1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        sound1 = MediaPlayer.create(HelperActivity.this, R.raw.horn);
        userInput1 = (EditText) findViewById(R.id.user_Input1);
        userInput2 = (EditText) findViewById(R.id.user_Input2);
        userInput3 = (EditText) findViewById(R.id.user_Input3);
        recordsTextView = (TextView) findViewById(R.id.records_TextView);
        recordsTextView.setMovementMethod(new ScrollingMovementMethod());
        /* Can pass nulls because of the constants in the helper.
         * the 1 means version 1 so don't run update.
         */
        dbHandler = new SQLActivity(this);
        printDatabase();
    }
    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        recordsTextView.setText(dbString);
        userInput1.setText("");
        userInput2.setText("");
        userInput3.setText("");
    }

    //add your elements onclick methods.
    //Add a product to the database
    public void addButtonClicked(View view){
        // dbHandler.add needs an object parameter.
        Products product =
                new Products(userInput1.getText().toString(), userInput3.getText().toString(), Double.parseDouble(userInput2.getText().toString()));
        dbHandler.addProduct(product);
        if(sound1.isPlaying()) {
            sound1.pause();
            sound1.seekTo(0);
            sound1.start();
        }
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view){
        // dbHandler delete needs string to find in the db
        String inputText = userInput3.getText().toString();
        dbHandler.deleteProduct(inputText);
        if(sound1.isPlaying()) {
            sound1.pause();
            sound1.seekTo(0);
            sound1.start();
        }
        printDatabase();
    }

}
/*
public class HelperActivity extends Activity {
// Declare references

    EditText userInput1,userInput2,userInput3;
    TextView recordsTextView,recordsTextView1;
    SQLActivity dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        userInput1 = (EditText) findViewById(R.id.user_Input1);
        userInput2 = (EditText) findViewById(R.id.user_Input2);
        userInput3 = (EditText) findViewById(R.id.user_Input3);
        recordsTextView = (TextView) findViewById(R.id.records_TextView);
        recordsTextView1 = (TextView) findViewById(R.id.records_TextView1);
        recordsTextView.setMovementMethod(new ScrollingMovementMethod());
        recordsTextView1.setMovementMethod(new ScrollingMovementMethod());
        /* Can pass nulls because of the constants in the helper.
         * the 1 means version 1 so don't run update.
         *
        dbHandler = new SQLActivity(this);
        printDatabase();
    }
    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        recordsTextView.setText(dbString);
        userInput1.setText("");
        userInput2.setText("");
        userInput3.setText("");
    }
    public void printView(){
        String dbString = dbHandler.viewToString();
        recordsTextView1.setText(dbString);
        //userInput1.setText("");
        //userInput2.setText("");
    }

    //add your elements onclick methods.
    //Add a product to the database
    public void addButtonClicked(View view){
        // dbHandler.add needs an object parameter.
        Products product =
                new Products(userInput1.getText().toString(), userInput3.getText().toString(), Double.parseDouble(userInput2.getText().toString()));
        dbHandler.addProduct(product);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view){
        // dbHandler delete needs string to find in the db
        String inputText = userInput3.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    public void queryButtonClicked(View view){
        // dbHandler delete needs string to find in the db
        //String inputText = userInput1.getText().toString();
        //dbHandler.deleteProduct(inputText);
        printView();
    }
}
*/



