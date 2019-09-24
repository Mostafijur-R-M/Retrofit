package com.fendonus.retrofit.network;

import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.AllCourse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/courses/1{fbclid}")
    Call<List<AllCourse>> getAllCourse(@Query("fbclid")
                                               String fbclid);

    @GET("api/chapter/90046{fbclid}")
    Call<List<AllChapter>> getAllChapter(@Query("fbclid")
                                                 String fbclid);
}
//IwAR0xGTEH4UJ1crYckICW5Kluqbg5fIJvcRZtY80iGtVCg2RPei1ay2PGvm8
//IwAR0MunBO7ZA-ewDaQT6HHOHIF-zx0bI--9QzyoSNdcLnUKj-S5Nr7NbhXXA