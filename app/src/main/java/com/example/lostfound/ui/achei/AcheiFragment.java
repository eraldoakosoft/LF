package com.example.lostfound.ui.achei;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lostfound.AcheiActivity;
import com.example.lostfound.Main2Activity;
import com.example.lostfound.R;
import com.example.lostfound.model.Documento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AcheiFragment extends Fragment {

    private AcheiViewModel acheiViewModel;

    private EditText txtNome;
    private EditText txtTipo;
    private EditText txtCpf;
    private EditText txtRg;
    private Button btnAdiciona;


    Documento documento;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("objetos");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        acheiViewModel = ViewModelProviders.of(this).get(AcheiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_achei, container, false);


        txtTipo = root.findViewById(R.id.edit1Tipo);
        txtNome = root.findViewById(R.id.edit1Nome);
        txtCpf = root.findViewById(R.id.edit1CPF);
        txtRg = root.findViewById(R.id.edit1RG);
        btnAdiciona = root.findViewById(R.id.btn1Adicionar);

        btnAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoAdicionar();
                limparCampos();
            }
        });




        return root;
    }


    public void botaoAdicionar(){
        String campoTipo = txtTipo.getText().toString();
        String campoNome = txtNome.getText().toString();
        String campoCPF = txtCpf.getText().toString();
        String campoRG = txtRg.getText().toString();
        documento = new Documento();
        documento.setStatus(true);
        documento.setTipo(campoTipo);
        documento.setNome(campoNome);
        documento.setCpf(campoCPF);
        documento.setRg(campoRG);

        reference.push().setValue(documento);




    }

    public void limparCampos(){
        txtTipo.setText("");
        txtRg.setText("");
        txtNome.setText("");
        txtCpf.setText("");
    }




}