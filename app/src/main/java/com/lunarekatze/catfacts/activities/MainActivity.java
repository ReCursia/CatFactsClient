package com.lunarekatze.catfacts.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lunarekatze.catfacts.adapters.FactsAdapter;
import com.lunarekatze.catfacts.viewmodels.FactsViewModel;
import com.lunarekatze.catfacts.R;
import com.lunarekatze.catfacts.models.Fact;

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
                /* TODO (Review) нет нужды каждый раз создавать инстанс адаптера, можно добавить метод setFacts в адаптер
                    и делать notifyDataSetChanged()
                 */
                mAdapter = new FactsAdapter(MainActivity.this, mFactList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}
