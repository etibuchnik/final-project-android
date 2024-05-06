package com.example.shift_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

public class MyShifts extends AppCompatActivity {
    private ImageButton backward;
    private TextView shift1;
    private TextView shift2;
    private TextView shift3;
    private TextView shift4;
    private TextView shift5;
    private TextView shift6;
    private TextView shift7;
    private TextView shift8;
    private TextView shift9;
    private TextView shift10;
    private TextView shift11;
    private TextView shift12;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private List shiftsIWant;
    private ArrayList objArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shifts);
        findViews();
        turnOffAll();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("shiftsIWant").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("the shifts are:", String.valueOf(task.getResult().getValue()));
                    objArray = (ArrayList) task.getResult().getValue();
                    System.out.println(objArray.get(0).getClass());
                    refreshUI(objArray);

                }
            }
        });





        backward.setOnClickListener(view -> backward(view));

    }

    private void turnOffAll() {
        shift1.setVisibility(View.INVISIBLE);
        shift2.setVisibility(View.INVISIBLE);
        shift3.setVisibility(View.INVISIBLE);
        shift4.setVisibility(View.INVISIBLE);
        shift5.setVisibility(View.INVISIBLE);
        shift6.setVisibility(View.INVISIBLE);
        shift7.setVisibility(View.INVISIBLE);
        shift8.setVisibility(View.INVISIBLE);
        shift9.setVisibility(View.INVISIBLE);
        shift10.setVisibility(View.INVISIBLE);
        shift11.setVisibility(View.INVISIBLE);
        shift12.setVisibility(View.INVISIBLE);
    }

    private void refreshUI(ArrayList objArray) {
        if (Integer.parseInt(objArray.get(0).toString()) == 1) {
            shift1.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(1).toString()) == 1) {
            shift2.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(2).toString()) == 1) {
            shift3.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(3).toString()) == 1) {
            shift4.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(4).toString()) == 1) {
            shift5.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(5).toString()) == 1) {
            shift6.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(6).toString()) == 1) {
            shift7.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(7).toString()) == 1) {
            shift8.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(8).toString()) == 1) {
            shift9.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(9).toString()) == 1) {
            shift10.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(10).toString()) == 1) {
            shift11.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(objArray.get(11).toString()) == 1) {
            shift12.setVisibility(View.VISIBLE);
        }


    }

    private void backward(Object view) {
        Intent intent = new Intent(MyShifts.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    private void findViews() {
        backward=findViewById(R.id.MYSHIFTS_BTN_back);
        shift1=findViewById(R.id.MYSHIFTS_TXT_1);
        shift2=findViewById(R.id.MYSHIFTS_TXT_2);
        shift3=findViewById(R.id.MYSHIFTS_TXT_3);
        shift4=findViewById(R.id.MYSHIFTS_TXT_4);
        shift5=findViewById(R.id.MYSHIFTS_TXT_5);
        shift6=findViewById(R.id.MYSHIFTS_TXT_6);
        shift7=findViewById(R.id.MYSHIFTS_TXT_7);
        shift8=findViewById(R.id.MYSHIFTS_TXT_8);
        shift9=findViewById(R.id.MYSHIFTS_TXT_9);
        shift10=findViewById(R.id.MYSHIFTS_TXT_10);
        shift11=findViewById(R.id.MYSHIFTS_TXT_11);
        shift12=findViewById(R.id.MYSHIFTS_TXT_12);

    }
}