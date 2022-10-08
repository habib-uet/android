package com.example.habib.urrehman.classtask.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL="https://studenttestx.000webhostapp.com/";
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    public  UserService getUserService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(UserService.class);
    }
}
