package com.lunarekatze.catfacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FactsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);        // fixed width and height for child elements
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FactsViewModel model = ViewModelProviders.of(this).get(FactsViewModel.class);

        model.getFacts().observe(this, new Observer<List<Fact>>() {
            @Override
            public void onChanged(@Nullable List<Fact> mFactList) {
                mAdapter = new FactsAdapter(MainActivity.this, mFactList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}
