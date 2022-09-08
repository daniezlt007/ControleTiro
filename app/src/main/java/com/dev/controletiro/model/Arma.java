package com.dev.controletiro.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dev.controletiro.model.enums.CalibreArma;
import com.dev.controletiro.model.enums.TipoArma;

import java.io.Serializable;

@Entity(tableName = "arma")
public class Arma implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idarma")
    private Long idArma;

    private String descricao;

    @ColumnInfo(name = "tipoarma")
    private TipoArma tipoArma;

    @ColumnInfo(name = "calibrearma")
    private CalibreArma calibreArma;

    @ColumnInfo(name = "numeroregistro")
    private String numeroRegistro;

    public Arma() {

    }

    public Arma(String descricao, TipoArma tipoArma, CalibreArma calibreArma, String numeroRegistro) {
        this.descricao = descricao;
        this.tipoArma = tipoArma;
        this.calibreArma = calibreArma;
        this.numeroRegistro = numeroRegistro;
    }

    public Arma(Long idArma, String descricao, TipoArma tipoArma, CalibreArma calibreArma, String numeroRegistro) {
        this.idArma = idArma;
        this.descricao = descricao;
        this.tipoArma = tipoArma;
        this.calibreArma = calibreArma;
        this.numeroRegistro = numeroRegistro;
    }

    public Long getIdArma() {
        return idArma;
    }

    public void setIdArma(Long idArma) {
        this.idArma = idArma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoArma getTipoArma() {
        return tipoArma;
    }

    public void setTipoArma(TipoArma tipoArma) {
        this.tipoArma = tipoArma;
    }

    public CalibreArma getCalibreArma() {
        return calibreArma;
    }

    public void setCalibreArma(CalibreArma calibreArma) {
        this.calibreArma = calibreArma;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }
}
