package br.edu.iff.pooa.trabalho02_2018_1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.edu.iff.pooa.trabalho02_2018_1.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa.trabalho02_2018_1.adapter.EleitorAdapter;
import br.edu.iff.pooa.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class ListarEleitorActivity extends AppCompatActivity implements ClickRecyclerViewListener{

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eleitor);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarEleitorActivity.this, InfoEleitorActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    private List<Eleitor> getEleitores(){

        return (List)realm.where(Eleitor.class).findAll();
    }

    @Override
    public void onClick(Object object) {
        Eleitor eleitor = (Eleitor) object;
        Intent intent = new Intent(ListarEleitorActivity.this, InfoEleitorActivity.class);
        intent.putExtra("id",eleitor.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Eleitores);

        recyclerView.setAdapter(new EleitorAdapter(getEleitores(),this, this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);

    }

    @Override
    public void finish(){
        realm.close();
    }
}
