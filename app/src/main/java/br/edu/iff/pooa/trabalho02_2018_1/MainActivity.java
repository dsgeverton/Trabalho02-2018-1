package br.edu.iff.pooa.trabalho02_2018_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.candidato = findViewById(R.id.buttonCandidato);
        this.mViewHolder.eleitor = findViewById(R.id.buttonEleitor);

        this.mViewHolder.eleitor.setOnClickListener(this);
        this.mViewHolder.candidato.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonEleitor){
            Intent intent = new Intent(this, ListarEleitorActivity.class);
            startActivity(intent);
        }

        if (id == R.id.buttonCandidato){
            Intent intent = new Intent(this, ListarCandidatoActivity.class);
            startActivity(intent);
        }

    }

    public static class ViewHolder{
        Button eleitor, candidato;
    }
}
