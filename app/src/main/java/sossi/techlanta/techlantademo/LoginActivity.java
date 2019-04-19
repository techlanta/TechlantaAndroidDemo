package sossi.techlanta.techlantademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        Button mEmailSignInButton = (Button) findViewById(R.id.LoginButton);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptLogin()) {
                    mAuth.signInWithEmailAndPassword(mEmailView.getText().toString(),
                            mPasswordView.getText().toString())
                            .addOnCompleteListener(LoginActivity.this,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign-in success

                                                Log.d("Sign in", "Sign in successful");
                                                final FirebaseUser user = mAuth.getCurrentUser();
                                                Toast.makeText(LoginActivity.this, "Sign in success",
                                                        Toast.LENGTH_LONG).show();
//
                                                updateUI(user);
                                                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(
                                                        LoginActivity.this, new OnSuccessListener<InstanceIdResult>() {
                                                            @Override
                                                            public void onSuccess(InstanceIdResult instanceIdResult) {
                                                                final String deviceToken = instanceIdResult.getToken();
                                                                DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference();
                                                                databaseUser = databaseUser.child("Users/" + user.getUid());

                                                                databaseUser.child("deviceToken").setValue(deviceToken);
                                                            }
                                                        });

                                                Intent mainActivity = new Intent(getApplicationContext(), WelcomeScreenActivity.class);
                                                startActivity(mainActivity);
                                            } else {
                                                // Sign-in fail
                                                Toast.makeText(LoginActivity.this,
                                                        "Authentication failed", Toast.LENGTH_LONG).show();
                                                updateUI(null);
                                            }
                                        }
                                    });
                }
            }
        });
//        Intent i = new Intent(this, WelcomeScreenActivity.class);
//        startActivity(i);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user == null) {
            return;
        }
    }

    private boolean attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(mPasswordView.getText()) || !isPasswordValid(password)) {
            mPasswordView.setError("Your password is wrong!!");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mEmailView.getText())) {
            mEmailView.setError("Please enter an valid email!!");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("Please enter an valid email!!");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        return !cancel;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



}
