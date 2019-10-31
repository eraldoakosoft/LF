package com.example.lostfound.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostfound.R;
import com.example.lostfound.adapter.Adapter;
import com.example.lostfound.model.Cartao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewHome;

    private List<Cartao> listcard = new ArrayList<>();

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewHome = root.findViewById(R.id.recyclerViewHome);


        final DatabaseReference cartoes = reference.child("Cartoes");

        cartoes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cartao dadosCartao = dataSnapshot.getValue(Cartao.class);



                Cartao car = new Cartao();
                for(int i =0; i < 5; i++){
                    car.setNome(dadosCartao.getNome());
                    car.setQuemAchou(dadosCartao.getQuemAchou());
                    car.setData_entrada(dadosCartao.getData_entrada());
                    car.setDigitos(dadosCartao.getDigitos());
                    car.setData_saida(dadosCartao.getData_saida());
                    car.setBanco(dadosCartao.getBanco());
                    car.setStatus(true);
                    listcard.add(car);

                }





                Log.i("FIREBASE",dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //configurar adapter
        Adapter adapter = new Adapter(listcard);






        //CONFIGURAR RECICLERVIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.addItemDecoration(new DividerItemDecoration(root.getContext(), LinearLayout.VERTICAL));
        recyclerViewHome.setAdapter(adapter);


        return root;
    }
}