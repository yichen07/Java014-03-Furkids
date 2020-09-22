package com.example.furkidsapplication.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.furkidsapplication.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        NavController navController = Navigation.findNavController(this, R.id.nhf_result);
        String result = getIntent().getStringExtra("result");
        result = result != null ? result : "A";
        switch (result) {
            case "A":
                navController.navigate(R.id.resultAFragment);
                break;
            case "B":
                navController.navigate(R.id.resultBFragment);
                break;
            case "C":
                navController.navigate(R.id.resultCFragment);
                break;
            case "D":
                navController.navigate(R.id.resultDFragment);
                break;
        }

    }
}