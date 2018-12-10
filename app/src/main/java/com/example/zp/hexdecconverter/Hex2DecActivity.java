//Author: Linchuan Chen
//This activities converts Hex to decimal

package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        if (input.substring(0,2).equals("0X")) {
            input = input.substring(2);
        }
        try {
            output = Long.toString(Long.parseLong(input,16));
            currentConverted = new ConvertedEntry(output, "0x" + input);
        } catch (NumberFormatException nfe) {
            output = "Exceeds radix limit";
            Log.w("exception:", nfe.getMessage());
            Log.w("cause:", nfe.getCause());
            currentConverted = null;
        }
        text.setText(output);
    }
}
