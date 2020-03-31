package com.example.gero.proyectooraculo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Osimielci on 4/12/2016.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://192.168.0.10/oraculo/android/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username,String mail, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("mail",mail);
    }
    public Map<String, String> getParams(){
        return params;
    }
}
