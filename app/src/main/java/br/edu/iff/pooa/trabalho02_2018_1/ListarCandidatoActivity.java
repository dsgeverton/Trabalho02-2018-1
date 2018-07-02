package br.edu.iff.pooa.trabalho02_2018_1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.edu.iff.pooa.trabalho02_2018_1.adapter.CandidatoAdapter;
import br.edu.iff.pooa.trabalho02_2018_1.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class ListarCandidatoActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_candidato);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCandidato);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarCandidatoActivity.this, InfoCandidatoActivity.class);
                intent.putExtra("id","0");
                startActivity(intent);
            }
        });
    }

    private List<Candidato> getCandidatos(){

        return (List)realm.where(Candidato.class).findAll();
    }

    @Override
    public void onClick(Object object) {
        Candidato candidato = (Candidato) object;
        Intent intent = new Intent(ListarCandidatoActivity.this, InfoCandidatoActivity.class);
        intent.putExtra("id", candidato.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Candidatos);

        recyclerView.setAdapter(new CandidatoAdapter(getCandidatos(),this, this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);

    }

    @Override
    public void finish(){
        super.finish();
        realm.close();
    }
}