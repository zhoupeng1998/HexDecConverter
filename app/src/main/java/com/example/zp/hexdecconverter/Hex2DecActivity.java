package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Hex2DecActivity extends AppCompatActivity {

    TextView text;
    EditText field;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        text = findViewById(R.id.resultText);
        text.setText("Hex to Decimal");
        field = findViewById(R.id.inputField);
        field.setText("");
    }

    public void convert (View view) {
        String output;
        try {
            output = Long.toString(Long.parseLong(field.getText().toString(),16));
        } catch (NumberFormatException nfe) {
            output = "Exceeds radix limit";
        }
        text.setText(output);
    }
}
