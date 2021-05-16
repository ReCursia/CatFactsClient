package com.lunarekatze.catfacts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactsViewModel extends ViewModel {
    private FactStorage mFactStorage;
    // the data we will get asynchronously
    //private MutableLiveData<List<Fact>> mFactList;

    public FactsViewModel() {
        mFactStorage = FactStorage.getInstance();
    }

    public LiveData<List<Fact>> getFacts() {
        loadFacts();

        return mFactStorage.getFacts();
    }

    private void loadFacts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Fact>> call = api.getFacts();

        call.enqueue(new Callback<List<Fact>>() {
            @Override
            public void onResponse(Call<List<Fact>> call, Response<List<Fact>> response) {
                MutableLiveData<List<Fact>> mFactList = new MutableLiveData<>();
                mFactList.setValue(response.body());
                mFactStorage.setFacts(response.body());
            }

            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {

            }
        });
    }
}
