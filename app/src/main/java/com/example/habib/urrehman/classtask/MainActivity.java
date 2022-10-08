package com.example.habib.urrehman.classtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvFooter;
    Button btSignUp;
    EditText etName, etPassword;
    String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFooter = findViewById(R.id.footer);
        btSignUp = findViewById(R.id.signUp);
        etName = findViewById(R.id.named);
        etPassword = findViewById(R.id.pasword);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                password = etPassword.getText().toString();
                if (!(name.equals(""))) {
                    if (!password.equals("")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                        SharedPreferences.Editor myEdit = sharedPreferences.edit();

                        myEdit.putString("name", name);
                        myEdit.putString("password",password );


                        myEdit.apply();
                        Toast.makeText(MainActivity.this, "Sign Up Successful 😊", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SignIn.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "Password can not be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Name Can not be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
        String text = "Already have an account Sign In";

        SpannableString ss = new SpannableString(text);

        // creating clickable span to be implemented as a link
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan1, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvFooter.setText(ss);
        tvFooter.setMovementMethod(LinkMovementMethod.getInstance());


    }
}