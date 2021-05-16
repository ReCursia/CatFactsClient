package com.lunarekatze.catfacts;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class FactStorage {
    private static FactStorage sFactStorageInstance;
    private MutableLiveData<List<Fact>> mFacts;

    public static FactStorage getInstance(){
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

        return null;
    }
}
