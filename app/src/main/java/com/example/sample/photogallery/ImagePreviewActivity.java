package com.example.sample.photogallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class ImagePreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        Intent intent=getIntent();
        String imagePath=intent.getStringExtra("image");
        ImageView imageView=(ImageView)findViewById(R.id.preview_image);

        File file=new File(imagePath);

        if(file.exists())
        {
            Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }

    }
}
