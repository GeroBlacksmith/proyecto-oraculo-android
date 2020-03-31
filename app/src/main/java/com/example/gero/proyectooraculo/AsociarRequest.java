package com.example.gero.proyectooraculo;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Osimielci on 4/12/2016.
 */
public class AsociarRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.0.10/oraculo/android/POST/asociarzona.php";
    private Map<String, String> params;

    /**
     * Creates a new request with the given method.
     *
     * @param listener Listener to receive the String response
     */
    public AsociarRequest(int idUsuario, int idZona, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("action", "login");
        params.put("idUsuario", String.valueOf(idUsuario));
        params.put("idZona", String.valueOf(idZona));
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
