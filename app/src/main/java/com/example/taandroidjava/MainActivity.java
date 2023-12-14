package com.example.taandroidjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.taandroidjava.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    FloatingActionButton fab;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replacefragment(new Home_fragment());

        fab = findViewById(R.id.fab_button);

        binding.bottomNavView.setBackground(null);

        bottomNavigationView = findViewById(R.id.bottomNavView);

        binding.bottomNavView.setSelectedItemId(R.id.fab);
        updateFabIcon();

        binding.fabButton.setOnClickListener(view -> {
            int selectedItem = bottomNavigationView.getSelectedItemId();
            if (selectedItem == R.id.fab) {
                startActivity(new Intent(MainActivity.this, buying_page.class));
            } else {
                bottomNavigationView.setSelectedItemId(R.id.fab);
            }
        });

        binding.bottomNavView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.menu_favorite:
                    replacefragment(new favorite_page());
                    fabIconHome();
                    break;

                case R.id.fab:
                    replacefragment(new Home_fragment());
                    updateFabIcon();
                    break;

                case R.id.menu_history:
                    replacefragment(new history_fragment());
                    fabIconHome();
                    break;
            }
            return true;
        });

    }

    private void fabIconHome() {
        fab.setImageResource(R.drawable.baseline_home_24);
    }

    private void updateFabIcon() {
        fab.setImageResource(R.drawable.baseline_shopping_cart_24);
    }

    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}