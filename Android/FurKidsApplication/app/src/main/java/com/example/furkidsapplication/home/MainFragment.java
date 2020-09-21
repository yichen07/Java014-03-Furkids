package com.example.furkidsapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.furkidsapplication.R;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btResultA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
            }
        });

        view.findViewById(R.id.btResultB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
            }
        });

        view.findViewById(R.id.btResultC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
            }
        });

        view.findViewById(R.id.btResultD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
            }
        });
    }

    private void onButtonClick(View v) {
        Intent intent = new Intent(getContext(), ResultActivity.class);
        switch (v.getId()) {
            case R.id.btResultA:
                intent.putExtra("result", "A");
                break;
            case R.id.btResultB:
                intent.putExtra("result", "B");
                break;
            case R.id.btResultC:
                intent.putExtra("result", "C");
                break;
            case R.id.btResultD:
                intent.putExtra("result", "D");
                break;
        }
        startActivity(intent);
    }
}