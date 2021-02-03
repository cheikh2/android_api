package com.cm.gestionpharmacie.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cm.gestionpharmacie.EnvoiAdapter;
import com.cm.gestionpharmacie.R;
import com.cm.gestionpharmacie.model.Envoi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FairEnvoi extends AppCompatActivity {

    List<Envoi> envois;
    RecyclerView recyclerView;
    SwipeRefreshLayout refresh;
    EnvoiAdapter envoiAdapter;
    Dialog dialog;
    String url="http://192.168.1.6:8080/envoie";

    private EditText montant, prenomEme, nomEme, cni, telephoneEme, prenomRec, nomRec, telephoneRec;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fair_envoi);

        montant = findViewById(R.id.montant);
        prenomEme = findViewById(R.id.prenomEme);
        nomEme = findViewById(R.id.nomEme);
        cni = findViewById(R.id.cni);
        telephoneEme = findViewById(R.id.telephoneEme);
        prenomRec =findViewById(R.id.prenomRec);
        nomRec = findViewById(R.id.nomRec);
        telephoneRec = findViewById(R.id.telephoneRec);
        saveButton = findViewById(R.id.submit);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private  void saveData(){
        final String myMontant = montant.getText().toString().trim();
        final String myPrenomEme = prenomEme.getText().toString().trim();
        final String myNomEme = nomEme.getText().toString().trim();
        final String myCni = cni.getText().toString().trim();
        final String myTelephoneEme = telephoneEme.getText().toString().trim();
        final String myPrenomRec = prenomRec.getText().toString().trim();
        final String myNomRec = nomRec.getText().toString().trim();
        final String mytelephoneRec = telephoneRec.getText().toString().trim();

        if (TextUtils.isEmpty(myMontant)){
            montant.setError("Remplir le montant");
            montant.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myMontant)){
            prenomEme.setError("Remplir le prenom");
            prenomEme.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myMontant)){
            nomEme.setError("Remplir le nom");
            nomEme.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myMontant)){
            telephoneEme.setError("Remplir le telephone");
            telephoneEme.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myMontant)){
            cni.setError("Remplir le cni");
            cni.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myMontant)){
            prenomRec.setError("Remplir le prenom");
            prenomRec.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myMontant)){
            nomRec.setError("Remplir le nom");
            nomRec.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myMontant)){
            telephoneRec.setError("Remplir le telephone");
            telephoneRec.requestFocus();
            return;
        }


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,url, null, new Response.Listener<JSONArray>() {
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
}