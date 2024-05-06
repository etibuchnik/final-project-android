package com.example.shift_manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    private EditText fullname;
    private EditText userName;
    private EditText email;
    private EditText manager;
    private EditText salary;
    private Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        findViews();

        update.setOnClickListener(view -> updateDetailes(view));

    }

    private void updateDetailes(View view) {

        update();

        Toast.makeText(UpdateActivity.this, "הפרטים שלך עודכנו בהצלחה", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(UpdateActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void update() {

        if (!validateForm()) {
            return;
        }

        String newfullName = fullname.getText().toString();
        int newisManager = Integer.parseInt(manager.getText().toString());
        int newsalaryPerHour = Integer.parseInt(salary.getText().toString());
        String newUserName = userName.getText().toString();
        String newEmail = email.getText().toString();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("users");
        FirebaseUser Fuser = mAuth.getCurrentUser();
        DatabaseReference userRef = usersRef.child(Fuser.getUid());

        Map<String, Object> userUpdate = new HashMap<>();

        userUpdate.put("email", newEmail);
        userUpdate.put("fullname", newfullName);
        userUpdate.put("username", newUserName);
        userUpdate.put("manager", newisManager);
        userUpdate.put("salary", newsalaryPerHour);


        userRef.updateChildren(userUpdate);


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
        return result;


    }

    private void findViews() {
        fullname = findViewById(R.id.editTextName);
        manager = findViewById(R.id.editManager);
        salary = findViewById(R.id.editSalary);
        update = findViewById(R.id.SIGNUP_BTN_update);
        userName = findViewById(R.id.editTextUserName);
        email = findViewById(R.id.editTextEmail);

    }
}