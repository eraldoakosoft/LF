package com.example.lostfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostfound.config.ConfiguracaoFireBase;
import com.example.lostfound.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //VARIAVEIS PARA LOGIN
    private EditText txtEmail, txtSenha;
    private TextView txtCadas;
    private Button btnEntar;
    private Usuario usuario;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.editText2);
        txtSenha = findViewById(R.id.editText);
        txtCadas = findViewById(R.id.textView4);
        btnEntar = findViewById(R.id.button);

        //BOTÃO ENTRAR
        btnEntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //VALIDAR CAMPOS
                String textoEmail = txtEmail.getText().toString();
                String textoSenha = txtSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin();
                    } else {
                        Toast.makeText(MainActivity.this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Preencha o Email!", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        verificarUsuarioLogado();
    }


    public void btnCadastar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }


    public void verificarUsuarioLogado() {
        auth = ConfiguracaoFireBase.getFirebaseAutenticacao();


        if (auth.getCurrentUser() != null) {
            abrirTelaPrincipal();

        }
    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(this, Main2Activity.class));
        finish();
    }


    public void validarLogin() {
        auth = ConfiguracaoFireBase.getFirebaseAutenticacao();
        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirTelaPrincipal();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao fazer Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
