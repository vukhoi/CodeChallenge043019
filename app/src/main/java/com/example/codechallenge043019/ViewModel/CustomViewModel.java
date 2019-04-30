package com.example.codechallenge043019.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.codechallenge043019.Model.Movie;
import com.example.codechallenge043019.Model.MovieList;
import com.example.codechallenge043019.Model.RetrofitHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomViewModel extends ViewModel {
    private String TAG = this.getClass().getSimpleName();
    private MutableLiveData<List<Movie>> movieList = new MutableLiveData<List<Movie>>();

    public MutableLiveData<List<Movie>> getObservable(){
        RetrofitHelper retrofitHelper = RetrofitHelper.getINSTANCE();
        Observable<MovieList> movieListObservable = retrofitHelper.makeRetrofitCall();


        movieListObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(MovieList list) {
                        Log.d(TAG,"onNext");
                        movieList.postValue(list.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete");
                    }
                });

        return movieList;
    }
}
