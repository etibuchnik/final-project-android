package com.example.shift_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.nullness.qual.NonNull;

public class SalaryActivity extends AppCompatActivity {
    private TextView hours;
    private TextView salary;
    private ImageButton backward;
    public int numOfChosenShifts;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private int salaryPerHour;
    private int numShifts = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        findViews();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("numshifts").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("the salary is:", String.valueOf(task.getResult().getValue()));
                    int numshifts = Integer.parseInt(String.valueOf(task.getResult().getValue()));
                    updateNumShifts(numshifts);
                }
            }
        });


        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("salary").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("the salary is:", String.valueOf(task.getResult().getValue()));
                    salaryPerHour = Integer.parseInt(String.valueOf(task.getResult().getValue()));
                    calculate(salaryPerHour);
                }
            }
        });





        backward.setOnClickListener(view -> backward(view));

    }

    private void updateNumShifts(int numshifts) {
        hours.setText(numshifts*8 + " ");
        numshifts = numshifts;


    }

    private void calculate(int salaryPerHour) {
        salary.setText((salaryPerHour*numShifts) + " ");
    }

    private void backward(Object view) {
        Intent intent = new Intent(SalaryActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    private void findViews() {
        hours = findViewById(R.id.SALARY_TXT_amountUser);
        salary = findViewById(R.id.SALARY_TXT_salaryUser);
        backward = findViewById(R.id.SALARY_BTN_back);
    }
}