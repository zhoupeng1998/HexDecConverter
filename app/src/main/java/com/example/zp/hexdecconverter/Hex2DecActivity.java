package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.logging.Logger;

public class Hex2DecActivity extends AppCompatActivity {

    TextView text;
    EditText field;
    DatabaseReference myRef;
    FirebaseDatabase database;
    long decValue;
    String hexValue;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        text = findViewById(R.id.resultText);
        text.setText("Hex to Decimal");
        field = findViewById(R.id.inputField);
        field.setText("");

        database = FirebaseDatabase.getInstance("https://jlab4-3d648.firebaseio.com/");
        myRef = database.getReference("message");
    }

    public void convert (View view) {
        String output;
        this.hexValue = "0x" + field.getText().toString().toUpperCase();
        try {
            this.decValue = Long.parseLong(field.getText().toString(),16);
            output = Long.toString(this.decValue);
        } catch (NumberFormatException nfe) {
            output = "Exceeds radix limit";
        }
        text.setText(output);
    }

    //
    public void sendTestData (View view) {
        String key = myRef.child("convert").push().getKey();
        Converted convertedObj = new Converted(true, this.decValue, this.hexValue);
        HashMap<String, Object> convertedValue = convertedObj.toMap();
        HashMap<String, Object> convertedUpdate = new HashMap<>();
        convertedUpdate.put("/converted/" + key, convertedValue);
        myRef.updateChildren(convertedUpdate);
        //this.myRef.setValue("Fuck yeah");
        //System.out.print("succeed");
        Log.d("sendTestData", "Updating converted" + convertedValue);

    }
}
