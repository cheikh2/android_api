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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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
    String urlAdd="http://192.168.1.18:8080/envoie";

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
        final String myTelephoneRec = telephoneRec.getText().toString().trim();

        if (TextUtils.isEmpty(myMontant)){
            montant.setError("Remplir le montant");
            montant.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myNomEme)){
            nomEme.setError("Remplir le nom");
            nomEme.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myPrenomEme)){
            prenomEme.setError("Remplir le prenom");
            prenomEme.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myTelephoneEme)){
            telephoneEme.setError("Remplir le telephone");
            telephoneEme.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myCni)){
            cni.setError("Remplir le cni");
            cni.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myNomRec)){
            nomRec.setError("Remplir le nom");
            nomRec.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myPrenomRec)){
            prenomRec.setError("Remplir le prenom");
            prenomRec.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myTelephoneRec)){
            telephoneRec.setError("Remplir le telephone");
            telephoneRec.requestFocus();
            return;
        }

        JSONObject postparams = new JSONObject();
        try {
            postparams.put("montant",myMontant);
            postparams.put("prenomEme",myPrenomEme);
            postparams.put("nomEme",myNomEme);
            postparams.put("telephoneEme",myTelephoneEme);
            postparams.put("cni",myCni);
            postparams.put("prenomRec",myPrenomRec);
            postparams.put("nomRec",myNomRec);
            postparams.put("telephoneRec",myTelephoneRec);
        }catch (JSONException e){
            e.getMessage();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,urlAdd, postparams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Toast.makeText(getApplicationContext(), "transfert reussi", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();

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
        queue.add(jsonObjectRequest);
    }
}