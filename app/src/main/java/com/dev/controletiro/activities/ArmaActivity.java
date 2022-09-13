package com.dev.controletiro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.controletiro.R;
import com.dev.controletiro.database.ProjectDataBase;
import com.dev.controletiro.database.dao.ArmaDAO;
import com.dev.controletiro.model.Arma;
import com.dev.controletiro.model.Usuario;
import com.dev.controletiro.model.enums.CalibreArma;
import com.dev.controletiro.model.enums.TipoArma;
import com.dev.controletiro.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class ArmaActivity extends AppCompatActivity {

    private Spinner spinnerTipoArma, spinnerCalibreArma;
    private TextView txtIdArma;
    private Button buttonSalvarArma;
    private TextInputEditText txtdescricaoarma, txtnumeroregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arma);

        init();
        Arma armaRecuperado = finByGetArma();

        if(armaRecuperado != null){

        }

        String[] listaTipoArma = getTiposArmasStrings();

        String[] listaCalibreArma = getCalibreArmasStrings();

        preencherSpinner(listaTipoArma, spinnerTipoArma);

        preencherSpinner(listaCalibreArma, spinnerCalibreArma);

        buttonSalvarArma.setOnClickListener(view -> {
            String msg = "";
            String descricaoArma = txtdescricaoarma.getText().toString();
            int tipoArma = spinnerTipoArma.getSelectedItemPosition();
            int calibreArma = spinnerCalibreArma.getSelectedItemPosition();
            String numeroRegistro = txtnumeroregistro.getText().toString();

            if(descricaoArma.isEmpty()){
                msg += "Campo Descrição deve ser preenchido.\n";
            }

            if(tipoArma < 1){
                msg += "Tipo da Arma deve ser selecionado.\n";
            }

            if(calibreArma < 1){
                msg += "Calibre da Arma deve ser selecionado.\n";
            }

            if(numeroRegistro.isEmpty()){
                msg += "Campo Número de Registro deve ser preenchido.\n";
            }

            if (!msg.equals("")) {
                Util.showMsgToast(ArmaActivity.this, msg);
            } else{

            }

            Arma arma = recuperaArma();
            salvarArma(arma);
            Util.showMsgToast(ArmaActivity.this, "Salvo com sucesso.");
        });

    }

    private Boolean recuperarDadosDeOutraTela() {
        Intent intent = getIntent();
        Bundle recuperaDados = intent.getExtras();
        if(recuperaDados != null){
            Arma armaRecuperado = (Arma) recuperaDados.get("arma");
            if(armaRecuperado != null){
                preenc(usuarioRecuperado);
                return true;
            }
        }
        return false;
    }

    private void preencherSpinner(String[] listaParam, Spinner spinnerTipoArma) {
        ArrayList<String> lista = new ArrayList<>(Arrays.asList(listaParam));
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lista);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoArma.setAdapter(adapterTipo);
    }

    @NonNull
    private String[] getCalibreArmasStrings() {
        String listaCalibreArma[] = {
                "Selecione o Calibre",
                CalibreArma.MM22.getCalibre(),
                CalibreArma.MM25.getCalibre(),
                CalibreArma.MM32.getCalibre(),
                CalibreArma.MM380.getCalibre(),
                CalibreArma.MM40.getCalibre(),
                CalibreArma.MM9.getCalibre(),
                CalibreArma.MM44.getCalibre(),
                CalibreArma.MM45.getCalibre(),
                CalibreArma.MM357.getCalibre()
        };
        return listaCalibreArma;
    }

    @NonNull
    private String[] getTiposArmasStrings() {
        String listaTipoArma[] = {
                "Selecione o Tipo de Arma",
                TipoArma.REVOLVER.getTipoArma(),
                TipoArma.PISTOLA.getTipoArma(),
                TipoArma.CARABINA.getTipoArma(),
                TipoArma.FUZIL.getTipoArma(),
                TipoArma.ESPINGARDA.getTipoArma(),
                TipoArma.METRALHADORA.getTipoArma(),
                TipoArma.SUBMETRALHADORA.getTipoArma(),
        };
        return listaTipoArma;
    }

    private void init(){
        spinnerTipoArma = findViewById(R.id.spinner_tipoarma);
        spinnerCalibreArma = findViewById(R.id.spinner_calibrearma);
        txtIdArma = findViewById(R.id.txtidarma);
        txtdescricaoarma = findViewById(R.id.txtdescricaoarma);
        txtnumeroregistro = findViewById(R.id.txtnumeroregistro);
        buttonSalvarArma = findViewById(R.id.btnsalvararma);
    }

    private void preencherCampos(Arma arma){
        txtIdArma.setText(arma.getIdArma().toString());
        txtdescricaoarma.setText(arma.getDescricao());

    }

    private void salvarArma(Arma arma){
        if(arma != null){
            ArmaDAO armaDAO = ProjectDataBase.getInstance(ArmaActivity.this).getArmaDAO();
            armaDAO.saveArma(arma);
        } else {
            Util.showMsgConfirm(ArmaActivity.this, "Atenção", "Objeto arma não pode ser vazio.", null);
        }
    }

    private Arma finByGetArma(){
        ArmaDAO armaDAO = ProjectDataBase.getInstance(ArmaActivity.this).getArmaDAO();
        Arma arma = armaDAO.findByOneArma();
        return arma;
    }

    private Arma recuperaArma(){
        Arma arma = new Arma();
        arma.setDescricao(txtdescricaoarma.getText().toString());
        arma.setTipoArma(String.valueOf(spinnerTipoArma.getSelectedItem()));
        arma.setCalibreArma(String.valueOf(spinnerCalibreArma.getSelectedItem()));
        arma.setNumeroRegistro(txtdescricaoarma.getText().toString());
        return arma;
    }



}