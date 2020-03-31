package com.example.gero.proyectooraculo;
/**
 * Created by Osimielci on 4/12/2016.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
public class TodasLasZonasRequest extends StringRequest {
    private static final String ZONA_REQUEST_URL = "http://192.168.0.10/oraculo/android/GET/todasLasZonas.php";
    public TodasLasZonasRequest(Response.Listener<String> listener) {
        super(Method.GET, ZONA_REQUEST_URL, listener, null);
    }
}