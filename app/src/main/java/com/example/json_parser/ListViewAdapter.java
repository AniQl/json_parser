package com.example.json_parser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class ListViewAdapter extends ArrayAdapter<Persons> {

    private List<Persons> personsList;

    private Context mCtx;

    public ListViewAdapter(List<Persons> personsList, Context mCtx) {
        super(mCtx, R.layout.list_items, personsList);
        this.personsList = personsList;
        this.mCtx = mCtx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        TextView textViewId = listViewItem.findViewById(R.id.textViewId);
        TextView textViewFirst_name = listViewItem.findViewById(R.id.textViewFirst_name);
        TextView textViewLast_name = listViewItem.findViewById(R.id.textViewLast_name);
        TextView textViewAge = listViewItem.findViewById(R.id.textViewAge);
        TextView textViewGender = listViewItem.findViewById(R.id.textViewGender);
        TextView textViewEmail = listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewPhone = listViewItem.findViewById(R.id.textViewPhone);

        Persons persons = personsList.get(position);

        textViewId.setText(persons.getId());
        textViewFirst_name.setText(persons.getFirst_name());
        textViewLast_name.setText(persons.getLast_name());
        textViewAge.setText(persons.getAge());
        textViewGender.setText(persons.getGender());
        textViewEmail.setText(persons.getEmail());
        textViewPhone.setText(persons.getPhone());


        return listViewItem;
    }
}
