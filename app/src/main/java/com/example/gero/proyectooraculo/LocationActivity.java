package com.example.gero.proyectooraculo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_location);


        Response.Listener<String> listener=new Response.Listener<String>(){

            @Override
            public void onResponse(String response){
                try {
                    Log.d("Response weather: ", response);
                    JSONObject jsonObject = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        LocationRequest locationRequest = new LocationRequest(listener);
        RequestQueue queue = Volley.newRequestQueue(LocationActivity.this);
        queue.add(locationRequest);

     }
}
