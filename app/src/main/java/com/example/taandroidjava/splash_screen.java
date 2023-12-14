package com.example.taandroidjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class splash_screen extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://ta-android-java-default-rtdb.asia-southeast1.firebasedatabase.app/").getReferenceFromUrl("https://ta-android-java-default-rtdb.asia-southeast1.firebasedatabase.app/");


    private static int SPLASH_SCREEN = 3000;

    Animation topanim, botanim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.imageView2);
        image.setAnimation(topanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(splash_screen.this, signup_page.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN);
    }

    public void done() {

        final TextInputEditText fullname = findViewById(R.id.fullname);

        final String fullnametxt = fullname.getText().toString().trim();

        DatabaseReference reference = databaseReference;
        Query done = reference.orderByChild("fullname").equalTo(fullnametxt);

        done.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String done = snapshot.child(fullnametxt).child("login").getValue(String.class);

                    if(!Objects.equals(done, "yes")){
                        Intent intent = new Intent(splash_screen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}