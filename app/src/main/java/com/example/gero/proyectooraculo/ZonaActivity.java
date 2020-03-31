package com.example.gero.proyectooraculo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class ZonaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona);
        Intent intent = getIntent();
        final String nombre = intent.getStringExtra("nombre");
        final String cuenta = intent.getStringExtra("cuenta");
        final int idUsuarios = intent.getIntExtra("idUsuarios", -1);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response);

                    final ListView list = (ListView) findViewById(R.id.lvColab);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        int max = jsonObject.getInt("max");
                        Log.d("Success: ", "yes");
                        int contador = 0;
                        final ArrayList<String> zonas = new ArrayList<String>();
                        final ArrayList<Integer> ids = new ArrayList<Integer>();
                        while (contador < max) {
                            Log.d("Descripcion: ", jsonObject.getJSONObject(String.valueOf(contador)).getString("descripcion"));
                            Log.d("Descripcion: ", jsonObject.getJSONObject(String.valueOf(contador)).getString("idZona"));
                            zonas.add(jsonObject.getJSONObject(String.valueOf(contador)).getString("descripcion"));
                            ids.add(Integer.parseInt(jsonObject.getJSONObject(String.valueOf(contador)).getString("idZona")));
                            contador += 1;
                        }
                        final StableArrayAdapter adapter = new StableArrayAdapter(ZonaActivity.this,
                                android.R.layout.simple_list_item_1, zonas);
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onItemClick(AdapterView<?> parent, final View view,
                                                    int position, long id) {


                                Toast.makeText(getApplicationContext(),
                                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                                        .show();
                                final String item = (String) parent.getItemAtPosition(position);
                                int index=zonas.indexOf(item.toString());
                                int idZona=ids.get(index);

                                Log.d("item: ",item.toString());
                                Log.d("Id Zona: ", String.valueOf(idZona));

                                Intent intent=new Intent(ZonaActivity.this, ColaboracionActivity.class);
                                intent.putExtra("idUsuarios", idUsuarios);
                                intent.putExtra("idZona", idZona);
                                intent.putExtra("zona", item.toString());
                                ZonaActivity.this.startActivity(intent);

                            }

                        });

                    } else {
                        Log.e("Success: ", "no");
                    }
                } catch (JSONException e) {
                    Log.e("JSONException: ", e.getMessage());
                } finally {

                }
            }
        };
        ZonaRequest zonaRequest = new ZonaRequest(idUsuarios, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ZonaActivity.this);
        queue.add(zonaRequest);

        Button bAddZona = (Button)findViewById(R.id.bAddZona);
        bAddZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZonaActivity.this, AddZonaActivity.class);
                intent.putExtra("idUsuarios",idUsuarios);
                intent.putExtra("nombre", nombre);
                intent.putExtra("cuenta", cuenta);
                startActivity(intent);
            }
        });
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
