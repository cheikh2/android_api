package com.cm.gestionpharmacie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.Dialog;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cm.gestionpharmacie.UI.FairEnvoi;
import com.cm.gestionpharmacie.model.Envoi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Envoi> envois;
    RecyclerView recyclerView;
    SwipeRefreshLayout refresh;
    EnvoiAdapter envoiAdapter;
    Dialog dialog;
    String url="http://192.168.1.6:8080/envoies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.envoisList);
        envois = new ArrayList<>();
        extractEnvoie();

        FloatingActionButton fab = findViewById(R.id.doEnvoi);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FairEnvoi.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void extractEnvoie() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject envoiObjet = response.getJSONObject(i);
                        Envoi envoi = new Envoi();
                        envoi.setId(envoiObjet.getInt("id"));
                        envoi.setDateEnvoi(envoiObjet.getString("dateEnvoi"));
                        envoi.setMontant(envoiObjet.getDouble("montant"));
                        //JSONObject Jarray  = envoiObjet.getJSONObject("emetteur");
                        //envoi.setEmetteur(Jarray.getJSONObject("id"));
                        //System.out.println(Jarray);
                        //envoi.setEmetteur(Jarray);
                        //envoi.setRecepteur(envoiObjet.getJSONObject("recepteur"));
                        /*JSONObject recepteurObjet = response.getJSONObject(i);
                        Recepteur recepteur = new Recepteur();
                        recepteur.setId(recepteurObjet.getInt("id"));*/

                        envois.add(envoi);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                envoiAdapter = new EnvoiAdapter(getApplication(), envois);
                recyclerView.setAdapter(envoiAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErroResponse: " + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    /*public void doEnvoi(){
        TextView close, envInf;
        final EditText montant, prenomEme, nomEme, telephoneEme, cni, prenomRec, nomRec, telephoneRec;
        Button submit;

        dialog.setContentView(R.layout.activity_modenc);
        close = (TextView) dialog.findViewById(R.id.textClose);
        envInf = (TextView) dialog.findViewById(R.id.envInf);
        envInf.setText("Informations");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        montant = (EditText) dialog.findViewById(R.id.montant);
        prenomEme = (EditText) dialog.findViewById(R.id.prenomEme);
        nomEme = (EditText) dialog.findViewById(R.id.nomEme);
        telephoneEme = (EditText) dialog.findViewById(R.id.telephoneEme);
        cni = (EditText) dialog.findViewById(R.id.cni);
        prenomRec = (EditText) dialog.findViewById(R.id.prenomRec);
        nomRec = (EditText) dialog.findViewById(R.id.nomRec);
        telephoneRec = (EditText) dialog.findViewById(R.id.telephoneRec);
        submit = (Button) dialog.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = montant.getText().toString();

                Submit(data);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void Submit(String data) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                refresh.post(new Runnable() {
                    @Override
                    public void run() {
                        envois.clear();
                        doEnvoi();

                    }
                });
                Toast.makeText(getApplicationContext(), "transfert d'argent", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "new transfert", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("montant", data);
                params.put("prenomEme", data);
                params.put("nomEme", data);
                params.put("telephoneEme", data);
                params.put("cni", data);
                params.put("prenomRec", data);
                params.put("nomRec", data);
                params.put("telephoneRec", data);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }*/

}