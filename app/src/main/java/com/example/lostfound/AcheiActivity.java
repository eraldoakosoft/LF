package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lostfound.model.Documento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcheiActivity extends AppCompatActivity {


    private EditText campoTipo;
    private EditText campoNome;
    private EditText campoCPF;
    private EditText campoRG;
    private Button btnAdicionar;

    private Documento documento;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("objetos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achei);


        campoTipo = findViewById(R.id.editAcheiTipo);
        campoNome = findViewById(R.id.editAcheiNome);
        campoCPF = findViewById(R.id.editAcheiCPF);
        campoRG = findViewById(R.id.editAcheiRG);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });

    }



    public void adicionar(){
        String textoTipo = campoTipo.getText().toString();
        String textoNome = campoNome.getText().toString();
        String textoCPF = campoCPF.getText().toString();
        String textoRG = campoRG.getText().toString();
        documento = new Documento();
        documento.setTipo(textoTipo);
        documento.setNome(textoNome);
        documento.setRg(textoRG);
        documento.setCpf(textoCPF);
        documento.setStatus(true);


        reference.child("001").setValue(documento);

    }

}
