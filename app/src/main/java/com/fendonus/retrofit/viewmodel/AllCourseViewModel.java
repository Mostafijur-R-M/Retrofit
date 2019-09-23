package com.fendonus.retrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fendonus.retrofit.model.AllCourse;
import com.fendonus.retrofit.model.AllCourseRepository;

import java.util.List;

public class AllCourseViewModel extends AndroidViewModel {
    AllCourseRepository allCourseRepository;

    public AllCourseViewModel(@NonNull Application application) {
        super(application);
        allCourseRepository = new AllCourseRepository();
    }
    public LiveData<List<AllCourse>> liveData(){
        return allCourseRepository.getMutableLiveData();
    }
}
