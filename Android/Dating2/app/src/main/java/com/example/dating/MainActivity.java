package com.example.dating;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.dating.Fragment.HomeFragment;
import com.example.dating.Fragment.NotificationFragment;
import com.example.dating.Fragment.ProfileFragment;
import com.example.dating.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Fragment selectFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(navigationItemselectLisner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemReselectedListener navigationItemselectLisner = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    selectFragment = new HomeFragment();
                    break;
                case R.id.nav_search:
                    selectFragment = new SearchFragment();
                    break;
                case R.id.nav_add:
                    selectFragment = null;
                    startActivity(new Intent(MainActivity.this,PostActivity.class));
                    break;
                case R.id.nav_heart:
                    selectFragment = new NotificationFragment();
                    break;
                case R.id.nav_profile:
                    SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                    editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    editor.apply();
                    selectFragment = new ProfileFragment();
                    break;
            }
            if(selectFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectFragment).commit();
            }
            return;
        }

    };
}