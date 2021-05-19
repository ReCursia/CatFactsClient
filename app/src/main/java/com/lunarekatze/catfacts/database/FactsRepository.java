package com.lunarekatze.catfacts.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FactsRepository {

    private FactsDao mFactsDao;
    private LiveData<List<FactItem>> mAllFacts;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    public FactsRepository(Context context) {
        FactsRoomDatabase db = FactsRoomDatabase.getDatabase(context);
        mFactsDao = db.wordDao();
        mAllFacts = mFactsDao.getAllFacts();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<FactItem>> getmAllFacts() {
        return mAllFacts;
    }

    public LiveData<FactItem> getFact(int fact_id) {
        return mFactsDao.getFact(fact_id);
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert(FactItem fact) {
        new insertAsyncTask(mFactsDao).execute(fact);
    }

    public void deleteAll() {
        new deleteAsyncTask(mFactsDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<FactItem, Void, Void> {

        private FactsDao mAsyncTaskDao;

        insertAsyncTask(FactsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FactItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask {

        private FactsDao mAsyncTaskDao;

        deleteAsyncTask(FactsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}