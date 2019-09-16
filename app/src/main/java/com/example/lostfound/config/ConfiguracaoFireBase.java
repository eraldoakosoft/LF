package com.example.lostfound.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFireBase {
    private static FirebaseAuth autenticacao;


    // RETORNA A INSTANCIA DO FIREBASE
    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;


    }



}
