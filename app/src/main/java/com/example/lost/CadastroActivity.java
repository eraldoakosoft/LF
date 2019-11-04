package com.example.lost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lostfound.R;
import com.example.lost.config.ConfiguracaoFireBase;
import com.example.lost.helper.Base64Custon;
import com.example.lost.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroActivity extends AppCompatActivity {


    private EditText campoCPF, campoRG, campoTel;

    private EditText campoNome, campoEmail, campoSenha, campoCEP, campoConfirmSenha;
    private Button botaoCadastrar;

    private static FirebaseAuth autenticacao;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        campoCPF = findViewById(R.id.txtcpf);
        campoRG = findViewById(R.id.txtrg);
        campoTel = findViewById(R.id.txtTel);
        campoNome = findViewById(R.id.txtNome);
        campoEmail = findViewById(R.id.txtEmail);
        campoSenha = findViewById(R.id.txtSenha);
        campoConfirmSenha = findViewById(R.id.txtConfirmaSenha);
        botaoCadastrar = findViewById(R.id.btnCadastra);



        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoCPF = campoCPF.getText().toString();
                String textoRG = campoRG.getText().toString();
                String textoTel = campoTel.getText().toString();
                String textoNome = campoNome.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoConfirmaSenha = campoConfirmSenha.getText().toString();


                // VALIDAR SE OS CAMPOS FORAM PREENCHIDOS
                if (!textoNome.isEmpty()){
                    if(!textoEmail.isEmpty()){
                        if(!textoSenha.isEmpty() && textoConfirmaSenha.equals(textoSenha)){
                            //SE TODOS OS CAMPOS ESTIVEREM PREENCHIDOS
                            usuario = new Usuario();
                            usuario.setEmail(textoEmail);
                            usuario.setNome(textoNome);
                            usuario.setSenha(textoSenha);
                            usuario.setCpf(textoCPF);
                            usuario.setRg(textoRG);
                            usuario.setTel(textoTel);
                            usuario.setData_cadastro(getDateTime());
                            usuario.setStatus(true);
                            cadastrarUsuario();

                        }else{
                            Toast.makeText(CadastroActivity.this, "Preenha a Senha!", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(CadastroActivity.this, "Preenha o Email!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(CadastroActivity.this, "Preenha o nome!", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }

    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFireBase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String idUsuario = Base64Custon.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();

                    finish();
                }else{
                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, digite um e-mail válido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já existe!";

                    }catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
