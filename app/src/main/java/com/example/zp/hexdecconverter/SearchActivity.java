//Author: Peng Zhou
//This activity search for a data entry (in both decimal and hexadecimal value) from database and
//delete it upon click

package com.example.zp.hexdecconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener childEventListener;
    private EntryAdapter listAdapter;
    private ArrayList<ConvertedEntry> entryList;
    private ArrayList<ConvertedEntry> resultList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backup_search);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("entries");
        entryList = new ArrayList<ConvertedEntry>();
        resultList = new ArrayList<ConvertedEntry>();

        childEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                entryList.add( dataSnapshot.getValue(ConvertedEntry.class));
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

        listAdapter = new EntryAdapter(this, resultList );
        ListView results = findViewById(R.id.resultTable);

        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
                final ConvertedEntry entry = (ConvertedEntry) parent.getItemAtPosition(position);
                Log.w("deleting ID:", Long.toString(id));
                Log.w("deleting item:", entry.getId());
                myRef.child(entry.getId()).removeValue();
                entryList.remove(entry);
                resultList.remove(entry);
                listAdapter.notifyDataSetChanged();

            }
        });

        results.setAdapter(listAdapter);
    }

    public void search (View view) {
        listAdapter.clear();
        boolean found = false;
        EditText text = findViewById(R.id.searchField);
        String search = text.getText().toString().toUpperCase();
        if (search.substring(0,2).equals("0X")) {
            search = search.substring(2);
        }
        for (ConvertedEntry e : entryList) {
            if (e.getDec().equals(search) || e.getHex().substring(2).equals(search)) {
                listAdapter.add(e);
                found = true;
            }
        }
        if (!found) {
            Toast.makeText(this, "No result found", Toast.LENGTH_LONG).show();
        }
    }
}
