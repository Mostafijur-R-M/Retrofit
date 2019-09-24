package com.fendonus.retrofit.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.fendonus.retrofit.network.ApiClient;
import com.fendonus.retrofit.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllChapterRepository {
    MutableLiveData<List<AllChapter>> mutableLiveData;
    List<AllChapter> allChapters;

    public MutableLiveData<List<AllChapter>> getMutableLiveData(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<AllChapter>> call = apiInterface.getAllChapter("IwAR0MunBO7ZA-ewDaQT6HHOHIF-zx0bI--9QzyoSNdcLnUKj-S5Nr7NbhXXA");
        call.enqueue(new Callback<List<AllChapter>>() {
            @Override
            public void onResponse(Call<List<AllChapter>> call, Response<List<AllChapter>> response) {
                allChapters = response.body();
                mutableLiveData.setValue(allChapters);
                Log.e("chapter:", response.body().get(0).getTitle());
                Log.e("chapter:", "eee");

            }

            @Override
            public void onFailure(Call<List<AllChapter>> call, Throwable t) {
                Log.e("chapter:", t.getLocalizedMessage());
            }
        });
        return mutableLiveData;
    }
}
