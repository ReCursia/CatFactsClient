package com.lunarekatze.catfacts.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.lunarekatze.catfacts.adapters.FactsAdapter;
import com.lunarekatze.catfacts.database.FactItem;
import com.lunarekatze.catfacts.interfaces.Api;
import com.lunarekatze.catfacts.network.DataServiceGenerator;
import com.lunarekatze.catfacts.viewmodels.FactsViewModel;
import com.lunarekatze.catfacts.R;
import com.lunarekatze.catfacts.models.Fact;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FactsAdapter mAdapter;
    FactsViewModel mFactsViewModel;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh = findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(this::fetchFacts);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);        // fixed width and height for child elements
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchFacts();

        mFactsViewModel = ViewModelProviders.of(this).get(FactsViewModel.class);

        mFactsViewModel.getFacts().observe(this, mFactList -> {
            swipeRefresh.setRefreshing(false);
            mAdapter = new FactsAdapter(MainActivity.this, mFactList);
            mRecyclerView.setAdapter(mAdapter);
        });
    }

    private void fetchFacts(){
        swipeRefresh.setRefreshing(true);

        Api apiService = DataServiceGenerator.createService(Api.class);
        Call<List<Fact>> call = apiService.getFacts();

        call.enqueue(new Callback<List<Fact>>() {
            @Override
            public void onResponse(@NotNull Call<List<Fact>> call, @NotNull Response<List<Fact>> response) {
                if (response.isSuccessful()){
                    mFactsViewModel.deleteAll();

                    List<Fact> factsModelList = response.body();

                    if (factsModelList != null) {
                        for (int i = 0; i < factsModelList.size(); i++){
                            int factStatusVerified = factsModelList.get(i).getStatus().getVerified() ? 1 : 0;
                            int factStatusSentCount = factsModelList.get(i).getStatus().getSentCount();
                            String type = factsModelList.get(i).getType();
                            int deleted = factsModelList.get(i).getDeleted() ? 1 : 0;
                            String id = factsModelList.get(i).getId();
                            String user = factsModelList.get(i).getUser();
                            String text = factsModelList.get(i).getText();
                            int v = factsModelList.get(i).getV();
                            String source = factsModelList.get(i).getSource();
                            String updatedAt = factsModelList.get(i).getUpdatedAt();
                            String createdAt = factsModelList.get(i).getCreatedAt();
                            int used = factsModelList.get(i).getUsed() ? 1 : 0;

                            FactItem factItem = new FactItem(factStatusVerified, factStatusSentCount, type, deleted, id, user, text, v, source, updatedAt, createdAt, used);

                            mFactsViewModel.insert(factItem);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Fact>> call, @NotNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}