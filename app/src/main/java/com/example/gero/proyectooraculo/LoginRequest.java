package com.example.gero.proyectooraculo;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Osimielci on 3/12/2016.
 */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.0.10/oraculo/android/login.php";
    private Map<String, String> params;

    /**
     * Creates a new request with the given method.
     *
     * @param listener Listener to receive the String response
     */
    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("action", "login");
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

