package br.edu.iff.pooa.trabalho02_2018_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.iff.pooa.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class InfoEleitorActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private Realm realm;
    private Eleitor eleitor;
    private int idEleitor;
    SimpleDateFormat maskData = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_eleitor);

        this.mViewHolder.nomeEleitor = (EditText) findViewById(R.id.edtTextInfoNomeEleitor);
        this.mViewHolder.nomeMaeEleitor = (EditText) findViewById(R.id.edtTextInfoNomeMaeEleitor);
        this.mViewHolder.dataNascimentoEleitor = (EditText) findViewById(R.id.edtTextInfoDataNascimentoEleitor);
        this.mViewHolder.tituloEleitor = (EditText) findViewById(R.id.edtTextInfoTituloEleitor);
        this.mViewHolder.zonaEleitor = (EditText) findViewById(R.id.edtTextInfoZonaEleitor);
        this.mViewHolder.secaoEleitor = (EditText) findViewById(R.id.edtTextinfoSecaoEleitor);
        this.mViewHolder.municipioEleitor = (EditText) findViewById(R.id.edtTextInfoMunicipioEleitor);
        this.mViewHolder.salvarEleitor = (Button) findViewById(R.id.buttonSalvarEleitor);
        this.mViewHolder.habilitarEdicaoEleitor = (Switch) findViewById(R.id.switchHabilitarEdicaoEleitor);

        this.mViewHolder.salvarEleitor.setOnClickListener(this);

        Intent intent = getIntent();
        idEleitor = (Integer) intent.getSerializableExtra("id");
        if (idEleitor == 0)
        {
            this.mViewHolder.habilitarEdicaoEleitor.setVisibility(View.INVISIBLE);
            this.mViewHolder.habilitarEdicaoEleitor.setActivated(false);
            this.mViewHolder.habilitarEdicaoEleitor.setSplitTrack(false);
        }

        povoate();

        this.mViewHolder.habilitarEdicaoEleitor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mViewHolder.nomeEleitor.setEnabled(true);
                    mViewHolder.nomeMaeEleitor.setEnabled(true);
                    mViewHolder.dataNascimentoEleitor.setInputType(InputType.TYPE_CLASS_DATETIME);
                    mViewHolder.dataNascimentoEleitor.setEnabled(true);
                    mViewHolder.tituloEleitor.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mViewHolder.tituloEleitor.setEnabled(true);
                    mViewHolder.zonaEleitor.setEnabled(true);
                    mViewHolder.secaoEleitor.setEnabled(true);
                    mViewHolder.municipioEleitor.setEnabled(true);
                    mViewHolder.salvarEleitor.setEnabled(true);
                } else {
                    atualizar();
                    povoate();
                    mViewHolder.nomeEleitor.setEnabled(false);
                    mViewHolder.nomeMaeEleitor.setEnabled(false);
                    mViewHolder.dataNascimentoEleitor.setEnabled(false);
                    mViewHolder.tituloEleitor.setEnabled(false);
                    mViewHolder.zonaEleitor.setEnabled(false);
                    mViewHolder.secaoEleitor.setEnabled(false);
                    mViewHolder.municipioEleitor.setEnabled(false);
                    mViewHolder.salvarEleitor.setEnabled(false);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonSalvarEleitor){
            atualizar();
            povoate();
            finish();
        }
    }

    public static class ViewHolder{
        EditText nomeEleitor, nomeMaeEleitor, dataNascimentoEleitor, tituloEleitor, zonaEleitor, secaoEleitor, municipioEleitor;
        Button salvarEleitor;
        Switch habilitarEdicaoEleitor;
    }

    public void atualizar() {

        int token;
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        if (idEleitor == 0) {
            eleitor = realm.where(Eleitor.class).equalTo("id", idEleitor).findFirst();
            if (eleitor == null) {
                token = 1;
            } else {
                token = realm.where(Eleitor.class).max("id").intValue() + 1;
            }
            eleitor.setId(token);
        }

        eleitor.setNome(mViewHolder.nomeEleitor.getText().toString());
        eleitor.setNomeMae(mViewHolder.nomeMaeEleitor.getText().toString());
        try {
            eleitor.setDataNascimento(maskData.parse(mViewHolder.dataNascimentoEleitor.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eleitor.setNumeroTitulo(mViewHolder.tituloEleitor.getText().toString());
        eleitor.setZona(mViewHolder.zonaEleitor.getText().toString());
        eleitor.setSecao(mViewHolder.secaoEleitor.getText().toString());
        eleitor.setMunicipio(mViewHolder.municipioEleitor.getText().toString());

        realm.copyToRealmOrUpdate(eleitor);
        realm.commitTransaction();
        realm.close();
    }

    public void povoate() {

        realm = Realm.getDefaultInstance();
        eleitor = realm.where(Eleitor.class).equalTo("id", idEleitor).findFirst();
        realm.close();

        if (idEleitor != 0){
            mViewHolder.nomeEleitor.setText(eleitor.getNome());
            mViewHolder.nomeMaeEleitor.setText(eleitor.getNomeMae());
            mViewHolder.dataNascimentoEleitor.setInputType(InputType.TYPE_CLASS_TEXT);
            mViewHolder.dataNascimentoEleitor.setText(eleitor.getDataNascimento().toString());
            mViewHolder.tituloEleitor.setInputType(InputType.TYPE_CLASS_TEXT);
            mViewHolder.tituloEleitor.setText(eleitor.getNumeroTitulo());
            mViewHolder.zonaEleitor.setText(eleitor.getZona());
            mViewHolder.secaoEleitor.setText(eleitor.getSecao());
            mViewHolder.municipioEleitor.setText(eleitor.getMunicipio());
            mViewHolder.salvarEleitor.setText(eleitor.getMunicipio());
        }
    }
}
