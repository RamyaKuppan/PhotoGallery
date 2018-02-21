package com.example.sample.photogallery.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


public class RectangleImageView extends AppCompatImageView {
    public RectangleImageView(Context context) {
        super(context);
    }

    public RectangleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectangleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(0, 0, 0, 0, paint);

    }
}
