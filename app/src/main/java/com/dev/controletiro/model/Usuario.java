package com.dev.controletiro.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Blob;

@Entity(tableName = "usuario")
public class Usuario implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String nome;
    private String email;

    @ColumnInfo(name = "data_nascimento")
    private String dataNascimento;

    @ColumnInfo(name = "foto_perfil")
    private byte[] fotoPerfil;

    @ColumnInfo(name = "foto_registro")
    private byte[] fotoRegistro;

    @ColumnInfo(name = "certificado_registro")
    private String certificadoRegistro;

    public Usuario() {

    }

    @Ignore
    public Usuario(String nome, String email, String dataNascimento, byte[] fotoPerfil, byte[] fotoRegistro, String certificadoRegistro) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.fotoPerfil = fotoPerfil;
        this.fotoRegistro = fotoRegistro;
        this.certificadoRegistro = certificadoRegistro;
    }

    @Ignore
    public Usuario(Long id, String nome, String email, String dataNascimento, byte[] fotoPerfil, byte[] fotoRegistro, String certificadoRegistro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.fotoPerfil = fotoPerfil;
        this.fotoRegistro = fotoRegistro;
        this.certificadoRegistro = certificadoRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public byte[] getFotoRegistro() {
        return fotoRegistro;
    }

    public void setFotoRegistro(byte[] fotoRegistro) {
        this.fotoRegistro = fotoRegistro;
    }

    public String getCertificadoRegistro() {
        return certificadoRegistro;
    }

    public void setCertificadoRegistro(String certificadoRegistro) {
        this.certificadoRegistro = certificadoRegistro;
    }
}
