package com.example.dating;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {

    Uri imageUri;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }
}