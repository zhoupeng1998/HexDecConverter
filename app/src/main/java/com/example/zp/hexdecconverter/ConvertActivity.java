package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class ConvertActivity extends AppCompatActivity {

    protected TextView text;
    protected EditText field;
    protected ConvertedEntry currentConverted;
    protected DatabaseReference myRef;
    protected FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        text = findViewById(R.id.resultText);
        field = findViewById(R.id.inputField);
        database = FirebaseDatabase.getInstance("https://hexdecconverter-38aed.firebaseio.com/");
        myRef = database.getReference("entries");
    }

    public abstract void convert (View view);

    public void pushEntry (View view) {
        if (currentConverted != null) {
            String key = myRef.push().getKey();
            currentConverted.setId(key);
            myRef.child(key).setValue(currentConverted);
            Toast.makeText(this, key + " " + currentConverted.getDec() + " "
                    + currentConverted.getHex() + " successfully added.", Toast.LENGTH_LONG).show();
        }
    }
}
