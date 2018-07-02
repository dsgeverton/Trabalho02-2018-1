package br.edu.iff.pooa.trabalho02_2018_1.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Candidato extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String nome;
    private String partido;
    private String numeroUrna;
    private String cargo;
    private int numeroVotos;
    private String uf;
    private String municipio;

    public Candidato() {}

    public Candidato(String nome, String partido, String numeroUrna, String cargo, int numeroVotos, String uf, String municipio) {
        this.nome = nome;
        this.partido = partido;
        this.numeroUrna = numeroUrna;
        this.cargo = cargo;
        this.numeroVotos = numeroVotos;
        this.uf = uf;
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getNumeroUrna() {
        return numeroUrna;
    }

    public void setNumeroUrna(String numeroUrna) {
        this.numeroUrna = numeroUrna;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
