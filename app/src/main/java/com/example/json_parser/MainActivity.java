package com.example.json_parser;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL = "https://marcin.rodziewicz.pracownik.put.poznan.pl/files/persons.json";

    ListView listView;
    List<Persons> personsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        personsList = new ArrayList<>();

        loadPersonsList();
    }
    private void loadPersonsList() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        try {

                            JSONArray personsArray = new JSONArray(response);

                            //JSONObject obj = new JSONObject(response);
                            //JSONArray personsArray = obj.getJSONArray("first_name");

                            for (int i = 0; i < personsArray.length(); i++) {
                                JSONObject personsObject = personsArray.getJSONObject(i);
                                Persons persons = new Persons(personsObject.getString("id"),
                                        personsObject.getString("first_name"), personsObject.getString("last_name"), personsObject.getString("age"),
                                        personsObject.getString("gender"), personsObject.getString("email"), personsObject.getString("phone"));

                                personsList.add(persons);
                            }

                            ListViewAdapter adapter = new ListViewAdapter(personsList, getApplicationContext());
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
