package com.example.lost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lost.config.ConfiguracaoFireBase;
import com.example.lost.helper.Base64Custon;
import com.example.lost.model.Documento;
import com.example.lostfound.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        documento.setData_entrada(getDateTime());
        documento.setData_saida("0000-00-00");

        reference.push().setValue(documento);
        Toast.makeText(AcheiDocumentoActivity.this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
        finish();




    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
