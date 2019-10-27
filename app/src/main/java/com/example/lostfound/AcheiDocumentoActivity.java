package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lostfound.config.ConfiguracaoFireBase;
import com.example.lostfound.helper.Base64Custon;
import com.example.lostfound.model.Documento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcheiDocumentoActivity extends AppCompatActivity {


    private TextView txtNomeCompleto;
    private TextView txtCPF;
    private TextView txtRG;
    private TextView txtDataNascimento;
    private TextView txtNomeMae;
    private TextView txtNaturalidade;
    private Button btnAdicionarDoc;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Documentos");
    private Documento documento;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achei_documento);


        txtNomeCompleto = findViewById(R.id.editDVNomeCompleto);
        txtCPF = findViewById(R.id.editDVCPF);
        txtRG = findViewById(R.id.editRG);
        txtDataNascimento = findViewById(R.id.editDataNascimento);
        txtNomeMae = findViewById(R.id.editNomeMae);
        txtNaturalidade = findViewById(R.id.editNaturalidade);
        btnAdicionarDoc = findViewById(R.id.btnAdicionarDoc);


        btnAdicionarDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarBD();
            }
        });





    }


    public void adicionarBD(){

        documento = new Documento();


        firebaseAuth = ConfiguracaoFireBase.getFirebaseAutenticacao();
        firebaseAuth.getCurrentUser().getEmail();

        String idUsuario = Base64Custon.codificarBase64(firebaseAuth.getCurrentUser().getEmail());


        documento.setNome(txtNomeCompleto.getText().toString());
        documento.setRg(txtRG.getText().toString());
        documento.setCpf(txtCPF.getText().toString());
        documento.setNomeDaMae(txtNomeMae.getText().toString());
        documento.setNaturalidade(txtNaturalidade.getText().toString());
        documento.setDataNascimento(txtDataNascimento.getText().toString());
        documento.setIdQuemAchou( idUsuario );
        documento.setStatus(true);

        reference.push().setValue(documento);





    }
}
