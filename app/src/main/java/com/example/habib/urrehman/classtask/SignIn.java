package com.example.habib.urrehman.classtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.habib.urrehman.classtask.api.RetrofitClient;
import com.example.habib.urrehman.classtask.api.Student;

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
                        getList(name, password);


                    } else {
                        Toast.makeText(SignIn.this, "Password can not be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignIn.this, "Name Can not be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getList(String name, String password) {

        // below line is for displaying our progress bar.


        // on below line we are creating a retrofit
        // builder and passing our base url
        RetrofitClient retrofitClient = new RetrofitClient();
//        Call<Users> userDetail=retrofitClient.getUserService().getUsersDetail(1);
        Call<List<Student>> call = retrofitClient.getUserService().getStudentByName(name);


        // passing data from our text fields to our modal class.

        // on below line we are executing our method.
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> list = response.body();
                Toast.makeText(SignIn.this, "Name Found", Toast.LENGTH_SHORT).show();
                if (list.size() != 0) {
                    if (list.get(0).getPassword().equals(password)) {
                        Toast.makeText(SignIn.this, "successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, StudentDetails.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(SignIn.this, "password mismatched", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignIn.this, "user name mismatched", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {

                Toast.makeText(SignIn.this, "Error Found " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}