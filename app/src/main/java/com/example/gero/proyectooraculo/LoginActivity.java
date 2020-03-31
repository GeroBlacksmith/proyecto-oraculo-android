package com.example.gero.proyectooraculo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText txtUsername = (EditText) findViewById(R.id.txt_username_login);
        final EditText txtPassword = (EditText) findViewById(R.id.txt_pwd_login);
        final Button bLogin = (Button)findViewById(R.id.bLogin);
        final Button bRegisterHere = (Button) findViewById(R.id.bRegisterHere);

        bRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regiterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(regiterIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = txtUsername.getText().toString();
                final String password = txtPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    /**
                     * Called when a response is received.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("Response: ", response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                intent.putExtra("idUsuarios", jsonObject.getInt("idUsuarios"));
                                intent.putExtra("idRol",jsonObject.getInt("idRol"));
                                intent.putExtra("nombre",jsonObject.getString("nombre"));
                                intent.putExtra("cuenta",jsonObject.getString("cuenta"));
                                intent.putExtra("correo",jsonObject.getString("correo"));


                                LoginActivity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }catch(JSONException e){
                            Log.e("JSONException: ", e.getMessage());
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(username, password,responseListener );
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}
