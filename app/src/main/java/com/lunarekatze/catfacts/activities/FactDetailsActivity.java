package com.lunarekatze.catfacts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.lunarekatze.catfacts.R;
import com.lunarekatze.catfacts.models.Fact;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FactDetailsActivity extends AppCompatActivity {

    private TextView mFactText;
    private TextView mFactCreatedAt;
    private TextView mFactTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_details);

        mFactText = findViewById(R.id.fact_text_view);
        mFactCreatedAt = findViewById(R.id.fact_createdAt_view);
        mFactTitle = findViewById(R.id.fact_title_view);

        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if(getIntent().hasExtra("fact")) {
            Fact fact = getIntent().getParcelableExtra("fact");
            int factNumber = getIntent().getIntExtra("fact_number", 0);

            mFactTitle.setText(String.format(getString(R.string.fact_number), factNumber));
            mFactText.setText(fact.getText());

            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date date = format.parse(fact.getCreatedAt());

                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                String formattedDate = dateTimeFormat.format(date);

                mFactCreatedAt.setText(formattedDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
}
