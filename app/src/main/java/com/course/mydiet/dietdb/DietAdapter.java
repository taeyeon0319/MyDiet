package com.course.mydiet.dietdb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.mydiet.DetailActivity;
import com.course.mydiet.R;
import com.course.mydiet.fooddb.FoodDB;

import org.w3c.dom.Text;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {

    private List<Diet> dietList;


    public DietAdapter(List<Diet> list) {
        dietList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_diet, parent, false);
        DietAdapter.ViewHolder vh = new DietAdapter.ViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Diet item = dietList.get(position);

        holder.title.setText(item.title);
        holder.time.setText(item.time);
        holder.review.setText(item.review);
        holder.place.setText(item.place);
        holder.image.setImageURI(Uri.parse(item.image));


        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mPostion = holder.getAdapterPosition();
                Context context = view.getContext();
                Intent detailActivity = new Intent(context, DetailActivity.class);
                detailActivity.putExtra("date", dietList.get(mPostion).date);
                detailActivity.putExtra("title", dietList.get(mPostion).title);
                detailActivity.putExtra("time", dietList.get(mPostion).time);
                detailActivity.putExtra("review", dietList.get(mPostion).review);
                detailActivity.putExtra("place", dietList.get(mPostion).place);
                detailActivity.putExtra("image", dietList.get(mPostion).image);
                (context).startActivity(detailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dietList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout card_view;
        TextView title;
        TextView time;
        TextView review;
        TextView place;
        ImageView image;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemFood);
            time = itemView.findViewById(R.id.itemTime);
            review = itemView.findViewById(R.id.itemReview);
            card_view = itemView.findViewById(R.id.linearlayout);
            place = itemView.findViewById(R.id.itemPlace);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
