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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;
import static android.os.Build.VERSION_CODES.M;

public class ColaboracionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaboracion);

        Intent intent=getIntent();
        final int idUsuarios = intent.getIntExtra("idUsuarios",-1);
        final int idZona = intent.getIntExtra("idZona",-1);
        final String zona = intent.getStringExtra("zona");

        final TextView descripcion=(TextView)findViewById(R.id.tvDescipcion);
        descripcion.setText(zona);

        final Response.Listener<String> listener = new Response.Listener<String>(){
            public void onResponse(String response){
                try {
                    Log.d("Response: ",response);
                    ListView list=(ListView)findViewById(R.id.lvColaboraciones);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        int max = jsonObject.getInt("max");
                        Log.d("Success: ", "yes");
                        int contador = 0;
                        final ArrayList<String> colaboraciones = new ArrayList<String>();
                        final ArrayList<Integer> ids = new ArrayList<Integer>();
                        while (contador < max) {
                            Log.d("Descripcion: ", jsonObject.getJSONObject(String.valueOf(contador)).getString("descripcion"));
                            Log.d("nombre: ", jsonObject.getJSONObject(String.valueOf(contador)).getString("nombre"));

                            colaboraciones.add("Usuario: "+jsonObject.getJSONObject(String.valueOf(contador)).getString("nombre"));
                            colaboraciones.add(jsonObject.getJSONObject(String.valueOf(contador)).getString("descripcion"));


                            contador += 1;
                        }
                        final ColaboracionActivity.StableArrayAdapter adapter = new ColaboracionActivity.StableArrayAdapter(ColaboracionActivity.this,
                                android.R.layout.simple_list_item_1, colaboraciones);
                        list.setAdapter(adapter);
                    }

                }catch(JSONException e){

                }


            }
        };
        ColaboracionRequest colaboracionRequest = new ColaboracionRequest(idUsuarios, idZona, listener);
        RequestQueue queue = Volley.newRequestQueue(ColaboracionActivity.this);
        queue.add(colaboracionRequest);

        Button bColaboracionAdd = (Button)findViewById(R.id.bColaboracionAdd);
        bColaboracionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ColaboracionActivity.this, AddColaborationActivity.class);
                intent.putExtra("idUsuario", idUsuarios);
                intent.putExtra("idZona", idZona);
                intent.putExtra("zona", zona);
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
