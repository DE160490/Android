package com.example.androidexercise;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {
    private List<Animal> animalList;
    private Context context;
    public AnimalAdapter(Context context, List<Animal> animalList) {
        this.context = context;
        this.animalList = animalList;
    }
    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise8_1_1, parent, false);
        return new AnimalViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);

        holder.textViewId.setText(String.valueOf(animal.getId()));
        holder.textViewName.setText(animal.getName());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Exercise8_2.class);
            intent.putExtra("id", animal.getId());
            intent.putExtra("name", animal.getName());
            intent.putExtra("description",
                    animal.getDescription());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return animalList.size();
    }
    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewName;
        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewName =
                    itemView.findViewById(R.id.textViewName);
        }

    }
}