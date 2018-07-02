package br.edu.iff.pooa.trabalho02_2018_1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import br.edu.iff.pooa.trabalho02_2018_1.R;
import br.edu.iff.pooa.trabalho02_2018_1.model.Candidato;

public class CandidatoAdapter extends RecyclerView.Adapter {

    private List<Candidato> candidatos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public CandidatoAdapter(List<Candidato> candidatos, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.candidatos = candidatos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_candidato, parent, false);
        CandidatoAdapter.CandidatoViewHolder candidatoViewHolder = new CandidatoAdapter.CandidatoViewHolder(view);
        return candidatoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CandidatoAdapter.CandidatoViewHolder v_holder = (CandidatoAdapter.CandidatoViewHolder) holder;

        Candidato eleitor  = candidatos.get(position) ;

        v_holder.nomeCandidato.setText(eleitor.getNome());
        v_holder.partidoCandidato.setText(eleitor.getPartido());
        v_holder.cargoCandidato.setText(eleitor.getCargo());
        v_holder.urnaCandidato.setText(eleitor.getNumeroUrna());
    }

    @Override
    public int getItemCount() {
        return candidatos.size();
    }

    public class CandidatoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeCandidato;
        private final TextView partidoCandidato;
        private final TextView cargoCandidato;
        private final TextView urnaCandidato;

        public CandidatoViewHolder(View itemView) {
            super(itemView);
            nomeCandidato = (TextView) itemView.findViewById(R.id.textViewNomeCandidato);
            partidoCandidato = (TextView) itemView.findViewById(R.id.textViewPartidoCandidato);
            cargoCandidato = (TextView) itemView.findViewById(R.id.textViewCargoCandidato);
            urnaCandidato = (TextView) itemView.findViewById(R.id.textViewNumeroUrnaCandidato);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(candidatos.get(getLayoutPosition()));
                }
            });
        }
    }
}
