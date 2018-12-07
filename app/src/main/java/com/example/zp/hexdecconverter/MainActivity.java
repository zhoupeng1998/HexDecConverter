package com.example.zp.hexdecconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
    }

    public void enterHex2Dec (View view) {
        Intent intent = new Intent(this, Hex2DecActivity.class);
        startActivity(intent);
    }

    public void enterDec2Hex (View view) {
        Intent intent = new Intent(this, Dec2HexActivity.class);
        startActivity(intent);
    }
}
