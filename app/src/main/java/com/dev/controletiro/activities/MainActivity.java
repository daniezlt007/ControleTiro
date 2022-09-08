package com.dev.controletiro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.dev.controletiro.R;
import com.dev.controletiro.database.ProjectDataBase;
import com.dev.controletiro.database.dao.UsuarioDAO;
import com.dev.controletiro.util.Util;

public class MainActivity extends AppCompatActivity {

    private CardView cardviewconfig;
    private Integer verificarExecucao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verificarExecucao = 1;
        initComponents();
        verificarUsuarioCadastrado();


        cardviewconfig.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(verificarExecucao < 1){
            verificarUsuarioCadastrado();
        }
    }

    private void verificarUsuarioCadastrado() {
        if(countUser() >= 1){
            Util.showMsgToast(MainActivity.this, "Sistema carregado.");
        } else {
            Util.showMsgConfirm(MainActivity.this, "Cadastrar Usuário", "É necessário criar um usuário para perfil.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, FormUserActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private Integer countUser(){
        UsuarioDAO usuarioDAO = ProjectDataBase.getInstance(this).getUsuarioDAO();
        Integer count = usuarioDAO.countUser();
        return count;
    }

    private void initComponents(){
        cardviewconfig = findViewById(R.id.cardviewconfig);
    }


}