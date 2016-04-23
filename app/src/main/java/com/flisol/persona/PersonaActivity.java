package com.flisol.persona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class PersonaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        RequestQueue cola = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.10.145:8000/personas/17/";

        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    ;
                    llenarComponentes(String.valueOf(response.get("nombre"))
                    , String.valueOf(response.get("descripcion")),
                            String.valueOf(response.get("foto")),
                            String.valueOf(response.get("habilidad_uno")),
                            String.valueOf(response.get("habilidad_dos")),
                            String.valueOf(response.get("habilidad_tres"))
                            );

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "No hay conexión a Internet", Toast.LENGTH_LONG);
            }
        });

        cola.add(peticion);
    }


    private void llenarComponentes(String nombre, String descripcion,String foto, String habilidadUno, String habilidadDos,
                                   String habilidadTres) {
        ((TextView)findViewById(R.id.nombre)).setText(nombre);
        ((TextView)findViewById(R.id.descripcion)).setText(descripcion);

        Picasso.with(getApplicationContext())
                .load(foto)
                .fit()
                .into((CircularImageView)findViewById(R.id.foto_perfil));

        ((TextView)findViewById(R.id.habilidad_uno)).setText(habilidadUno);
        ((TextView)findViewById(R.id.habilidad_dos)).setText(habilidadDos);
        ((TextView)findViewById(R.id.habilidad_tres)).setText(habilidadTres);
    }
}
