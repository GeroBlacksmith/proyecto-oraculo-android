package com.example.gero.proyectooraculo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class AddColaborationActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_colaboration);

        Button bAddColan = (Button) findViewById(R.id.bAddColab);
        final EditText etDescripcion = (EditText) findViewById(R.id.etDescripcion);

        Intent intent = getIntent();
        final int idUsuario = intent.getIntExtra("idUsuario", -1);
        final int idZona = intent.getIntExtra("idZona", -1);
        final String zona = intent.getStringExtra("zona");

        bAddColan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descripcion = etDescripcion.getText().toString();
                Response.Listener<String> listener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Log.d("Reponse: ", response);

                    }

                };
                AddColaborationRequest request = new AddColaborationRequest(idUsuario, idZona, descripcion, listener);
                RequestQueue queue = Volley.newRequestQueue(AddColaborationActivity.this);
                queue.add(request);
                Intent intent =new Intent(AddColaborationActivity.this, ColaboracionActivity.class);
                intent.putExtra("idUsuarios", idUsuario);
                intent.putExtra("idZona", idZona);
                intent.putExtra("zona", zona);
                startActivity(intent);
            }
        });

    }


}

