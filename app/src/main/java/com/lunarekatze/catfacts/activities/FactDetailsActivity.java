package com.lunarekatze.catfacts.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.widget.TextView;

import com.lunarekatze.catfacts.R;
import com.lunarekatze.catfacts.database.FactItem;
import com.lunarekatze.catfacts.database.FactsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FactDetailsActivity extends AppCompatActivity {

    private FactsRepository mFactsRepository;
    private TextView mFactText;
    private TextView mFactCreatedAt;
    private TextView mFactTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_details);

        mFactsRepository = new FactsRepository(getApplicationContext());

        mFactText = findViewById(R.id.fact_text_view);
        mFactCreatedAt = findViewById(R.id.fact_createdAt_view);
        mFactTitle = findViewById(R.id.fact_title_view);

        showDetails();
    }

    private void showDetails() {
        int factNumber = getIntent().getIntExtra("fact_number", 0);
        int factId = getIntent().getIntExtra("fact_id", 0);

        LiveData<FactItem> factItem = mFactsRepository.getFact(factId);

        factItem.observe(this, fact -> {
            if(fact != null) {
                mFactTitle.setText(String.format(getString(R.string.fact_number), factNumber));
                mFactText.setText(fact.getText());

                try {
                    if(fact.getCreatedAt() != null) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                        Date date = format.parse(fact.getCreatedAt());

                        if(date != null) {
                            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
                            String formattedDate = dateTimeFormat.format(date);

                            mFactCreatedAt.setText(formattedDate);
                        }
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}