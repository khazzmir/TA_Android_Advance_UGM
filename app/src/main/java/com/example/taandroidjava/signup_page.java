package com.example.taandroidjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup_page extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://ta-android-java-default-rtdb.asia-southeast1.firebasedatabase.app/").getReferenceFromUrl("https://ta-android-java-default-rtdb.asia-southeast1.firebasedatabase.app/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup_page);
    }

    public void signup(View view) {

        final TextInputEditText fullname = findViewById(R.id.namalengkap);
        final TextInputEditText username = findViewById(R.id.panggilan);
        final TextInputEditText email = findViewById(R.id.emailpengguna);
        final TextInputEditText phonenumber = findViewById(R.id.nomorhp);
        final TextInputEditText password = findViewById(R.id.pw);

        final String fullnametxt = fullname.getText().toString();
        final String usernametxt = username.getText().toString();
        final String emailtxt = email.getText().toString();
        final String phonenumbertxt = phonenumber.getText().toString();
        final String passwordtxt = password.getText().toString();

        if(fullnametxt.isEmpty() || usernametxt.isEmpty() || emailtxt.isEmpty() || phonenumbertxt.isEmpty() || passwordtxt.isEmpty()){
            Toast.makeText(this, "Tolong isi semua", Toast.LENGTH_SHORT).show();
        }else {
            databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(fullnametxt)){
                        Toast.makeText(signup_page.this, "Nama ini telah digunakan", Toast.LENGTH_SHORT).show();
                    }else {

                        databaseReference.child("Users").child(fullnametxt).child("email").setValue(emailtxt);
                        databaseReference.child("Users").child(fullnametxt).child("username").setValue(usernametxt);
                        databaseReference.child("Users").child(fullnametxt).child("phonenumber").setValue(phonenumbertxt);
                        databaseReference.child("Users").child(fullnametxt).child("password").setValue(passwordtxt);

                        Toast.makeText(signup_page.this, "User berhasil terdaftar", Toast.LENGTH_SHORT).show();
                        login(view);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    public void login(View view) {
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
        finish();
    }
}