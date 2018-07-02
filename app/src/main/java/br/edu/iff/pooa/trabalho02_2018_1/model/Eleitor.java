package br.edu.iff.pooa.trabalho02_2018_1.model;

import java.io.Serializable;
import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Eleitor extends RealmObject implements Serializable {

    @PrimaryKey
    private String id;
    private String nome;
    private String nomeMae;
    private Date dataNascimento;
    private String numeroTitulo;
    private String zona;
    private String secao;
    private String municipio;

    public Eleitor() {}

    public Eleitor(String id, String nome, String nomeMae, Date dataNascimento, String numTitulo, String zona, String secao, String municipio) {
        this.id = id;
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.dataNascimento = dataNascimento;
        this.numeroTitulo = numTitulo;
        this.zona = zona;
        this.secao = secao;
        this.municipio = municipio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(String numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
