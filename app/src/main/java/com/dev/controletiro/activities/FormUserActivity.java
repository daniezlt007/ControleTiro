package com.dev.controletiro.activities;

import static com.dev.controletiro.util.Util.converterBitmapBlob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.controletiro.R;
import com.dev.controletiro.database.ProjectDataBase;
import com.dev.controletiro.database.dao.UsuarioDAO;
import com.dev.controletiro.model.Usuario;
import com.dev.controletiro.util.Util;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class FormUserActivity extends AppCompatActivity {

    private TextView txtId;
    private TextInputEditText txtNome, txtEmail, txtDataNascimento, txtCertificadoRegistro;
    private Button buttonSalvar, btndatanascimento, btnFotoPerfil, btnFotoCr;
    private ImageView imgFotoPerfil, imgFotoCr;

    private static final int pic_id = 123;
    private int codigo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);
        initComponentes();

        Usuario usuarioRecuperado = finByGetUser();
        if (recuperarDadosDeOutraTela()) {
            buttonSalvar.setText("Editar");
            codigo = 3;
            preencherCampos(usuarioRecuperado);
        }

        MaterialDatePicker.Builder materialDataBuilder = MaterialDatePicker.Builder.datePicker();
        materialDataBuilder.setTitleText("SELECIONE A DATA");
        final MaterialDatePicker materialDatePicker = materialDataBuilder.build();

        btndatanascimento.setOnClickListener(
                view -> materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER")
        );

        materialDatePicker.addOnPositiveButtonClickListener(
                selection -> txtDataNascimento.setText(Util.converteData((Long) selection))
        );


        btnFotoPerfil.setOnClickListener(view -> {
            codigo = 1;
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, pic_id);
        });

        btnFotoCr.setOnClickListener(view -> {
            codigo = 2;
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, pic_id);
        });


        buttonSalvar.setOnClickListener(view -> {
            String msg = "";
            String nome = txtNome.getText().toString();
            String email = txtEmail.getText().toString();
            String dataNasc = txtDataNascimento.getText().toString();
            String certificado = txtCertificadoRegistro.getText().toString();
            if (nome.isEmpty()) {
                msg += "Campo Nome deve ser preenchido.\n";
            }
            if (email.isEmpty()) {
                msg += "Campo Email deve ser preenchido.\n";
            }
            if (dataNasc.isEmpty()) {
                msg += "Campo Data Nasc. deve ser preenchido.\n";
            }
            if(imgFotoPerfil.getDrawable() == null){
                msg += "A imagem de perfil deve ser selecionada.\n";
            }

            if(imgFotoCr.getDrawable() == null){
                msg += "A imagem do QRCode do CR deve ser selecionada.\n";
            }
            if (certificado.isEmpty()) {
                msg += "Certificado de Registro deve ser preenchido.\n";
            }
            if (!msg.equals("")) {
                Util.showMsgToast(FormUserActivity.this, msg);
            } else {
                if (countUser() >= 1 && codigo < 3) {
                    Util.showMsgToast(FormUserActivity.this, "Já possui um usuário cadastrado.");
                } else {
                    if(usuarioRecuperado == null){
                        Usuario usuario = new Usuario(nome, email, dataNasc, converterBitmapBlob(imgFotoPerfil), converterBitmapBlob(imgFotoCr), certificado);
                        salvarUser(usuario);
                        Util.showMsgToast(FormUserActivity.this, "Salvo com sucesso.");
                        finish();
                    }else{
                        Usuario usuarioEditado = recuperaUsuario();
                        editarUser(usuarioEditado);
                        Util.showMsgToast(FormUserActivity.this, "Editado com sucesso.");
                        finish();
                    }
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id && resultCode == RESULT_OK) {
            if (codigo == 1) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgFotoPerfil.setImageBitmap(bitmap);
            }
        }

        if (requestCode == pic_id && resultCode == RESULT_OK) {
            if (codigo == 2) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgFotoCr.setImageBitmap(bitmap);
            }
        }

    }

    private void initComponentes() {
        txtId = findViewById(R.id.txtid);
        txtNome = findViewById(R.id.txtnome);
        txtEmail = findViewById(R.id.txtemail);
        txtDataNascimento = findViewById(R.id.txtdatanascimento);
        btndatanascimento = findViewById(R.id.btndatanascimento);
        buttonSalvar = findViewById(R.id.btnsalvar);
        btnFotoPerfil = findViewById(R.id.btnFotoPerfil);
        btnFotoCr = findViewById(R.id.btnFotoCr);
        imgFotoPerfil = findViewById(R.id.imgFotoPerfil);
        imgFotoCr = findViewById(R.id.imgFotoCR);
        txtCertificadoRegistro = findViewById(R.id.txtcr);
    }

    private Boolean recuperarDadosDeOutraTela() {
        Intent intent = getIntent();
        Bundle recuperaDados = intent.getExtras();
        if(recuperaDados != null){
            Usuario usuarioRecuperado = (Usuario) recuperaDados.get("usuario");
            if(usuarioRecuperado != null){
                preencherCampos(usuarioRecuperado);
                return true;
            }
        }
        return false;
    }

    private Usuario recuperaUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(Long.parseLong(txtId.getText().toString()));
        usuario.setNome(txtNome.getText().toString());
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setDataNascimento(txtDataNascimento.getText().toString());
        usuario.setCertificadoRegistro(txtCertificadoRegistro.getText().toString());
        usuario.setFotoPerfil(converterBitmapBlob(imgFotoPerfil));
        usuario.setFotoRegistro(converterBitmapBlob(imgFotoCr));
        return usuario;
    }

    private void preencherCampos(Usuario usuarioRecuperado) {
        txtId.setText(usuarioRecuperado.getId().toString());
        txtNome.setText(usuarioRecuperado.getNome());
        txtEmail.setText(usuarioRecuperado.getEmail());
        txtDataNascimento.setText(usuarioRecuperado.getDataNascimento());
        txtCertificadoRegistro.setText(usuarioRecuperado.getCertificadoRegistro());
        imgFotoPerfil.setImageBitmap(Util.converterBlobBitmap(usuarioRecuperado.getFotoPerfil()));
        imgFotoCr.setImageBitmap(Util.converterBlobBitmap(usuarioRecuperado.getFotoRegistro()));
    }

    private void salvarUser(Usuario usuario) {
        UsuarioDAO usuarioDAO = ProjectDataBase.getInstance(this).getUsuarioDAO();
        usuarioDAO.saveUser(usuario);
    }

    private void editarUser(Usuario usuario) {
        UsuarioDAO usuarioDAO = ProjectDataBase.getInstance(this).getUsuarioDAO();
        usuarioDAO.updateUser(usuario);
    }

    private Usuario finByGetUser() {
        UsuarioDAO usuarioDAO = ProjectDataBase.getInstance(this).getUsuarioDAO();
        Usuario usuario = usuarioDAO.findByOneUser();
        return usuario;
    }

    private Integer countUser() {
        UsuarioDAO usuarioDAO = ProjectDataBase.getInstance(this).getUsuarioDAO();
        Integer count = usuarioDAO.countUser();
        return count;
    }

}