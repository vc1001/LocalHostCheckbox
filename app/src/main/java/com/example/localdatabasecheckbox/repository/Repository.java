package com.example.localdatabasecheckbox.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.localdatabasecheckbox.model.QuestionList;
import com.example.localdatabasecheckbox.services.GetService;
import com.example.localdatabasecheckbox.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    GetService getServicedata;

    public LiveData<QuestionList> getlivedata(){
        MutableLiveData<QuestionList> data = new MutableLiveData<>();
        getServicedata= RetrofitInstance.getService();

        Call<QuestionList> response = getServicedata.getData();

        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {

                QuestionList questionList= response.body();

                data.setValue(questionList);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {

            }
        });
        return data;
    }


}
