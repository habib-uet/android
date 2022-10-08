package com.example.habib.urrehman.classtask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habib.urrehman.classtask.api.RetrofitClient;
import com.example.habib.urrehman.classtask.api.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiActivity extends AppCompatActivity {
 ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar=findViewById(R.id.progressBar);
        setContentView(R.layout.activity_api);
        String name=getIntent().getStringExtra("Name");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getAllUsers();
    }
    private void getAllUsers() {
        RetrofitClient retrofitClient = new RetrofitClient();
//        Call<Users> userDetail=retrofitClient.getUserService().getUsersDetail(1);
        Call<List<Users>> usersCall = retrofitClient.getUserService().getAllUsers();
        usersCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                Toast.makeText(ApiActivity.this, "here", Toast.LENGTH_SHORT).show();
                List<Users> dataList = response.body();
                Toast.makeText(ApiActivity.this, ""+dataList.size(), Toast.LENGTH_SHORT).show();
                if(dataList.size()>0){
                    progressBar=findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.INVISIBLE);
                    RecyclerView recyclerViewAdapter;
                    recyclerViewAdapter=findViewById(R.id.recycler);
                    PersonAdaptor personAdaptor=new PersonAdaptor();
                    ArrayList<Users> users = (ArrayList<Users>) dataList;
                    personAdaptor.setChatEntities(users);
                    recyclerViewAdapter.setAdapter(personAdaptor);
                    recyclerViewAdapter.setLayoutManager(new LinearLayoutManager(ApiActivity.this));
                    recyclerViewAdapter.setItemAnimator(new DefaultItemAnimator());
//                    Toast.makeText(ApiActivity.this, ""+dataList.get(0).getName(), Toast.LENGTH_SHORT).show();
//                    tv.setText(dataList.get(0).getTitle());
                }

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(ApiActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}