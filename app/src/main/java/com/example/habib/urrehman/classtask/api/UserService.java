package com.example.habib.urrehman.classtask.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
//
    @FormUrlEncoded
    @POST("/new_student.php")
    Call<Student> createPost(@Field("name") String name,@Field("email") String email,@Field("password") String password,@Field("status") String status);
    @FormUrlEncoded
    @POST("/get_student.php")
    Call<List<Student>> getStudentByName(@Field("name") String name);
    @GET("public/v2/users")
    Call<List<Users>> getAllUsers();
    @GET("public/v2/users/{id}")
    Call<Users> getUsersDetail(@Path("id") int id);
}
