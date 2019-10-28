package com.example.lostfound.ui.achei;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lostfound.AcheiCartaoActivity;
import com.example.lostfound.AcheiDocVeiculoActivity;
import com.example.lostfound.AcheiDocumentoActivity;
import com.example.lostfound.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcheiFragment extends Fragment {

    private AcheiViewModel acheiViewModel;

    private Button btnDoc;
    private Button btnDocVei;
    private Button btnCartoes;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        acheiViewModel = ViewModelProviders.of(this).get(AcheiViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_achei, container, false);


        btnDoc = root.findViewById(R.id.btnDocumento);
        btnDocVei = root.findViewById(R.id.btnDocVeiculo);
        btnCartoes = root.findViewById(R.id.btnCartao);

        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(),AcheiDocumentoActivity.class));
            }
        });

        btnDocVei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(), AcheiDocVeiculoActivity.class));
            }
        });

        btnCartoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(), AcheiCartaoActivity.class));
            }
        });


        return root;
    }

}