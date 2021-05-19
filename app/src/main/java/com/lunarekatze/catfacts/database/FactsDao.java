package com.lunarekatze.catfacts.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FactsDao {
    @Query("SELECT * FROM facts_table WHERE fact_id = :fact_id")
    LiveData<FactItem> getFact(int fact_id);

    @Query("SELECT * FROM facts_table")
    LiveData<List<FactItem>> getAllFacts();

    @Insert
    void insert(FactItem factItem);

    @Query("DELETE FROM facts_table")
    void deleteAll();
}
