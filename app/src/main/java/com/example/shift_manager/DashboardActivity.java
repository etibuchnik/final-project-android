package com.example.shift_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class DashboardActivity extends AppCompatActivity {
    private TextView userName;
    private Button sendShifts;
    private Button logout;
    private Button myShifts;
    private Button salary;
    private Button userDetailes;
    private Button manageShifts;
    private FirebaseDatabase database;
    private String name;
    private FirebaseAuth mAuth;
    private User connectedUser;
    private FirebaseUser Fuser;
    public int numOfChosenShifts;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        findViews();

       // Bundle extras = getIntent().getExtras();
        // / // if (extras != null) {
       //     numOfChosenShifts = Integer.parseInt(extras.getString("key"));
       //     //The key argument here must match that used in the other activity
       // }


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");
        Fuser = mAuth.getCurrentUser();

        ref.child(Fuser.getUid()).child("fullname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    String user_name = usernameFromEmail(Fuser.getEmail());
                    userName.setText(user_name);
                }
                else {
                    Log.d("the fullname is:", String.valueOf(task.getResult().getValue()));
                    userName.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });




        String uid = Fuser.getUid();

        //check
        System.out.println("CURRENT CONNECTED:");
        System.out.println(uid);




        sendShifts.setOnClickListener(view -> onClick(view));
        myShifts.setOnClickListener(view -> onClick(view));
        salary.setOnClickListener(view -> onClick(view));
        userDetailes.setOnClickListener(view -> onClick(view));
        manageShifts.setOnClickListener(view -> onClick(view));
        logout.setOnClickListener(view -> onClick(view));

    }

    private void refreshUI() {
        userName.setText(connectedUser.fullname);
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void onClick(View view) {
        int i = view.getId();
        if (i==R.id.DASH_BTN_logout){
            logout();
        }
        if (i == R.id.dashboard_BTN_sendShifts){
            pushSendShifts();
        }
        if (i == R.id.dashboard_BTN_myShifts){

            pushMyShifts();
        }
        if (i == R.id.dashboard_BTN_salary){

            pushSalary();
        }
        if (i == R.id.dashboard_BTN_userDetailes){
            pushUserDetailes();

        }
        if (i == R.id.dashboard_BTN_manageShifts){
            pushManageShifts();

        }
    }

    private void logout() {
        mAuth.getInstance().signOut();
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();



    }

    private void pushManageShifts() {
        Intent intent = new Intent(DashboardActivity.this, ManageShifts.class);
        startActivity(intent);
        finish();
    }

    private void pushUserDetailes() {
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void pushSalary() {
        Intent intent = new Intent(DashboardActivity.this, SalaryActivity.class);
        //intent.putExtra("hours", numOfChosenShifts);
        //need to send: salary per hour, numHours
        startActivity(intent);
        finish();
    }

    private void pushMyShifts() {
        Intent intent = new Intent(DashboardActivity.this, MyShifts.class);
        //need to send: user
        startActivity(intent);
        finish();
    }

    private void pushSendShifts() {
        Intent intent = new Intent(DashboardActivity.this, ChooseShift.class);
        //need to send: user
        startActivity(intent);
        finish();

    }

    private void findViews() {
        userName=findViewById(R.id.dashboard_TXT_userName);
        sendShifts=findViewById(R.id.dashboard_BTN_sendShifts);
        myShifts=findViewById(R.id.dashboard_BTN_myShifts);
        salary=findViewById(R.id.dashboard_BTN_salary);
        userDetailes=findViewById(R.id.dashboard_BTN_userDetailes);
        manageShifts=findViewById(R.id.dashboard_BTN_manageShifts);
        logout = findViewById(R.id.DASH_BTN_logout);

    }
}

