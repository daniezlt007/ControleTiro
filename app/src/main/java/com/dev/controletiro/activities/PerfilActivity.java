package com.dev.controletiro.activities;

import static com.dev.controletiro.util.Util.converterBlobBitmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.controletiro.R;
import com.dev.controletiro.database.ProjectDataBase;
import com.dev.controletiro.database.dao.UsuarioDAO;
import com.dev.controletiro.model.Usuario;

public class PerfilActivity extends AppCompatActivity {

    private TextView txtperfilid, txtperfilnome, txtperfilemail,txtperfilcr, txtperfildatanascimento;
    private ImageView idperfil, idcertificado;
    private Button btneditar, btnsair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        iniciarComponentes();
        Usuario usuario = finByGetUser();
        montarObjetoNaTela(usuario);

        btneditar.setOnClickListener(view -> {
            Intent intent = new Intent(PerfilActivity.this, FormUserActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        });

        btnsair.setOnClickListener(view -> {
            Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Usuario usuario = finByGetUser();
        montarObjetoNaTela(usuario);
    }

    private void montarObjetoNaTela(Usuario usuario) {
        txtperfilid.setText(usuario.getId().toString());
        txtperfilnome.setText("Nome: " + usuario.getNome());
        txtperfilemail.setText("Email: " + usuario.getEmail());
        txtperfilcr.setText("CR: " + usuario.getCertificadoRegistro());
        txtperfildatanascimento.setText("Data Nascimento: " + usuario.getDataNascimento());
        idperfil.setImageBitmap(converterBlobBitmap(usuario.getFotoPerfil()));
        idcertificado.setImageBitmap(converterBlobBitmap(usuario.getFotoRegistro()));
    }

    private Usuario finByGetUser() {
        UsuarioDAO usuarioDAO = ProjectDataBase.getInstance(this).getUsuarioDAO();
        Usuario usuario = usuarioDAO.findByOneUser();
        return usuario;
    }

    private void iniciarComponentes(){
        txtperfilid = findViewById(R.id.txtperfilid);
        txtperfilnome = findViewById(R.id.txtperfilnome);
        txtperfilemail = findViewById(R.id.txtperfilemail);
        txtperfilcr = findViewById(R.id.txtperfilcr);
        txtperfildatanascimento = findViewById(R.id.txtperfilnascimento);
        idperfil = findViewById(R.id.idperfil);
        idcertificado = findViewById(R.id.idcertificado);
        btneditar = findViewById(R.id.btneditar);
        btnsair = findViewById(R.id.btnsair);
    }

}