package com.example.clswwearosgpsmemo2022.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StrokedText extends androidx.appcompat.widget.AppCompatTextView {
    public StrokedText(@NonNull Context context) {
        super(context);
    }

    public StrokedText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokedText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            super.draw(canvas);
        }
    }
}
