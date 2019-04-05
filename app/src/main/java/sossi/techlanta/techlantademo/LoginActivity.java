package sossi.techlanta.techlantademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = new Intent(this, WelcomeScreenActivity.class);
        startActivity(i);

    }

    public void onClickLogin(View v) {
        EditText userNameField = findViewById(R.id.UserNameField);
        EditText passwordField = findViewById(R.id.PasswordField);

        String userName = userNameField.getText().toString();
        String password = passwordField.getText().toString();

        if (userName.equals("user") && password.equals("pass")) {
            Intent i = new Intent(this, WelcomeScreenActivity.class);
            startActivity(i);
        }
    }

}
