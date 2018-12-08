package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        String input = field.getText().toString().toUpperCase();
        try {
            output = Long.toString(Long.parseLong(input,16));
            currentConverted = new ConvertedEntry(output, input);
        } catch (NumberFormatException nfe) {
            output = "Exceeds radix limit";
            currentConverted = null;
        }
        text.setText(output);
    }
}
