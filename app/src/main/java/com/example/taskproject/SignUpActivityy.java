package com.example.taskproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.taskproject.databinding.ActivitySignUpActivityyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivityy extends AppCompatActivity implements View.OnClickListener {
    ActivitySignUpActivityyBinding binding;
    private ProgressDialog progressDialog;
    public SignUpActivityy(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up_activityy);
        initListener();
    }
    private void isUserAlreadySignIn() {
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(SignUpActivityy.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void initListener() {
        binding.signUpButton.setOnClickListener(this);
        binding.signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButton:
                if(isDataValid()) {
                    signUpWithEmailAndPassword();
                }
                break;
            case R.id.signInButton:
                Intent intent = new Intent(SignUpActivityy.this, SignInActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean isDataValid() {
        if(binding.nameTxt.getText().toString().isEmpty()){
            binding.nameTxt.requestFocus();
            Toast.makeText(this, "Please provide name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(binding.passwordTxt.getText().toString().isEmpty()){
            binding.passwordTxt.requestFocus();
            Toast.makeText(this, "Please provide Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(binding.emailTxt.getText().toString().isEmpty()){
            binding.emailTxt.requestFocus();
            Toast.makeText(this, "Please provide email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void signUpWithEmailAndPassword() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("We are creating your account");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.emailTxt.getText().toString().trim(),
                binding.passwordTxt.getText().toString().trim()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("name",binding.nameTxt.getText().toString().trim());
                    map.put("email",binding.emailTxt.getText().toString().trim());
                    map.put("password",binding.passwordTxt.getText().toString().trim());
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //sent verificaion email
                                    sendVerificationEmail();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivityy.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    Toast.makeText(SignUpActivityy.this, "User SignUp Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignUpActivityy.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendVerificationEmail() {
        FirebaseAuth.getInstance().getCurrentUser()
                .sendEmailVerification()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivityy.this, "Email Verification link sent to your email. please verifiy and then signin", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivityy.this, "Email not sent", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}