//Author: Peng Zhou
//Array adapter, handles data and put them into list view

package com.example.zp.hexdecconverter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EntryAdapter extends ArrayAdapter<ConvertedEntry> {

    private Context mContext;
    private List<ConvertedEntry> entryList = new ArrayList<ConvertedEntry>();

    public EntryAdapter( Context context, ArrayList<ConvertedEntry> list)
    {
        super( context, 0, list);
        mContext = context;
        entryList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.entry,parent,false);

        ConvertedEntry currentEntry = entryList.get(position);

        TextView decView = (TextView) listItem.findViewById(R.id.textView_Dec);
        decView.setText(currentEntry.getDec());

        TextView hexView = (TextView) listItem.findViewById(R.id.textView_Hex);
        hexView.setText(currentEntry.getHex());

        return listItem;
    }
}
