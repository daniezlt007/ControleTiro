package com.dev.controletiro.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dev.controletiro.model.Arma;
import com.dev.controletiro.model.Usuario;

import java.util.List;

@Dao
public interface ArmaDAO {

    @Insert
    void saveArma(Arma arma);

    @Query("SELECT * FROM arma")
    List<Arma> findAllArmas();

    @Delete
    void removeArma(Arma arma);

    @Update
    void updateArma(Arma Arma);

    @Query("SELECT * FROM arma LIMIT 1")
    Arma findByOneArma();

    @Query("SELECT count(*) FROM arma")
    Integer countArma();

}
