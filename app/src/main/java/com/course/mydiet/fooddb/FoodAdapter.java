package com.course.mydiet.fooddb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.mydiet.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder2> {
    private List<Food> foodList;

    public FoodAdapter(List<Food> list){
        foodList = list;
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_food, parent, false);
        FoodAdapter.ViewHolder2 vh = new FoodAdapter.ViewHolder2(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        Food item = foodList.get(position);
        holder.food.setText(item.foodname);
        holder.amount.setText(String.valueOf(item.amount));
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView food;
        TextView amount;

        ViewHolder2(View itemView) {
            super(itemView);

            food = itemView.findViewById(R.id.itemFood);
            amount = itemView.findViewById(R.id.itemAmount);
        }

    }
}
