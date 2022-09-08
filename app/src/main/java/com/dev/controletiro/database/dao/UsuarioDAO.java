package com.dev.controletiro.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dev.controletiro.model.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Insert
    void saveUser(Usuario usuario);

    @Query("SELECT * FROM usuario")
    List<Usuario> findAllUsers();

    @Delete
    void removeUser(Usuario usuario);

    @Update
    void updateUser(Usuario usuario);

    @Query("SELECT * FROM usuario LIMIT 1")
    Usuario findByOneUser();

    @Query("SELECT count(*) FROM usuario")
    Integer countUser();

}
