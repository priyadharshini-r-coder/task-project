package com.example.taskproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.taskproject.databinding.ActivitySignInActivityyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Objects;

public class SignInActivityy extends AppCompatActivity implements View.OnClickListener {
    ActivitySignInActivityyBinding binding;
    private FirebaseRemoteConfig config;
    private ProgressDialog progressDialog;
    public SignInActivityy()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_in_activityy);
        initListener();
    config = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(60)
                .build();
    config.setConfigSettingsAsync(firebaseRemoteConfigSettings);
       config.setDefaultsAsync(R.xml.login_default_values);
        getValueFromFireBaseCOnfig();
        getDynamicLinkFromFireBase();
        setContentView(binding.getRoot());
    }

    private void initListener() {
        binding.signInButton.setOnClickListener(this);
        binding.signUpButton.setOnClickListener(this);
    }

    private void getDynamicLinkFromFireBase() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        Log.i("SignInActivity","We have a Dynamic Link");
                        Uri deepLink = null;

                        if(pendingDynamicLinkData!=null){
                            deepLink = pendingDynamicLinkData.getLink();
                        }

                        if(deepLink!=null){
                            Log.i("SignInActivity", "Here the Dynamic link \n" + deepLink.toString());

                            String email = deepLink.getQueryParameter("email");
                            String password = deepLink.getQueryParameter("password");

                            binding.emailTxt.setText(email);
                            binding.passwordTxt.setText(password);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivityy.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getValueFromFireBaseCOnfig() {
       config.fetchAndActivate()
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if(task.isSuccessful())
                        {
                            Log.i("SignInActivity", String.valueOf(task.getResult()));
                            String text = config.getString("login_submit_text");
                            binding.signInButton.setText(text);
                        }
                        else{
                            Toast.makeText(SignInActivityy.this, "Fetch Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInButton:
                if(isDataValid()) {
                    doSignInWithEmailAndPassword();
                }
                break;
            case R.id.signUpButton:
                Intent intent = new Intent(SignInActivityy.this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean isDataValid() {
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

    private void doSignInWithEmailAndPassword() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Loging to your account");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.emailTxt.getText().toString().trim(),
                binding.passwordTxt.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    if(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()) {
                        Toast.makeText(SignInActivityy.this, "User SignIn Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInActivityy.this, MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SignInActivityy.this, "PLease verify your email first", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(SignInActivityy.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}