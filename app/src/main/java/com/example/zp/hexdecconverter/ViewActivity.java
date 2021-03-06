//Author: Linchuan Chen
//This activities read all stored entries from firebase to a list view and delete it upon click

package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener childEventListener;
    private EntryAdapter listAdapter;
    private ArrayList<ConvertedEntry> entryList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("entries");
        entryList = new ArrayList<ConvertedEntry>();

        childEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listAdapter.add( dataSnapshot.getValue(ConvertedEntry.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                listAdapter.remove(dataSnapshot.getValue(ConvertedEntry.class));
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                listAdapter.remove(dataSnapshot.getValue(ConvertedEntry.class));
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        myRef.addChildEventListener(childEventListener);

        listAdapter = new EntryAdapter(this, entryList );
        ListView results = findViewById(R.id.dataList);


        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
                final ConvertedEntry entry = (ConvertedEntry) parent.getItemAtPosition(position);
                Log.w("deleting ID:", Long.toString(id));
                Log.w("deleting item:", entry.getId());
                myRef.child(entry.getId()).removeValue();
                entryList.remove(entry);
                listAdapter.notifyDataSetChanged();

            }
        });
        results.setAdapter(listAdapter);
    }
}
