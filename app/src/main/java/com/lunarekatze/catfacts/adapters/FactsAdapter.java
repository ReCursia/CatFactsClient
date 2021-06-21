package com.lunarekatze.catfacts.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lunarekatze.catfacts.R;
import com.lunarekatze.catfacts.activities.FactDetailsActivity;
import com.lunarekatze.catfacts.models.Fact;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactViewHolder> {

    // TODO почему поля не private?
    Context mContext;
    List<Fact> mFactList;

    public FactsAdapter (Context mContext, List<Fact> mFactList) {
        // TODO по сути не нужен, читать ниже
        this.mContext = mContext;
        this.mFactList = mFactList;
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO (Review) nit: контекст можно получать из parent.getContext()
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_layout, parent, false);
        final FactViewHolder holder = new FactViewHolder(view);

        // TODO (Review) обычно в onCreateViewHolder не вешают слушателей и прочее, это можно сделать в onBindViewHolder
        // TODO обычно все события слушателя обрабатываются во фрагменте/активити, т.е. просто делается проброс, но это nit
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fact fact = getFact(holder.getAbsoluteAdapterPosition());

                // TODO (Review) названия extra правильнее будет в константы записать
                // TODO желательно делать фабричный статичный метод getLaunchIntent с нужными тебе параметрами,
                // TODO тогда можно точно знать какие параметры нужны
                Intent intent = new Intent(v.getContext(), FactDetailsActivity.class);
                intent.putExtra("fact_number", holder.getAbsoluteAdapterPosition()+1);
                intent.putExtra("fact", fact);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void  onBindViewHolder(@NonNull FactViewHolder holder, int position) {
        Fact fact = mFactList.get(position);
        holder.textView.setText(fact.getText());
        holder.factTitleView.setText(String.format(this.mContext.getString(R.string.fact_number), position + 1));
    }

    @Override
    public int getItemCount() {
        return mFactList.size();
    }

    public Fact getFact(int position) {
        // TODO можно объединить условия через &&
        if(mFactList != null) {
            if(mFactList.size() > 0) {
                return mFactList.get(position);
            }
        }
        return null;
    }

    // TODO (Review) холдер можно сделать статичным
    class FactViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView factTitleView;

        public FactViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
            factTitleView = itemView.findViewById(R.id.fact_title_view);
        }
    }
}
