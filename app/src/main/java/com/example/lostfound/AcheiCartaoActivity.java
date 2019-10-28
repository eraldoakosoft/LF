package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.example.lostfound.config.ConfiguracaoFireBase;
import com.example.lostfound.helper.Base64Custon;
import com.example.lostfound.model.Cartao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcheiCartaoActivity extends AppCompatActivity {


    private TextView txtNome;
    private TextView txtBanco;
    private TextView txtDigitos;
    private Button btnAdicionar;

    private Cartao cartao;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cartoes");
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achei_cartao);

        txtNome = findViewById(R.id.editACNome);
        txtBanco = findViewById(R.id.editACBanco);
        txtDigitos = findViewById(R.id.editACDigitos);
        btnAdicionar = findViewById(R.id.btnACAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarCartao();
            }
        });




    }

    public void adicionarCartao(){
        cartao = new Cartao();

        firebaseAuth = ConfiguracaoFireBase.getFirebaseAutenticacao();

        String idUsuario = Base64Custon.codificarBase64(firebaseAuth.getCurrentUser().getEmail());

        cartao.setNome(txtNome.getText().toString());
        cartao.setBanco(txtBanco.getText().toString());
        cartao.setDigitos(txtDigitos.getText().toString());
        cartao.setStatus(true);
        cartao.setQuemAchou(idUsuario);
        String dat = new Date().toString();
        cartao.setData_entrada(getDateTime());
        cartao.setData_saida("0000-00-00");
        reference.push().setValue(cartao);

        Toast.makeText(AcheiCartaoActivity.this, "Salvo com Sucesso!" , Toast.LENGTH_SHORT).show();
        finish();




    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
