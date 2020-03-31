package com.example.gero.proyectooraculo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Osimielci on 4/12/2016.
 */

public class AddColaborationRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "ADD_DOMAIN/oraculo/android/POST/colaboracion.php";
    private Map<String, String> params;

    /**
     * Creates a new request with the given method.
     *
     * @param listener Listener to receive the String response
     */
    public AddColaborationRequest(int idUsuario,int idZona,String descripcion, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("idUsuario", String.valueOf(idUsuario));
        params.put("idZona", String.valueOf(idZona));
        params.put("descripcion", descripcion);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
