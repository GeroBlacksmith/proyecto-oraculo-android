package com.example.gero.proyectooraculo;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
/**
 * Created by Osimielci on 4/12/2016.
 */
public class LocationRequest extends StringRequest {
    private static final String APIID="&APPID=db47f4964efc61e282950fb851d1b7b0";
    private static final String URL_CLIMA = "api.openweathermap.org/data/2.5/weather?q=NEUQUEN&units=metric";
    public LocationRequest(Response.Listener<String> listener) {
        super(Method.GET, String.format("%s%s", URL_CLIMA, APIID), listener, null);
    }
}
