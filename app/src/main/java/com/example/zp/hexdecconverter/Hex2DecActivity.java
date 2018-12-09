package com.example.zp.hexdecconverter;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Hex2DecActivity extends ConvertActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text.setText("Hex to Decimal");
        field.setText("");
    }

    public void convert (View view) {
        String output;
        String input = "0x" + field.getText().toString().toUpperCase();
        Log.w("input:", input);
        try {
            output = Long.toString(Long.parseLong(field.getText().toString(),16));
            currentConverted = new ConvertedEntry(output, input);
        } catch (NumberFormatException nfe) {
            output = "Exceeds radix limit";
            Log.w("exception:", nfe.getMessage());
            Log.w("cause:", nfe.getCause());
            currentConverted = null;
        }
        text.setText(output);
    }
}
