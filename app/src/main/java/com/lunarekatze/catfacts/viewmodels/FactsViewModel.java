package com.lunarekatze.catfacts.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lunarekatze.catfacts.database.FactItem;
import com.lunarekatze.catfacts.database.FactsRepository;
import com.lunarekatze.catfacts.interfaces.Api;
import com.lunarekatze.catfacts.models.Fact;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactsViewModel extends AndroidViewModel {

    private FactsRepository mFactRepository;
    private LiveData<List<FactItem>> mAllFacts;

    public FactsViewModel(Application app) {
        super(app);
        mFactRepository = new FactsRepository(app);
        mAllFacts = mFactRepository.getmAllFacts();
    }

    public LiveData<List<FactItem>> getFacts() {
        return mAllFacts;
    }

    public void insert(FactItem fact) {
        mFactRepository.insert(fact);
    }

    public void deleteAll() {
        mFactRepository.deleteAll();
    }
}
