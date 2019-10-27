package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lostfound.config.ConfiguracaoFireBase;
import com.example.lostfound.helper.Base64Custon;
import com.example.lostfound.model.DocumentoVeiculo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcheiDocVeiculoActivity extends AppCompatActivity {


    private EditText nome;
    private EditText cpf;
    private EditText placa;
    private EditText modelo;
    private Button adicionar;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("DocVeiculo");

    private DocumentoVeiculo documentoVeiculo;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achei_doc_veiculo);

        nome = findViewById(R.id.editDVNomeCompleto);
        cpf = findViewById(R.id.editDVCPF);
        placa = findViewById(R.id.editPlaca);
        modelo = findViewById(R.id.editModelo);
        adicionar = findViewById(R.id.btnDVAdicionar);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDV();
            }
        });





    }

    public void adicionarDV(){

        firebaseAuth = ConfiguracaoFireBase.getFirebaseAutenticacao();
        documentoVeiculo = new DocumentoVeiculo();
        documentoVeiculo.setNome(nome.getText().toString());
        documentoVeiculo.setCPF(cpf.getText().toString());
        documentoVeiculo.setModelo(modelo.getText().toString());
        documentoVeiculo.setPlaca(placa.getText().toString());
        String idUsuario = Base64Custon.codificarBase64(firebaseAuth.getCurrentUser().getEmail());
        documentoVeiculo.setQuemAchou(idUsuario);
        documentoVeiculo.setStatus(true);

        reference.push().setValue(documentoVeiculo);




    }
}
