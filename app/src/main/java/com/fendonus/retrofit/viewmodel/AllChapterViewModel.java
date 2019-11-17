package com.fendonus.retrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.AllChapterRepository;

import java.util.List;

public class AllChapterViewModel extends AndroidViewModel {
    AllChapterRepository allChapterRepository;

    public AllChapterViewModel(@NonNull Application application) {
        super(application);
        allChapterRepository = new AllChapterRepository();
    }
    public LiveData<List<AllChapter>> listLiveData(String course_id){
        return allChapterRepository.getMutableLiveData(course_id);
    }
}
