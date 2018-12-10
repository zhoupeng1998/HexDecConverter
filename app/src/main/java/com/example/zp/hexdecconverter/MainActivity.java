//Author: Peng Zhou
//entry point of the program

package com.example.zp.hexdecconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterHex2Dec (View view) {
        Intent intent = new Intent(this, Hex2DecActivity.class);
        startActivity(intent);
    }

    public void enterDec2Hex (View view) {
        Intent intent = new Intent(this, Dec2HexActivity.class);
        startActivity(intent);
    }

    public void enterView (View view) {
        Intent intent = new Intent(this,ViewActivity.class);
        startActivity(intent);
    }

    public void enterSearch (View view) {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
}
