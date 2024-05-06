package com.example.shift_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChooseShift extends AppCompatActivity {
    private ImageButton backward;
    private TextView numChosen;

    private Button shift1;
    private Button shift2;
    private Button shift3;
    private Button shift4;
    private Button shift5;
    private Button shift6;
    private Button shift7;
    private Button shift8;
    private Button shift9;
    private Button shift10;
    private Button shift11;
    private Button shift12;
    private Button sendShifts;
    private appManager appManager;
    public static final String TAG = "PUSHED BUTTON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_shift);
        findViews();

        appManager = new appManager();

        numChosen.setText(appManager.getNumOfCChosenShifts()+ " ");
        appManager.setShiftsIWant(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        System.out.println(appManager.getShiftsIWant());


        backward.setOnClickListener(view -> backward(view));

        shift1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick1(v);

            }
        });
        shift2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick2(v);

            }
        });
        shift3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick3(v);

            }
        });
        shift4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick4(v);

            }
        });
        shift5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick5(v);

            }
        });
        shift6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick6(v);

            }
        });
        shift7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick7(v);

            }
        });
        shift8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick8(v);

            }
        });
        shift9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick9(v);

            }
        });
        shift10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick10(v);

            }
        });
        shift11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick11(v);

            }
        });
        shift12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d(TAG, "clicked");
                onClick12(v);

            }
        });
        sendShifts.setOnClickListener(view -> send(view, appManager.getShiftsIWant()));

        
    }

    private void onClick1(View view) {
        appManager.userChooseShift(1);
        refreshUI();

    }
    private void onClick2(View view) {
        appManager.userChooseShift(2);
        refreshUI();
    }
    private void onClick3(View view) {
        appManager.userChooseShift(3);
        refreshUI();
    }

    private void onClick4(View view) {
        appManager.userChooseShift(4);
        refreshUI();
    }
    private void onClick5(View view) {
        appManager.userChooseShift(5);
        refreshUI();
    }
    private void onClick6(View view) {
        appManager.userChooseShift(6);
        refreshUI();
    }
    private void onClick7(View view) {
        appManager.userChooseShift(7);
        refreshUI();
    }
    private void onClick8(View view) {
        appManager.userChooseShift(8);
        refreshUI();
    }

    private void onClick9(View view) {
        appManager.userChooseShift(9);
        refreshUI();
    }
    private void onClick10(View view) {
        appManager.userChooseShift(10);
        refreshUI();
    }
    private void onClick11(View view) {
        appManager.userChooseShift(11);
        refreshUI();
    }
    private void onClick12(View view) {
        appManager.userChooseShift(12);
        refreshUI();
    }
    private void send(View view, int[] shiftsIWant) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("users");
        FirebaseUser Fuser = mAuth.getCurrentUser();
        DatabaseReference shiftsRef = usersRef.child(Fuser.getUid()).child("shiftsIWant");

        Map<String, Object> userUpdate = new HashMap<>();

        userUpdate.put("0", shiftsIWant[0]);
        userUpdate.put("1", shiftsIWant[1]);
        userUpdate.put("2", shiftsIWant[2]);
        userUpdate.put("3", shiftsIWant[3]);
        userUpdate.put("4", shiftsIWant[4]);
        userUpdate.put("5", shiftsIWant[5]);
        userUpdate.put("6", shiftsIWant[6]);
        userUpdate.put("7", shiftsIWant[7]);
        userUpdate.put("8", shiftsIWant[8]);
        userUpdate.put("9", shiftsIWant[9]);
        userUpdate.put("10", shiftsIWant[10]);
        userUpdate.put("11", shiftsIWant[11]);


        shiftsRef.updateChildren(userUpdate);
        System.out.println(appManager.getNumOfCChosenShifts());


        //saving num of shifts
        DatabaseReference numShiftsRef =  database.getReference().child("users").child(Fuser.getUid());
        Map<String, Object> numShiftsUpdate = new HashMap<>();
        numShiftsUpdate.put("numshifts", appManager.getNumOfCChosenShifts() );
        numShiftsRef.updateChildren(numShiftsUpdate);

        Intent intent = new Intent(ChooseShift.this, DashboardActivity.class);
        //intent.putExtra("key",appManager.getNumOfCChosenShifts());
        startActivity(intent);
        finish();

    }

    private void onClick(View view) {
        int i = view.getId();
        if (i == R.id.CHOOSE_TXT_1){
            appManager.userChooseShift(1);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_2){
            appManager.userChooseShift(2);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_3){
            appManager.userChooseShift(3);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_4){
            appManager.userChooseShift(4);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_5){
            appManager.userChooseShift(5);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_6){
            appManager.userChooseShift(6);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_7){
            appManager.userChooseShift(7);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_8){
            appManager.userChooseShift(8);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_9){
            appManager.userChooseShift(9);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_10){
            appManager.userChooseShift(10);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_11){
            appManager.userChooseShift(11);
            refreshUI();
        }
        if (i == R.id.CHOOSE_TXT_12){
            appManager.userChooseShift(12);
            refreshUI();
        }


    }

    private void refreshUI() {
        numChosen.setText(appManager.getNumOfCChosenShifts()+ " ");
    }

    private void backward(Object view) {
        Intent intent = new Intent(ChooseShift.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        backward=findViewById(R.id.CHOOSE_BTN_back);
        numChosen=findViewById(R.id.CHOOSE_TXT_numShifts);
        shift1=findViewById(R.id.CHOOSE_TXT_1);
        shift2=findViewById(R.id.CHOOSE_TXT_2);
        shift3=findViewById(R.id.CHOOSE_TXT_3);
        shift4=findViewById(R.id.CHOOSE_TXT_4);
        shift5=findViewById(R.id.CHOOSE_TXT_5);
        shift6=findViewById(R.id.CHOOSE_TXT_6);
        shift7=findViewById(R.id.CHOOSE_TXT_7);
        shift8=findViewById(R.id.CHOOSE_TXT_8);
        shift9=findViewById(R.id.CHOOSE_TXT_9);
        shift10=findViewById(R.id.CHOOSE_TXT_10);
        shift11=findViewById(R.id.CHOOSE_TXT_11);
        shift12=findViewById(R.id.CHOOSE_TXT_12);
        sendShifts=findViewById(R.id.CHOOSE_BTN_sendShifts);
    }
}