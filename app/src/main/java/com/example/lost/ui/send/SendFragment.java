package com.example.lost.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lost.MainActivity;
import com.example.lostfound.R;
import com.google.firebase.auth.FirebaseAuth;

public class SendFragment extends Fragment {

    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sendViewModel = ViewModelProviders.of(this).get(SendViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {

                        usuario.signOut();
                        startActivity(new Intent(root.getContext(), MainActivity.class));
                        getActivity().finish();

                    }
        });
        return root;
    }



}