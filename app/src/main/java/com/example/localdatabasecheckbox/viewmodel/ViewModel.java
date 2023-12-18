package com.example.localdatabasecheckbox.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.localdatabasecheckbox.model.Question;
import com.example.localdatabasecheckbox.model.QuestionList;
import com.example.localdatabasecheckbox.repository.Repository;

public class ViewModel extends androidx.lifecycle.ViewModel {

    Repository repository = new Repository();

    LiveData<QuestionList> liveData;

    public ViewModel(){
        liveData=repository.getlivedata();
    }
    public LiveData<QuestionList> getLiveData(){
        return liveData;
    }
}
