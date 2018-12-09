//Authro: Linchuan Chen
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("entries");

        // Set up an array that will have the contents that you want to display
        final ArrayList<ConvertedEntry> entryList = new ArrayList<ConvertedEntry>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database

        childEventListener = new ChildEventListener(){
            @Override
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.w("On child add", dataSnapshot.getValue(ConvertedEntry.class).getHex());

                //Log.w("Test::::::::::", Long.toString(dataSnapshot.getChildrenCount()));
                //Log.w("test:::::::", dataSnapshot.getChildren().toString());
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

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
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
