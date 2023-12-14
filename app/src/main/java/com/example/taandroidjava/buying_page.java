package com.example.taandroidjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taandroidjava.databinding.ActivityMainBinding;

public class buying_page extends AppCompatActivity implements cone.OnPositionChangeListener, ice_cream.OnPositionChangeListener {

    int itemselectedCount = 0;

    chooseAdapter adapter;

    String[] coneName = {"Cone", "Cup"};

    String[] iceName = {"Cookies n' Cream",
            "Chocolate",
            "Oreo",
            "Blue Raspberry",
            "Bubble Gum",
            "Durian",
            "Mango",
            "Mint",
            "Pistacio Almond",
            "Strawberry",
            "Taro",
            "Vanilla"
    };

    ActivityMainBinding binding;

    ImageView back, next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_buying_page);

        final ImageView back = findViewById(R.id.btback);
        final ImageView next = findViewById(R.id.btnext);
        final ImageView onback = findViewById(R.id.onback);

        findViewById(R.id.btback).setVisibility(View.GONE);
        findViewById(R.id.btnext).setVisibility(View.VISIBLE);

        showMainFragment();

        onback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBackFragment();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextFragment();
            }
        });

    }

    private void step1(int position) {
        itemselectedCount = position;
        TextView nameofThing = findViewById(R.id.nameofThing);
        final cone coneFragment = new cone();

        if (position >= 0 && position < coneName.length) {
            nameofThing.setText(coneName[position]);
            // Enable or disable based on the current fragment
            nameofThing.setEnabled(coneFragment.isVisible());
        } else {
            // Handle the case where the position is out of bounds
            Log.e("BuyingPage", "Invalid position: " + position);
        }
    }

    private void step2(int position) {
        itemselectedCount = position;
        TextView nameofThing = findViewById(R.id.nameofThing);
        final ice_cream iceCream = new ice_cream();

        if (position >= 0 && position < iceName.length) {
            nameofThing.setText(iceName[position]);
            // Enable or disable based on the current fragment
            nameofThing.setEnabled(iceCream.isVisible());
        } else {
            // Handle the case where the position is out of bounds
            Log.e("BuyingPage", "Invalid position: " + position);
        }
    }

    private cone coneFragment;
    private ice_cream iceCreamFragment;

    private void showMainFragment() {
        TextView title = findViewById(R.id.textstep);
        TextView choose = findViewById(R.id.textchoose);

        // Initialize coneFragment if null
        if (coneFragment == null) {
            coneFragment = new cone();
            coneFragment.setOnPositionChangeListener(this); // Set the listener here
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.itemplace, coneFragment);
        fragmentTransaction.commit();

        title.setText("Step 1");
        choose.setText("Choose Cone");

        // Update nameofThing based on the current fragment
        TextView nameofThing = findViewById(R.id.nameofThing);

        // Check if adapter is not null before getting the index
        if (coneFragment.adapter != null) {
            nameofThing.setText(coneName[coneFragment.adapter.getCurrentIndex()]);
            nameofThing.setEnabled(true);
        } else {
            Log.e("BuyingPage", "Adapter is null");
        }
    }

    private void showSecondFragment() {
        TextView title = findViewById(R.id.textstep);
        TextView choose = findViewById(R.id.textchoose);
        TextView nameofThing = findViewById(R.id.nameofThing);

        // Only create the iceCreamFragment if it's null
        if (iceCreamFragment == null) {
            iceCreamFragment = new ice_cream();
            iceCreamFragment.setOnPositionChangeListeneric(this);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.itemplace, iceCreamFragment);
        fragmentTransaction.commit();

        // Ensure that the fragment transaction is complete before updating UI
        fragmentManager.executePendingTransactions();

        title.setText("Step 2");
        choose.setText("Choose Flavor");

        // Update nameofThing based on the current fragment
        if (iceCreamFragment != null) {
            nameofThing.setText(iceName[iceCreamFragment.getSelectedIceCreamIndex()]);
            nameofThing.setEnabled(iceCreamFragment.isVisible());
        }
    }

    private void showBackFragment() {

        TextView title = findViewById(R.id.textstep);
        TextView choose = findViewById(R.id.textchoose);
        TextView nameofThing = findViewById(R.id.nameofThing);
        if (itemselectedCount > 0) {
            itemselectedCount--;

            Fragment fragment;

            switch (itemselectedCount) {
                case 0:
                    fragment = new cone();
                    title.setText("Step 1");
                    choose.setText("Choose Cone");
                    showMainFragment();
                    break;

                case 1:
                    fragment = new ice_cream();
                    title.setText("Step 2");
                    choose.setText("Choose Flavor");
                    showSecondFragment();
                    break;

                case 2:
                    fragment = new toping();
                    title.setText("Step 3");
                    choose.setText("Choose Toping");
                    break;

                default:
                    return;
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.itemplace, fragment);
            fragmentTransaction.commit();

            if (itemselectedCount == 2) {
                findViewById(R.id.btback).setVisibility(View.VISIBLE);
                findViewById(R.id.btnext).setVisibility(View.GONE);
            } else if (itemselectedCount == 0) {
                findViewById(R.id.btback).setVisibility(View.GONE);
                findViewById(R.id.btnext).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.btback).setVisibility(View.VISIBLE);
                findViewById(R.id.btnext).setVisibility(View.VISIBLE);
            }

        }
    }

    private void showNextFragment() {

        TextView title = findViewById(R.id.textstep);
        TextView choose = findViewById(R.id.textchoose);
        TextView nameofThing = findViewById(R.id.nameofThing);

        if (itemselectedCount < 2) {
            itemselectedCount++;

            Fragment fragment;

            switch (itemselectedCount) {
                case 0:
                    if (coneFragment == null) {
                        coneFragment = new cone();
                        coneFragment.setOnPositionChangeListener(this);
                    }
                    fragment = new cone();
                    title.setText("Step 1");
                    choose.setText("Choose Cone");
                    showMainFragment();
                    break;

                case 1:
                    if (iceCreamFragment == null) {
                        iceCreamFragment = new ice_cream();
                        iceCreamFragment.setOnPositionChangeListeneric(this);
                    }
                    fragment = new ice_cream();
                    title.setText("Step 2");
                    choose.setText("Choose Flavor");
                    showSecondFragment();
                    break;

                case 2:
                    fragment = new toping();
                    title.setText("Step 3");
                    choose.setText("Choose Toping");
                    break;

                default:
                    return;
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.itemplace, fragment);
            fragmentTransaction.commit();

            if (itemselectedCount == 2) {
                findViewById(R.id.btback).setVisibility(View.VISIBLE);
                findViewById(R.id.btnext).setVisibility(View.GONE);
            } else if (itemselectedCount == 0) {
                findViewById(R.id.btback).setVisibility(View.GONE);
                findViewById(R.id.btnext).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.btback).setVisibility(View.VISIBLE);
                findViewById(R.id.btnext).setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onPositionChange(int position) {
        step1(position);
        Log.d("BuyingPage", "onPositionChange: " + position);
    }

    public void onPositionChangeic(int position) {
        step2(position);
        Log.d("BuyingPage", "onPositionChangeic: " + position);
    }
}