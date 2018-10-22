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

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class SQLActivity extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "menuDB1d.db";
    public static final String TABLE_PRODUCTS = "menu";
    //public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WEIGHT = "course";
    public static final String COLUMN_DRINKS = "name";
    public static final String COLUMN_PRICE = "price";

    //We need to pass database information along to superclass
    public SQLActivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_WEIGHT + " TEXT," + COLUMN_DRINKS + " TEXT," + COLUMN_PRICE + " DOUBLE );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addProduct(Products product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WEIGHT, product.get_course());
        values.put(COLUMN_DRINKS, product.get_foodname());
        values.put(COLUMN_PRICE, product.get_price());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_DRINKS + "='" + productName + "';");
    }

    // this is goint in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("course")) != null) {
                //dbString += recordSet.getString(recordSet.getColumnIndex("productname"));
                dbString += recordSet.getString(0);
                dbString += ",   ";
                //dbString += recordSet.getString(recordSet.getColumnIndex("price"));
                dbString += recordSet.getString(1);
                dbString += ",   ";
                //dbString += recordSet.getString(recordSet.getColumnIndex("price"));
                dbString += recordSet.getString(2);
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        //dbString += "\n";

        db.close();
        return dbString;
    }

    public String viewToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT a.course, a.name as dishname, b.course, b.name, MAX(a.price+b.price) as total FROM " + TABLE_PRODUCTS +
                " a, " + TABLE_PRODUCTS + " b WHERE a.course = 'entree' AND b.course != 'entree' AND a.price+b.price IN (SELECT a.price+b.price as total FROM " + TABLE_PRODUCTS
                + " a, " + TABLE_PRODUCTS + " b WHERE total < 30);";

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("course")) != null) {
                // dbString += recordSet.getString(recordSet.getColumnIndex("productname"));
                dbString += recordSet.getString(0);
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("dishname"));
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("course"));
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("name"));
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("total"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }

        db.close();
        return dbString;
    }

}

/*//SQLITE IMPORTS
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class SQLActivity extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "menuDB1d.db";
    public static final String TABLE_PRODUCTS = "BAC Table";
    //public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_DRINKS = "Drinks";
    public static final String COLUMN_BAC = "BAC";

    public SQLActivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_WEIGHT + " TEXT," + COLUMN_DRINKS + " TEXT," + COLUMN_BAC + " DOUBLE );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addProduct(Products product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WEIGHT, product.get_weight());
        values.put(COLUMN_DRINKS, product.get_drinks());
        values.put(COLUMN_BAC, product.get_bac());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_DRINKS + "='" + productName + "';");
    }

    // this is goint in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("weight")) != null) {
                //dbString += recordSet.getString(recordSet.getColumnIndex("productname"));
                dbString += recordSet.getString(0);
                dbString += ",   ";
                //dbString += recordSet.getString(recordSet.getColumnIndex("price"));
                dbString += recordSet.getString(1);
                dbString += ",   ";
                //dbString += recordSet.getString(recordSet.getColumnIndex("price"));
                dbString += recordSet.getString(2);
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        //dbString += "\n";

        db.close();
        return dbString;
    }

    public String viewToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT a.weight, a.drink as beverage, b.weights, b.drink, MAX(a.bac+b.bac) as total FROM " + TABLE_PRODUCTS +
                " a, " + TABLE_PRODUCTS + " b WHERE a.weight = 'weight' AND b.weight != 'weight' AND a.bac+b.bac IN (SELECT a.bac+b.bac as total FROM " + TABLE_PRODUCTS
                + " a, " + TABLE_PRODUCTS + " b WHERE total < 30);";

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("weight")) != null) {
                // dbString += recordSet.getString(recordSet.getColumnIndex("productname"));
                dbString += recordSet.getString(0);
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("beverage"));
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("weight"));
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("drinks"));
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("bac"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }

        db.close();
        return dbString;
    }

}
*/
