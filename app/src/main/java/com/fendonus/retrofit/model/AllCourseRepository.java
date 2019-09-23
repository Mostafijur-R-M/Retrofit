package com.fendonus.retrofit.model;

import androidx.lifecycle.MutableLiveData;

import com.fendonus.retrofit.network.ApiClient;
import com.fendonus.retrofit.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCourseRepository {
    MutableLiveData<List<AllCourse>> mutableLiveData;
    List<AllCourse> allCourse;

    public MutableLiveData<List<AllCourse>> getMutableLiveData(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<AllCourse>> call = apiInterface.getAllCourse("IwAR0xGTEH4UJ1crYckICW5Kluqbg5fIJvcRZtY80iGtVCg2RPei1ay2PGvm8");
        call.enqueue(new Callback<List<AllCourse>>() {
            @Override
            public void onResponse(Call<List<AllCourse>> call, Response<List<AllCourse>> response) {
                allCourse = response.body();
                mutableLiveData.setValue(allCourse);
            }

            @Override
            public void onFailure(Call<List<AllCourse>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
