package com.example.boozlet;

import static com.example.boozlet.Constants.movingKeys.USERID;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null)
            login();
        else
            moveToMainActivity(user);

    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            moveToMainActivity(user);

        } else {

            Toast.makeText(this,"Unsuccessful Login! , try again",Toast.LENGTH_SHORT).show();

        }
    }

    private void moveToMainActivity(FirebaseUser user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USERID ,user.getUid());
        startActivity(intent);
        finish();


    }

    private void login() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(com.google.android.material.R.style.Base_Theme_AppCompat)
                .setLogo(R.drawable.ic_signin_logo)
                .setIsSmartLockEnabled(false) // remove when not testings //TODO
                .build();
        signInLauncher.launch(signInIntent);
    }

}