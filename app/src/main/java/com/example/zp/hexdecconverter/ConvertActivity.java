package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public abstract class ConvertActivity extends AppCompatActivity {

    protected TextView text;
    protected EditText field;
    protected ConvertedEntry currentConverted;
    protected DatabaseReference myRef;
    protected FirebaseDatabase database;

    protected ChildEventListener childEventListener;
    protected EntryAdapter listAdapter;
    protected ArrayList<ConvertedEntry> entryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        text = findViewById(R.id.resultText);
        field = findViewById(R.id.inputField);
        //database = FirebaseDatabase.getInstance("https://hexdecconverter-38aed.firebaseio.com/");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("entries");
        entryList = new ArrayList<ConvertedEntry>();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                entryList.add(dataSnapshot.getValue(ConvertedEntry.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(childEventListener);
        listAdapter = new EntryAdapter(this, entryList);
        ListView results = findViewById(R.id.dataList);
        results.setAdapter(listAdapter);
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
