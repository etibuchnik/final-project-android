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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignIn";
   private FirebaseUser Fuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        findViews();

        // Click listeners
        buttonSignIn.setOnClickListener(view -> onClick(view));
        buttonSignUp.setOnClickListener(view -> onClick(view));
    }







    private void findViews() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonLogin);
        buttonSignUp=findViewById(R.id.buttonRegister);

    }
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonLogin) {
            signIn();
        } else if (i == R.id.buttonRegister) {
            signUp();
        }
    }

    public void onStart() {
        super.onStart();

        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }

    private void signIn() {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            //onAuthSuccess(task.getResult().getUser());

                            //String currentUserID=task.getResult().getUser().getUid();

                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();



                        } else {
                            Toast.makeText( MainActivity.this , "Sign In Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signUp() {
        Log.d(TAG, "signUp");

        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(editTextUsername.getText().toString())) {
            editTextUsername.setError("Required");
            result = false;
        }
        else {
            editTextUsername.setError(null);
        }

        if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
            editTextPassword.setError("Required");
            result = false;
        } else {
            editTextPassword.setError(null);
        }

        return result;
    }

    private void writeNewUser(String userId, String username, String email) {
        //User user = new User(username, email);
        //this.user = user;
        //this.employee = userToEmployee(user);

        //mDatabase.child("users").child(userId).setValue(user);




    }



}

//"data.child('manager').val() == 1"
