package com.example.lostfound.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostfound.R;
import com.example.lostfound.model.Cartao;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Cartao> listcard;



    public Adapter(List<Cartao> listaCartao) {
        this.listcard = listaCartao;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);



        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Cartao cartao = listcard.get(position);

        holder.nome.setText(cartao.getNome());
        holder.nomeQuemAdicionou.setText(cartao.getQuemAchou());
        holder.dataAdicionado.setText(cartao.getData_entrada());
        holder.tipo.setText(cartao.getBanco());

    }

    @Override
    public int getItemCount() {
        return  listcard.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView nome;
        TextView dataAdicionado;
        TextView nomeQuemAdicionou;
        TextView tipo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNome);
            dataAdicionado = itemView.findViewById(R.id.textdata);
            nomeQuemAdicionou = itemView.findViewById(R.id.textNomeAchou);
            tipo = itemView.findViewById(R.id.textTipo);

        }
    }

}
