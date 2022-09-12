package com.dev.controletiro.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dev.controletiro.database.dao.ArmaDAO;
import com.dev.controletiro.database.dao.UsuarioDAO;
import com.dev.controletiro.model.Arma;
import com.dev.controletiro.model.Usuario;

@Database(entities = {Usuario.class, Arma.class}, version = 2, exportSchema = false)
public abstract class ProjectDataBase extends RoomDatabase {

    private static final String NOMEDB = "controletiroapp.db";

    public abstract UsuarioDAO getUsuarioDAO();
    public abstract ArmaDAO getArmaDAO();

    public static ProjectDataBase getInstance(Context context){
        return Room
                .databaseBuilder(context, ProjectDataBase.class, NOMEDB)
                .allowMainThreadQueries()
                .build();
    }


}
