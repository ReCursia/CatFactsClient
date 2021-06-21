package com.lunarekatze.catfacts.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lunarekatze.catfacts.interfaces.Api;
import com.lunarekatze.catfacts.models.Fact;
import com.lunarekatze.catfacts.repositories.FactStorage;

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
        //TODO как раз таки этот код должен быть в репозитории, и он не должен быть в курсе про ViewModel
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
                //TODO нет проверки response.isSuccessful() прежде чем делать дальнейшие шаги
                mFactList.setValue(response.body());
                mFactStorage.setFacts(response.body());
            }

            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {
                //TODO хотя бы логи добавить Log.e(...) или же оповестить пользователя через тосты
            }
        });
    }
}
