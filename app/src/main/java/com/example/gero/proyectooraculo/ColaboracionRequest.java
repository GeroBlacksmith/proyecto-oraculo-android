package com.example.gero.proyectooraculo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Osimielci on 3/12/2016.
 */

public class ColaboracionRequest extends StringRequest {

    private static final String COLABORACION_REQUEST_URL = "http://192.168.0.10/oraculo/android/GET/colaboracion.php";
    private Map<String, String> params;

    /**
     * Creates a new request with the given method.
     *
     * @param listener Listener to receive the String response
     */
    public ColaboracionRequest(int idUsuarios,int idZona, Response.Listener<String> listener) {
        super(Method.POST, COLABORACION_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idUsuarios", (new Integer(idUsuarios)).toString());
        params.put("idZona",(new Integer(idZona)).toString());

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
