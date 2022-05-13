package com.example.pocdoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText emailid, phone, name, password;
    Button Register_btn;
    FirebaseAuth mFirebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.register_email_text);
        name = findViewById(R.id.register_name_text);
        phone = findViewById(R.id.register_phonenum);
        password = findViewById(R.id.register_password_text);

        Register_btn = findViewById(R.id.Reg_btn);

        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailid.getText().toString();
                String pass = password.getText().toString();
                String Name = name.getText().toString();
                String num = phone.getText().toString();

                if(email.isEmpty()){
                    emailid.setError("Please enter email address");
                    emailid.requestFocus();
                }
                else if (pass.isEmpty()){
                    password.setError("Please enter a password");
                    password.requestFocus();
                }
                else if (Name.isEmpty()){
                    name.setError("Please enter your name");
                    name.requestFocus();
                }
                else if (num.isEmpty()){
                    phone.setError("Enter your phone number");
                    phone.requestFocus();
                }
                else if (email.isEmpty() && pass.isEmpty() && Name.isEmpty() && num.isEmpty()){

                    Toast.makeText(RegisterActivity.this, "Please enter your details", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pass.isEmpty() && Name.isEmpty() && num.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                            else startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                        }
                    });
                }
                else Toast.makeText(RegisterActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}