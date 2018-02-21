package com.example.sample.photogallery;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    public static final int GALLERY_REQUEST = 2;
    private RecyclerView rvImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvImages = (RecyclerView) findViewById(R.id.rv_images);
        checkPermission(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_REQUEST);
    }

    @Override
    public void onPermissionGrant(String[] permissions, int PERMISSION_REQUEST_CODE) {
        super.onPermissionGrant(permissions, PERMISSION_REQUEST_CODE);
        if (PERMISSION_REQUEST_CODE == GALLERY_REQUEST) {
            ArrayList<String> listOfAllImages = new ArrayList<>();
            Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            Cursor cursor = getContentResolver().query(uri, projection, null,
                    null, null);

            if (cursor != null) {
                int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                while (cursor.moveToNext()) {
                    String absolutePathOfImage = cursor.getString(column_index_data);

                    listOfAllImages.add(absolutePathOfImage);
                }

                ImageAdapter adapter = new ImageAdapter(this, listOfAllImages);
                rvImages.setLayoutManager(new GridLayoutManager(this, 3));
                rvImages.setAdapter(adapter);
            }


            Log.i("list of images", listOfAllImages.toString());
        }

    }

    @Override
    public void onPermissionDenied(String[] permissions, int PERMISSION_REQUEST_CODE) {
        super.onPermissionDenied(permissions, PERMISSION_REQUEST_CODE);
    }

}
