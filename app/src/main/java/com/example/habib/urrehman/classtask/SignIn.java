package com.example.habib.urrehman.classtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.habib.urrehman.classtask.api.RetrofitClient;
import com.example.habib.urrehman.classtask.api.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    Button btSignIn;
    EditText etName, etPassword;
    String name, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btSignIn = findViewById(R.id.signIn);
        etName = findViewById(R.id.name);
        etPassword = findViewById(R.id.pasword);
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                password = etPassword.getText().toString();
                if (!(name.equals(""))) {
                    if (!password.equals("")) {
                        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                        String nameGet = sh.getString("name", "");
                        String passwGet = sh.getString("password", "");

                        if (name.equals(nameGet)){
                            if (password.equals(passwGet)){
                                Toast.makeText(SignIn.this, "Sign In Successful 😊", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignIn.this, ApiActivity.class);
                                intent.putExtra("Name",name);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SignIn.this, "Password mismatched", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignIn.this, "Name mismatched", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(SignIn.this, "Password can not be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignIn.this, "Name Can not be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}