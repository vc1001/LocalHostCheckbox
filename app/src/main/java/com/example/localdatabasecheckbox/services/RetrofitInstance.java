package com.example.localdatabasecheckbox.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit= null;
   private static String Base_URL="http://192.168.29.137/myapi/";

   public static GetService getService(){

       if(retrofit==null){
           retrofit=new Retrofit.Builder().
                   baseUrl(Base_URL).
                   addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit.create(GetService.class);
   }
}
