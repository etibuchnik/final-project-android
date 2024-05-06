package com.example.shift_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shift_manager.Logic.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ProfileActivity extends AppCompatActivity {
    private ImageButton backward;
    private TextView fullname;
    private TextView username;
    private TextView role;
    private TextView salaryPerHour;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public int isManager;
    public Button update;
    private  User currentUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        findViews();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //upload the data
        //upload the name
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("fullname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("the name is:", String.valueOf(task.getResult().getValue()));
                    fullname.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });

        //upload the username
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("the name is:", String.valueOf(task.getResult().getValue()));
                    username.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });

        //upload the salary
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("salary").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("the name is:", String.valueOf(task.getResult().getValue()));
                    salaryPerHour.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });

        //upload is manager
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("manager").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("the name is:", String.valueOf(task.getResult().getValue()));
                    role.setText(isManager( Integer.parseInt(String.valueOf(task.getResult().getValue()))));
                }
            }
        });



        update.setOnClickListener(view -> updateDetailes(view));
        backward.setOnClickListener(view -> backward(view));

    }

    private void updateUI(User userConnected) {
        fullname.setText(userConnected.fullname);
        username.setText(userConnected.username);
        role.setText(isManager(userConnected.manager));
        salaryPerHour.setText(userConnected.salary + " ");

    }

    private String isManager(int manager){
        if (manager ==1){
            return "מנהל";
        }
        else
            return "עובד רגיל";
    }
    private void updateDetailes(View view) {
        Intent intent = new Intent(ProfileActivity.this, UpdateActivity.class);
        startActivity(intent);
        finish();
    }

    private void backward(Object view) {
        Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();


    }

    private void findViews() {
        fullname = findViewById(R.id.PROFILE_TXT_fullName);
        username = findViewById(R.id.PROFILE_TXT_userName);
        role = findViewById(R.id.PROFILE_TXT_role);
        salaryPerHour = findViewById(R.id.PROFILE_TXT_salaryPerHour);
        backward = findViewById(R.id.PROFILE_BTN_back);
        update = findViewById(R.id.PROFILE_BTN_update);


    }
    public void extractDetailes(FirebaseUser currentUser){

        DatabaseReference userRef = mDatabase.child("users").child(currentUser.getUid());
        DataSnapshot dataSnapshot;
       // dataSnapshot=
        //dataSnapshot.child("manager").getValue(android.R.integer.class);



    }
}






