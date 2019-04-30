package com.example.codechallenge043019.Model;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static Api api;
    private static Retrofit retrofit;
    private static RetrofitHelper INSTANCE;

    private RetrofitHelper(){
        initializeRetrofit();
    }

    public static RetrofitHelper getINSTANCE(){
        if (INSTANCE == null){
            synchronized (RetrofitHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new RetrofitHelper();
                }
            }
        }
        return INSTANCE;
    }

    private static void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://movies-sample.herokuapp.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }


    public Observable makeRetrofitCall() {
        return api.getMovieList();
    }

}
