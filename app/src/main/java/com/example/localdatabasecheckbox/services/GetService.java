package com.example.localdatabasecheckbox.services;

import com.example.localdatabasecheckbox.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    @GET("myquizapi.php")
    Call<QuestionList> getData();

}
