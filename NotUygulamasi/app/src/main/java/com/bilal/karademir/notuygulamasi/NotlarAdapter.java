package com.bilal.karademir.notuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu>{

    private Context mContext;
    private List<Notlar> notlarListe;

    public NotlarAdapter(Context mContext, List<Notlar> notlarListe) {
        this.mContext = mContext;
        this.notlarListe = notlarListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.not_card_tasarim,viewGroup,false);


        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu cardTasarimTutucu, final int i) {


        final Notlar not = notlarListe.get(i);
        cardTasarimTutucu.textViewDers.setText(not.getDers_adi());
        cardTasarimTutucu.textViewNot1.setText(String.valueOf(not.getNot1()));
        cardTasarimTutucu.textViewNot2.setText(String.valueOf(not.getNot2()));

        cardTasarimTutucu.not_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Log.e("ders Adı: ",not.getDers_adi());
               Intent intent = new Intent(mContext,DetayActivity.class);
                intent.putExtra("nesne",not);
                mContext.startActivity(intent);



            }
        });




    }

    @Override
    public int getItemCount() {
        return notlarListe.size();
    }


    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private CardView not_card;
        private TextView textViewDers;
        private TextView textViewNot1;
        private TextView textViewNot2;

        public CardTasarimTutucu(@NonNull View itemView) {

            super(itemView);
            not_card = itemView.findViewById(R.id.not_card);
            textViewDers = itemView.findViewById(R.id.textViewDers);
            textViewNot1 = itemView.findViewById(R.id.textViewNot1);
            textViewNot2 = itemView.findViewById(R.id.textViewNot2);






        }
    }
}
