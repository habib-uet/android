package com.example.habib.urrehman.classtask.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
//

    @GET("public/v2/users")
    Call<List<Users>> getAllUsers();
    @GET("public/v2/users/{id}")
    Call<Users> getUsersDetail(@Path("id") int id);
}
