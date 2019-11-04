package com.example.lost.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lost.adapter.Adapter;
import com.example.lost.model.Ajuda;
import com.example.lost.model.Documento;
import com.example.lost.model.Usuario;
import com.example.lostfound.R;
import com.example.lost.model.Cartao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewHome;
    private String nomeUsuario = "Nada";

    private Ajuda ajuda;


   //METODO DO YOUTUBE  INICIO
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private List<Cartao> listcard = new ArrayList<Cartao>();
    private List<Usuario> listUsuario = new ArrayList<>();
    private List<Documento> listDocumento = new ArrayList<Documento>();
    //private List<Ajuda> listaAjuda = new ArrayList<Ajuda>();
    //FIM

    //private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    //final DatabaseReference cartoes = reference.child("Cartoes");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewHome = root.findViewById(R.id.recyclerViewHome);

        inicializarFirebase();
        pesquisarCartoes();
       //pesquisarDocumento();
        return root;
    }


    public void inicializarFirebase(){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void pesquisarCartoes(){
        System.out.println("----------------------------------------------------------------");
        Query query;
        query = databaseReference.child("Cartoes").orderByKey().limitToLast(10);

        listcard.clear();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnap: dataSnapshot.getChildren()){
                    Cartao cartao = dataSnap.getValue(Cartao.class);
                    //Log.i("FIRE --->", " Banco ==: "+cartao.getBanco());
                    //Log.i("FIRE --->","Data ==: "+cartao.getData_entrada());

                    System.out.println("Pesquisa cartoes | Nome: " +  cartao.getQuemAchou());
                    //System.out.println("Pesquisa cartoes | Resultado da funcao :"+pesquisarNome(cartao.getQuemAchou()));
                    //Log.i("FIRE ---> ","PESQUISA CARTOES Nome ==: " + cartao.getQuemAchou());
                    //pesquisarNome(cartao.getQuemAchou());

                    //cartao.setQuemAchou(pesquisarNome(cartao.getQuemAchou()));
                    System.out.println(" Pesquisa cartoes | cartao.getQuemAchou ------ :" + cartao.getQuemAchou());
                    pesquisarNome(cartao.getQuemAchou());
                    System.out.println("Pesquisa cartoes | nomeUsuario: " + nomeUsuario);

                    listcard.add(cartao);
                    //ajuda = new Ajuda();
                    //ajuda.setCartao(cartao);
                    //listaAjuda.add(ajuda);
                    //System.out.println("+++++++++++++++++++++++++++++++++++++");
                }

                //configurar adapter
                Adapter adapter = new Adapter(listcard);
                //CONFIGURAR RECICLERVIEW
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerViewHome.setLayoutManager(layoutManager);
                recyclerViewHome.setHasFixedSize(true);
                recyclerViewHome.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                recyclerViewHome.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public void pesquisarNome(String nome){

        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        //Log.i("FIRE --->  ","Nome Pesquisa Nome: "+nome);
        System.out.println("PesquisaNome | Nome parametro: " + nome );
        Query query;
        query = databaseReference.child("usuarios").orderByKey().equalTo(nome);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnap: dataSnapshot.getChildren()){
                    Usuario usuario = dataSnap.getValue(Usuario.class);
                    nomeUsuario = usuario.getNome();

                    if(!nomeUsuario.equals("")) {
                        //nomeUsuario = usuario.getNome();
                        System.out.println("PesquisaNome | Nome dentro do if " + nomeUsuario);
                        //Log.i("FIRE --->  ", "Nome Dentro do for ==: " + nomeUsuario);
                        //break;

                    }
                    //System.out.println("PesquisaNome | Nome fora do if " + nomeUsuario);
                    //Log.i("FIRE --->  ","Fora do If" + usuario.getNome());




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        System.out.println("PesquisaNome | Nome Return : " + nomeUsuario);
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        //return nomeUsuario;

    }


    public void pesquisarDocumento(){
        Query query;
        query = databaseReference.child("Documentos");

        listDocumento.clear();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnap: dataSnapshot.getChildren()){
                    Documento documento = dataSnap.getValue(Documento.class);
                    //Log.i("FIRE --->", " Banco ==: "+cartao.getBanco());
                    //Log.i("FIRE --->","Data ==: "+cartao.getData_entrada());
                    Log.i("FIRE --->","Nome ==: "+documento.getIdQuemAchou());
                    //pesquisarNome(cartao.getQuemAchou());

                    ///cartao.setQuemAchou(nomeUsuario);
                    listDocumento.add(documento);
                }

                //configurar adapter
                //Adapter adapter = new Adapter(listDocumento);
                //CONFIGURAR RECICLERVIEW
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerViewHome.setLayoutManager(layoutManager);
                recyclerViewHome.setHasFixedSize(true);
                recyclerViewHome.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                //recyclerViewHome.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}