package com.meass.professionalworks;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecondAdapert extends RecyclerView.Adapter<SecondAdapert.MyView> {
    List<Model_Rest> list;

    public SecondAdapert(List<Model_Rest> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {

    }

    class MyView extends RecyclerView.ViewHolder{

        public MyView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
