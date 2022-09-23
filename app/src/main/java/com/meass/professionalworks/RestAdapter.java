package com.meass.professionalworks;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RestAdapter extends ArrayAdapter<Model_Rest> {
    List<Model_Rest> model_rests;
    Context context;
    Bitmap bitmap;

    public RestAdapter( List<Model_Rest> model_rests, Context context) {
        super(context,R.layout.list_item,model_rests);
        this.model_rests = model_rests;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewholder viewholder;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.list_item,null,true);
        viewholder=new viewholder();
        viewholder.imageView=convertView.findViewById(R.id.imageView);
        viewholder.textViewImageUrl=convertView.findViewById(R.id.textViewImageUrl);
        viewholder.textViewName=convertView.findViewById(R.id.textViewName);
        convertView.setTag(viewholder);
        viewholder.textViewName.setText(model_rests.get(position).getName());
        viewholder.textViewImageUrl.setText(model_rests.get(position).getDescription());
        if (viewholder.imageView!=null) {
            new ImageDownloaderTask(viewholder.imageView).execute(model_rests.get(position).getImageurl());

        }
        viewholder.imageView.setImageBitmap(bitmap);

        return convertView;
    }

    static class viewholder{
        ImageView imageView;
        TextView textViewName,textViewImageUrl;
    }

}
