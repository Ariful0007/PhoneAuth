package com.meass.professionalworks;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.myview> {
    public List<Model_Rest> data;

    public VideoAdapter(List<Model_Rest> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VideoAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list2, parent, false);
        return new VideoAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.myview holder, final int position) {
      ArrayList<SlideModel>slideModels=new ArrayList<>();
      slideModels.add(new SlideModel(data.get(position).getImageurl()));

holder.slider.setImageList(slideModels,true);
holder.textViewName.setText(data.get(position).getName());
holder.textViewImageUrl.setText(data.get(position).getImageurl());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
       ImageSlider slider;
       TextView textViewName,textViewImageUrl;

        public myview(@NonNull View itemView) {
            super(itemView);
            slider=itemView.findViewById(R.id.imageView);
            textViewName=itemView.findViewById(R.id.textViewName);
            textViewImageUrl=itemView.findViewById(R.id.textViewImageUrl);

        }
    }
}