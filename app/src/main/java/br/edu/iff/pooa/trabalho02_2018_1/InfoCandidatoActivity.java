package br.edu.iff.pooa.trabalho02_2018_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import br.edu.iff.pooa.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class InfoCandidatoActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private Realm realm;
    private Candidato candidato;
    private String idCandidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_candidato);

        this.mViewHolder.nomeCandidato = (EditText) findViewById(R.id.edtTextInfoNomeCandidato);
        this.mViewHolder.nomePartido = (EditText) findViewById(R.id.edtTextInfoNomePartidoCandidato);
        this.mViewHolder.numeroUrna = (EditText) findViewById(R.id.edtTextInfoNumeroUrna);
        this.mViewHolder.cargo = (EditText) findViewById(R.id.edtTextinfoCargoCandidato);
        this.mViewHolder.numeroVotos = (EditText) findViewById(R.id.edtTextInfoNumeroVotos);
        this.mViewHolder.estadoCandidato = (EditText) findViewById(R.id.edtTextInfoEstadoCandidato);
        this.mViewHolder.municipioCandidato = (EditText) findViewById(R.id.edtTextInfoMunicipioCandidato);
        this.mViewHolder.salvarCandidato = (Button) findViewById(R.id.buttonSalvarCandidato);
        this.mViewHolder.habilitarEdicaoCandidato = (Switch) findViewById(R.id.switchHabilitarEdicaoCandidato);
        this.mViewHolder.excluir = findViewById(R.id.excluirCandidato);

        this.mViewHolder.excluir.setOnClickListener(this);
        this.mViewHolder.salvarCandidato.setOnClickListener(this);

        Intent intent = getIntent();
        idCandidato = (String) intent.getSerializableExtra("id");
        if (idCandidato.equals("0"))
        {
            this.mViewHolder.habilitarEdicaoCandidato.setVisibility(View.INVISIBLE);
            this.mViewHolder.habilitarEdicaoCandidato.setActivated(false);
            this.mViewHolder.habilitarEdicaoCandidato.setSplitTrack(false);
            this.mViewHolder.excluir.setVisibility(View.INVISIBLE);
            this.mViewHolder.excluir.setClickable(false);

        }

        povoate();

        this.mViewHolder.habilitarEdicaoCandidato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mViewHolder.nomeCandidato.setEnabled(true);
                    mViewHolder.nomePartido.setEnabled(true);
                    mViewHolder.numeroUrna.setEnabled(true);
                    mViewHolder.cargo.setEnabled(true);
                    mViewHolder.numeroVotos.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mViewHolder.numeroVotos.setEnabled(true);
                    mViewHolder.estadoCandidato.setEnabled(true);
                    mViewHolder.municipioCandidato.setEnabled(true);
                    mViewHolder.salvarCandidato.setEnabled(true);
                } else {
                    atualizar();
                    povoate();
                    mViewHolder.nomeCandidato.setEnabled(false);
                    mViewHolder.nomePartido.setEnabled(false);
                    mViewHolder.numeroUrna.setEnabled(false);
                    mViewHolder.cargo.setEnabled(false);
                    mViewHolder.numeroVotos.setEnabled(false);
                    mViewHolder.estadoCandidato.setEnabled(false);
                    mViewHolder.municipioCandidato.setEnabled(false);
                    mViewHolder.salvarCandidato.setEnabled(false);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonSalvarCandidato){

            if(     mViewHolder.nomeCandidato.getText().toString().equals("") ||
                    mViewHolder.nomePartido.getText().toString().equals("") ||
                    mViewHolder.numeroUrna.getText().toString().equals("") ||
                    mViewHolder.cargo.getText().toString().equals("") ||
                    mViewHolder.numeroVotos.getText().toString().equals("") ||
                    mViewHolder.estadoCandidato.getText().toString().equals("") ||
                    mViewHolder.municipioCandidato.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Existem campos em branco!", Toast.LENGTH_SHORT).show();
            } else{
                atualizar();
                finish();
                Toast.makeText(getApplicationContext(), "ue!", Toast.LENGTH_SHORT).show();
            }
        }

        if (id == R.id.excluirCandidato){
            excluir();
        }

    }

    private void excluir() {

        new AlertDialog.Builder(this).setTitle("Deletar Candidato").
                setMessage("Tem certeza que deseja excluir este Candidato?").
                setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        candidato.deleteFromRealm();
                        realm.commitTransaction();
                        realm.close();
                        finish();
                    }
                }).setNegativeButton("Não", null).show();
    }

    public static class ViewHolder{
        EditText nomeCandidato, nomePartido, cargo, numeroUrna, numeroVotos, estadoCandidato, municipioCandidato;
        Button salvarCandidato;
        Switch habilitarEdicaoCandidato;
        TextView excluir;
    }

    public void atualizar() {

        String token;
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        if (idCandidato.equals("0")) {
            candidato = realm.where(Candidato.class).equalTo("id", idCandidato).findFirst();
            if (candidato == null) {
                token = getRandomHexString();
                candidato = new Candidato();
                candidato.setId(token);
            }
        }

        candidato.setNome(mViewHolder.nomeCandidato.getText().toString());
        candidato.setPartido(mViewHolder.nomePartido.getText().toString());
        candidato.setNumeroUrna(mViewHolder.numeroUrna.getText().toString());
        candidato.setCargo(mViewHolder.cargo.getText().toString());
        candidato.setNumeroVotos(Integer.parseInt(mViewHolder.numeroVotos.getText().toString()));
        candidato.setUf(mViewHolder.estadoCandidato.getText().toString());
        candidato.setMunicipio(mViewHolder.municipioCandidato.getText().toString());

        realm.copyToRealmOrUpdate(candidato);
        realm.commitTransaction();
        realm.close();
    }

    public void povoate() {

        realm = Realm.getDefaultInstance();
        candidato = realm.where(Candidato.class).equalTo("id", idCandidato).findFirst();
        realm.close();

        if (!idCandidato.equals("0")){
            mViewHolder.nomeCandidato.setEnabled(false);
            mViewHolder.nomePartido.setEnabled(false);
            mViewHolder.numeroUrna.setEnabled(false);
            mViewHolder.cargo.setEnabled(false);
            mViewHolder.numeroVotos.setEnabled(false);
            mViewHolder.estadoCandidato.setEnabled(false);
            mViewHolder.municipioCandidato.setEnabled(false);
            mViewHolder.salvarCandidato.setEnabled(false);

            mViewHolder.nomeCandidato.setText(candidato.getNome());
            mViewHolder.nomePartido.setText(candidato.getPartido());
            mViewHolder.numeroUrna.setText(candidato.getNumeroUrna());
            mViewHolder.cargo.setText(candidato.getCargo());
            mViewHolder.numeroVotos.setInputType(InputType.TYPE_CLASS_TEXT);
            mViewHolder.numeroVotos.setText(candidato.getNumeroVotos());
            mViewHolder.estadoCandidato.setText(candidato.getUf());
            mViewHolder.municipioCandidato.setText(candidato.getMunicipio());
        }
    }

    public String getRandomHexString(){
        int numchars = 6;
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
}
