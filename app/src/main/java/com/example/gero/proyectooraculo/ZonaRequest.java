package com.example.gero.proyectooraculo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Osimielci on 3/12/2016.
 */

public class ZonaRequest extends StringRequest {
    private static final String ZONA_REQUEST_URL = "http://192.168.0.10/oraculo/android/GET/zonas.php";
    private Map<String, String> params;

    /**
     * Creates a new request with the given method.
     *
     * @param listener Listener to receive the String response
     */
    public ZonaRequest(int idUsuarios, Response.Listener<String> listener) {
        super(Method.POST, ZONA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idUsuarios", (new Integer(idUsuarios)).toString());

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
