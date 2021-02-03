package com.cm.gestionpharmacie;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cm.gestionpharmacie.UI.EnvoiDetail;
import com.cm.gestionpharmacie.model.Envoi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EnvoiAdapter extends RecyclerView.Adapter<EnvoiAdapter.MyViewHolder> {
    LayoutInflater inflater;
    List<Envoi> envois;
    Context context;

    public EnvoiAdapter(Context context, List<Envoi> envois){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.envois = envois;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.envoi_list,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // bind the data
        Envoi envoi = envois.get(position);
        holder.idE.setText(Integer.toString(envoi.getId()));
        holder.dateEnvoi.setText(formatDate(envoi.getDateEnvoi()));
        holder.montant.setText(Double.toString(envoi.getMontant()));
        //holder.emetteur.setText(envois.get(position).getEmetteur().getId());
        //holder.recepteur.setText(envoi.getRecepteur().getId());
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EnvoiDetail.class);
                intent.putExtra("id", envoi.getId());
                intent.putExtra("montant", envoi.getMontant());
                /*intent.putExtra("nomEme", envoi.getEmetteur().getNomEme());
                intent.putExtra("prenomEme", envoi.getEmetteur().getPrenomEme());
                intent.putExtra("telephoneEme", envoi.getEmetteur().getTelephoneEme());
                intent.putExtra("cni", envoi.getEmetteur().getCni());
                intent.putExtra("nomRec", envoi.getRecepteur().getNomRec());
                intent.putExtra("prenomRec", envoi.getRecepteur().getPrenomRec());
                intent.putExtra("telephoneRec", envoi.getRecepteur().getTelephoneRec());*/
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return envois.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView idE, dateEnvoi, montant, emetteur, recepteur;
         private ImageView detail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idE = (TextView) itemView.findViewById(R.id.idE);
            dateEnvoi = (TextView) itemView.findViewById(R.id.dateEnvoi);
            montant = (TextView) itemView.findViewById(R.id.montant);
            emetteur = (TextView) itemView.findViewById(R.id.emetteur);
            recepteur = (TextView) itemView.findViewById(R.id.recepteur);
            detail = (ImageView) itemView.findViewById(R.id.detail);
        }
    }

    private String formatDate(String dataStr){
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(dataStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
            return fmtOut.format(date);
        }catch (ParseException e){
            Log.d("error", e.getMessage());
        }
        return "";
    }
}

