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
import br.edu.iff.pooa.trabalho02_2018_1.model.Eleitor;

public class EleitorAdapter extends RecyclerView.Adapter {

    private List<Eleitor> eleitores;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public EleitorAdapter(List<Eleitor> eleitores, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.eleitores = eleitores;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_eleitor, parent, false);
        EleitorViewHolder eleitorViewHolder = new EleitorViewHolder(view);
        return eleitorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EleitorViewHolder v_holder = (EleitorViewHolder) holder;

        Eleitor eleitor  = eleitores.get(position) ;

        v_holder.nomeEleitor.setText(eleitor.getNome());
        v_holder.tituloEleitor.setText(eleitor.getNumeroTitulo());
        v_holder.secaoEleitor.setText(eleitor.getSecao());
        v_holder.zonaEleitor.setText(eleitor.getZona());
    }

    @Override
    public int getItemCount() {
        return eleitores.size();
    }

    public class EleitorViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeEleitor;
        private final TextView tituloEleitor;
        private final TextView secaoEleitor;
        private final TextView zonaEleitor;

        public EleitorViewHolder(View itemView) {
            super(itemView);
            nomeEleitor = (TextView) itemView.findViewById(R.id.textViewNomeEleitor);
            tituloEleitor = (TextView) itemView.findViewById(R.id.textViewTituloEleitor);
            secaoEleitor = (TextView) itemView.findViewById(R.id.textViewSecaoEleitor);
            zonaEleitor = (TextView) itemView.findViewById(R.id.textViewZonaEleitor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(eleitores.get(getLayoutPosition()));
                }
            });
        }
    }
}
