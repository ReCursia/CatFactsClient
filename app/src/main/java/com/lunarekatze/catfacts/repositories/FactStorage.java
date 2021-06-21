package com.lunarekatze.catfacts.repositories;

import androidx.lifecycle.MutableLiveData;

import com.lunarekatze.catfacts.models.Fact;

import java.util.List;

public class FactStorage {
    private static FactStorage sFactStorageInstance;

    // TODO надо перенести в FactsViewModel, зачем это здесь?
    private MutableLiveData<List<Fact>> mFacts;

    public static FactStorage getInstance(){
        // TODO singletone для небольшого проекта ничего страшного конечно, но стоит почитать про Dagger или Hilt (DI)
        if (sFactStorageInstance == null){
            sFactStorageInstance = new FactStorage();
        }
        return sFactStorageInstance;
    }
    private FactStorage(){
        mFacts = new MutableLiveData<>();
    }

    public void setFacts(List<Fact> facts){
        mFacts.setValue(facts);
    }

    public MutableLiveData<List<Fact>> getFacts(){
        return mFacts;
    }

    public Fact getFact(int number) {
        // TODO почему нулл, надо доделать
        return null;
    }
}
