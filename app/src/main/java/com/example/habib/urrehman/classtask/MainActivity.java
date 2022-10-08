package com.example.habib.urrehman.classtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habib.urrehman.classtask.api.RetrofitClient;
import com.example.habib.urrehman.classtask.api.Student;
import com.example.habib.urrehman.classtask.api.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvFooter;
    Button btSignUp;
    ProgressBar progressBar;
    EditText etName, etPassword,etConfirmPasw,etEmail,etStatus;
    String name, password,confirmPassw,email,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFooter = findViewById(R.id.footer);
        btSignUp = findViewById(R.id.signUp);
        etName = findViewById(R.id.named);
        etPassword = findViewById(R.id.pasword);
        etConfirmPasw=findViewById(R.id.repasword);
        etEmail=findViewById(R.id.email);
        etStatus=findViewById(R.id.status);
        progressBar=findViewById(R.id.progressBar);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                password = etPassword.getText().toString();
                confirmPassw=etConfirmPasw.getText().toString();
                email=etEmail.getText().toString();
                status=etStatus.getText().toString();
                if (!(name.equals(""))) {
                    if (!password.equals("")) {
                        if (!confirmPassw.equals("")){
                            if(!email.equals("")){
                                if(!status.equals("")){
                                    if(password.equals(confirmPassw)){
                                        progressBar.setVisibility(View.VISIBLE);
                                        Student modal = new Student(name,password,email,status);
                                        postData(modal);
                                        Intent intent = new Intent(MainActivity.this, SignIn.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "Confirm Password mis matched", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Status can't be empty", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Email Can't be empty", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Confirm Password can't be empty", Toast.LENGTH_SHORT).show();
                        }


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

    private void postData(Student modal) {

        // below line is for displaying our progress bar.


        // on below line we are creating a retrofit
        // builder and passing our base url
        RetrofitClient retrofitClient = new RetrofitClient();
//        Call<Users> userDetail=retrofitClient.getUserService().getUsersDetail(1);
        Call<Student> call = retrofitClient.getUserService().createPost(modal.getName(), modal.getEmail(), modal.getPassword(), modal.getStatus());


        // passing data from our text fields to our modal class.

        // on below line we are executing our method.
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.

                // on below line we are setting empty text
                // to our both edit text.


                // we are getting response from our body
                // and passing it to our modal class.

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nName : " + response.body().toString();
                // below line we are setting our
                // string to our text view.
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, ""+responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Error found is : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}