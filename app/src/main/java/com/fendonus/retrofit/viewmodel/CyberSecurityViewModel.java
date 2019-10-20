package com.fendonus.retrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.CyberSecurityChapterRepository;

import java.util.List;

public class CyberSecurityViewModel extends AndroidViewModel {
    CyberSecurityChapterRepository cyberSecurityChapterRepository;

    public CyberSecurityViewModel(@NonNull Application application) {
        super(application);
        cyberSecurityChapterRepository = new CyberSecurityChapterRepository();
    }

    public LiveData<List<AllChapter>> listLiveData(){
        return cyberSecurityChapterRepository.getMutableLiveData();
    }
}
