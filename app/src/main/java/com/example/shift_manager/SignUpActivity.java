package com.example.shift_manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shift_manager.Logic.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText fullname;
    private EditText userName;
    private EditText email;
    private EditText manager;
    private EditText salary;
    private EditText passward;
    private Button send;

    private String newfullName; //שם מלא - חדש
    private String newPassward; // סיסמא

    private String newUserName;
    private String newEmail;
    private int newisManager; //1=מנהל
    private int newsalaryPerHour; //משכורת - חדש


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private User updatedUser;
    private static final String TAG = "Updated";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(view -> saveDetailes(view));
        }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }


    public void signup(){
        if (!validateForm()) {
            return;
        }
        newfullName = fullname.getText().toString();
        newisManager = Integer.parseInt(manager.getText().toString());
        newsalaryPerHour = Integer.parseInt(salary.getText().toString());
        newUserName = userName.getText().toString();
        newEmail = email.getText().toString();
        newPassward = passward.getText().toString();

        User newUser = new User(newUserName, newfullName, newEmail, newisManager, newsalaryPerHour);

        mAuth.createUserWithEmailAndPassword(newEmail, newPassward)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            mDatabase.child("users").child(task.getResult().getUser().getUid()).setValue(newUser);
                            //onAuthSuccess(task.getResult().getUser(), newUser);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }





    private void saveDetailes(View view) {
        signup();

        Toast.makeText(SignUpActivity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


        }

    void findViews() {
        fullname = findViewById(R.id.editTextName);
        manager = findViewById(R.id.editManager);
        salary = findViewById(R.id.editSalary);
        send = findViewById(R.id.SIGNUP_BTN_send);
        userName = findViewById(R.id.editTextUserName);
        email = findViewById(R.id.editTextEmail);
        passward=findViewById(R.id.editPassward);



    }

    private boolean validateForm() {
        boolean result = true;
        //fullname -check
        if (TextUtils.isEmpty(fullname.getText().toString())) {
            fullname.setError("Required");
            result = false;
        } else {
            fullname.setError(null);

        }
        //isManager - check
        if (TextUtils.isEmpty(manager.getText().toString())) {
            manager.setError("Required");
            result = false;
        } else {
            manager.setError(null);

        }

        //salary - check
        if (TextUtils.isEmpty(salary.getText().toString())) {
            salary.setError("Required");
            result = false;
        } else {
            salary.setError(null);

        }

        //username - check
        if (TextUtils.isEmpty(userName.getText().toString())) {
            userName.setError("Required");
            result = false;
        }
        else {
            userName.setError(null);

        }

        //email - check
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Required");
            result = false;
        }
        else {
            email.setError(null);

        }

        //passward - check
        if (TextUtils.isEmpty(passward.getText().toString())) {
            passward.setError("Required");
            result = false;
        } else {
            passward.setError(null);
        }

        return result;
    }


}




