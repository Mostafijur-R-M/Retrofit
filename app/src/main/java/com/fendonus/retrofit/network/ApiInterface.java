package com.fendonus.retrofit.network;

import com.fendonus.retrofit.model.AllCourse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("courses/1?fbclid=IwAR0xGTEH4UJ1crYckICW5Kluqbg5fIJvcRZtY80iGtVCg2RPei1ay2PGvm8")
    Call<AllCourse> getAllCourse();
}
