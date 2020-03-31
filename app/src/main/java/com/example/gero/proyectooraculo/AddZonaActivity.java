package com.example.gero.proyectooraculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.M;
import static com.example.gero.proyectooraculo.R.id.spinner;

public class AddZonaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_zona);
        Intent intent = getIntent();
        final String nombre = intent.getStringExtra("nombre");
        final String cuenta = intent.getStringExtra("cuenta");
        final int idUsuario = intent.getIntExtra("idUsuarios", -1);

        //TODO:PASOS A IMPLEMENTAR EN AGREGAR ZONA
        //PASO 1: obtener todas las zonas
        Response.Listener<String> listener = new Response.Listener<String>() {
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        int max = jsonObject.getInt("max");
                        int contador = 0;
                        final List<String> list = new ArrayList<String>();
                        final List<String> ids = new ArrayList<String>();
                        while (contador < max) {
                            list.add(jsonObject.getJSONObject(String.valueOf(contador)).getString("descripcion"));
                            ids.add(jsonObject.getJSONObject(String.valueOf(contador)).getString("idZona"));
                            contador++;
                        }
                        //PASO 2: cargar el spinner
                        final Spinner spinner2 = (Spinner) findViewById(spinner);


                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddZonaActivity.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(dataAdapter);
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                        Button bAgregar = (Button) findViewById(R.id.bAgregar);
                        bAgregar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int position = spinner2.getSelectedItemPosition();
                                int idZona = Integer.parseInt(ids.get(position));
                                //idUsuario;
                                //idZona;
                                Response.Listener<String> listener1 = new Response.Listener<String>() {
                                    public void onResponse(String response) {
                                        Log.d("response: ", response);
                                    }
                                };
                                AsociarRequest request = new AsociarRequest(idUsuario, idZona, listener1);
                                RequestQueue queue = Volley.newRequestQueue(AddZonaActivity.this);
                                queue.add(request);
                                //insert into asociarzona(idUsuarios idZona);
                                Intent intent = new Intent(AddZonaActivity.this, ZonaActivity.class);
                                intent.putExtra("idUsuarios", idUsuario);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("cuenta", cuenta);
                                startActivity(intent);
                            }
                        });
                    } else {

                    }
                } catch (JSONException e) {

                }
            }
        };
        TodasLasZonasRequest request = new TodasLasZonasRequest(listener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }
}
