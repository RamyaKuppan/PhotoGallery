package com.example.sample.photogallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<String> listOfImages;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, ArrayList<String> listOfImages) {
        this.context = context;
        this.listOfImages = listOfImages;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder imageHolder = null;

        switch (viewType) {
            case 0:
                View circleView = inflater.inflate(R.layout.layout_circleimage, parent, false);
                imageHolder = new ImageViewHolder(circleView);
                break;
            case 1:
                View squareView = inflater.inflate(R.layout.layout_squareimage, parent, false);
                imageHolder = new ImageViewHolder(squareView);
                break;
            case 2:
                View rectangleView = inflater.inflate(R.layout.layout_rectangleimage, parent, false);
                imageHolder = new ImageViewHolder(rectangleView);
                break;
        }
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ImageViewHolder imageViewHolder = (ImageViewHolder) holder;

        File file = new File(listOfImages.get(position));

        switch (getItemViewType(position)) {
            case 0:
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                    Glide.with(context)
                            .load(bitmap)
                            .apply(RequestOptions.circleCropTransform())
                            .into(imageViewHolder.image);
                   /* imageViewHolder.image.setImageBitmap(bitmap);

                    imageViewHolder.image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ImagePreviewActivity.class);
                            intent.putExtra("image", listOfImages.get(position));
                            context.startActivity(intent);
                        }
                    });*/
                }
                break;
            case 1:
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    imageViewHolder.image.setImageBitmap(bitmap);

                    imageViewHolder.image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ImagePreviewActivity.class);
                            intent.putExtra("image", listOfImages.get(position));
                            context.startActivity(intent);
                        }
                    });
                }
                break;
            case 2:
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    imageViewHolder.image.setImageBitmap(bitmap);

                    imageViewHolder.image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ImagePreviewActivity.class);
                            intent.putExtra("image", listOfImages.get(position));
                            context.startActivity(intent);
                        }
                    });
                }
                break;

        }
    }


    @Override
    public int getItemCount() {
        return listOfImages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
