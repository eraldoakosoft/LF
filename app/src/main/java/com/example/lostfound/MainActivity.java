package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lostfound.config.ConfiguracaoFireBase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart(){
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btnEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btnCadastar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }


    public void verificarUsuarioLogado(){
        auth = ConfiguracaoFireBase.getFirebaseAutenticacao();


        if(auth.getCurrentUser() != null){
            abrirTelaPrincipal();

        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, Main2Activity.class));
        finish();
    }
}
