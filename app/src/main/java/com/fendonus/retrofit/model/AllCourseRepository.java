package com.fendonus.retrofit.model;

import androidx.lifecycle.MutableLiveData;

import com.fendonus.retrofit.network.ApiClient;
import com.fendonus.retrofit.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCourseRepository {
    MutableLiveData<AllCourse> mutableLiveData;
    AllCourse allCourse;

    public MutableLiveData<AllCourse> getMutableLiveData(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AllCourse> call = apiInterface.getAllCourse();
        call.enqueue(new Callback<AllCourse>() {
            @Override
            public void onResponse(Call<AllCourse> call, Response<AllCourse> response) {
                allCourse = response.body();
                mutableLiveData.setValue(allCourse);
            }

            @Override
            public void onFailure(Call<AllCourse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
