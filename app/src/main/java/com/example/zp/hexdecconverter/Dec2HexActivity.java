//Author: Peng Zhou
//This activities converts decimal to hex and store it to firebase

package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Dec2HexActivity extends ConvertActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text.setText("Decimal to Hex");
        field.setText("");
    }

    public void convert (View view) {
        String output;
        String input = field.getText().toString();
        try {
            output = "0x" + Long.toHexString(Long.parseLong(input)).toUpperCase();
            currentConverted = new ConvertedEntry(input, output);
        } catch (NumberFormatException nfe) {
            output = "Exceeds radix limit";
            currentConverted = null;
        }
        text.setText(output);
    }
}
